package ua.lycoris.client.systems.commands.impl

import ua.lycoris.client.Logger
import ua.lycoris.client.Lycoris
import ua.lycoris.client.systems.commands.Command
import ua.lycoris.client.systems.commands.CommandManifest

@CommandManifest(
    name = "friend",
    min = 2,
    max = 2,
    leghtExeption = "Invalid arguments length! Usage - friend <add/del> <username>"
)

class FriendCommand : Command() {
    override fun execute(args: Array<out String>) {
        super.execute(args)

        if (args[1].equals("add")) {
            Logger.log(args[2] + " added as friend",-1117)
            Lycoris.friendManager.addFriend(args[2])
            mc.player.sendChatMessage("/msg " + args[2] + "[LYCORIS] You added as friend!")
        }

        if (args[1].equals("del")) {
            Logger.log(args[2] + " deleted as friend",-1118)
            Lycoris.friendManager.dellFriend(args[2])
        }

    }
}