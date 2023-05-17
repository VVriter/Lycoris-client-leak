package ua.lycoris.client.systems.commands.impl

import ua.lycoris.client.Logger
import ua.lycoris.client.systems.commands.Command
import ua.lycoris.client.systems.commands.CommandManifest
import ua.lycoris.client.systems.modules.Module

@CommandManifest(
    name = "bind",
    min = 2,
    max = 2,
    leghtExeption = "Invalid arguments length! Usage - bind <module> <key>"
)
class BindModuleCommand : Command() {
    override fun execute(args: Array<String>) {
        super.execute(args)
        var module: Module = moduleManager.modules.stream().filter{ m -> m.name.equals(args[0])}.findFirst().orElse(null)
        if (module != null) module.bind.setBindValue(args[1])
        Logger.log("Current " + args[0] + " bind is " + module.bind.name,-1117)
    }
}