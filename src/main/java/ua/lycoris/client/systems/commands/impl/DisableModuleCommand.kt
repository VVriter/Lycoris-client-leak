package ua.lycoris.client.systems.commands.impl

import ua.lycoris.client.Logger
import ua.lycoris.client.systems.commands.Command
import ua.lycoris.client.systems.commands.CommandManifest
import ua.lycoris.client.systems.modules.Module

@CommandManifest(name = "disable",
    description = "Use it to disable modules through chat",
    min = 1,
    leghtExeption = "Invalid arguments length! Usage - disable <module>"
)
class DisableModuleCommand : Command() {
    override fun execute(args: Array<String>) {
        super.execute(args)
        var module: Module = moduleManager.modules.stream().filter{m -> m.name.equals(args[0])}.findFirst().orElse(null)
        if (module != null) module.onDisable()
        Logger.log(args[0] + " is Disabled now!",-1117)
    }
}