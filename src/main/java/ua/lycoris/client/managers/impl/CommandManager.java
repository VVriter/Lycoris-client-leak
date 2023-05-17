package ua.lycoris.client.managers.impl;

import net.minecraft.network.play.client.CPacketChatMessage;
import org.apache.commons.lang3.ArrayUtils;
import ua.lycoris.client.Info;
import ua.lycoris.client.Logger;
import ua.lycoris.client.events.network.PacketEvent;
import ua.lycoris.client.managers.Manager;
import ua.lycoris.client.systems.commands.Command;
import ua.lycoris.client.utils.ClassFinder;
import ua.puncakecat.beet.Listener;
import ua.puncakecat.beet.Subscribe;

import java.util.*;

public class CommandManager extends Manager {

    private List<Command> commands;
    public CommandManager() {
        commands = new ArrayList<>();
        registerCommands();
    }

    @Subscribe
    private Listener<PacketEvent.Send> onPacketSend = new Listener<>(event -> {
        if (event.getPacket() instanceof CPacketChatMessage) {
            String message = ((CPacketChatMessage) event.getPacket()).getMessage();

            if (message.startsWith( Info.COMMAND_PREFIX)){
                String[] input = message.substring( Info.COMMAND_PREFIX.length()).split(" ");
                Command command = commands.stream().filter(comm -> comm.getName().equals(input[0])).findFirst().orElse(null);
                if(command != null) {
                    String[] args = ArrayUtils.remove(input, 0);
                    if (isInBound(args, command))
                        command.execute(args);
                    else
                        Logger.error( command.getLeghtError(), true);
                }else
                    Logger.error("Invalid command", true);

                event.setCancelled(true);
            }
        }
    });

    private void registerCommands() {
        Set<Class> classList = ClassFinder.findClasses(Command.class.getPackage().getName(), Command.class);
        classList.forEach(aClass -> {
            try {
                Command module = (Command) aClass.getConstructor().newInstance();
                commands.add(module);
            } catch (Exception e) {
                e.getCause().printStackTrace();
                System.err.println("Couldn't initiate command " + aClass.getSimpleName() + "! Err: " + e.getClass().getSimpleName() + ", message: " + e.getMessage());
            }
        });
        System.out.println("Commands initialised");
        commands.sort(Comparator.comparing(Command::getName));
    }

    private boolean isInBound(String[] args, Command command){
        return args.length >= command.getMin() && args.length <= command.getMax();
    }
}