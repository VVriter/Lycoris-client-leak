package ua.lycoris.client.systems.commands.impl

import ua.lycoris.client.Logger
import ua.lycoris.client.systems.commands.Command
import ua.lycoris.client.systems.commands.CommandManifest
import ua.lycoris.client.systems.modules.Module

@CommandManifest(name = "enable",
    description = "Use it to enable modules through chat",
    min = 1,
    leghtExeption = "Invalid arguments length! Usage - enable <module>"
)
class EnableModuleCommand : Command() {
    override fun execute(args: Array<String>) {
        super.execute(args)
        var module: Module = moduleManager.modules.stream().filter {m -> m.getName().equals(args[0])}.findFirst().orElse(null)
        if (module != null) module.onEnable()
        Logger.log(args[0] + " is Enabled now!",-1117)
    }
}