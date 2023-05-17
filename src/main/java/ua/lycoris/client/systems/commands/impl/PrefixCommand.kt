package ua.lycoris.client.systems.commands.impl

import ua.lycoris.client.Info
import ua.lycoris.client.Logger
import ua.lycoris.client.systems.commands.Command
import ua.lycoris.client.systems.commands.CommandManifest

@CommandManifest(
    name = "prefix",
    min = 1,
    max = 1,
    leghtExeption = "Invalid arguments length! Usage - prefix <key>"
)
class PrefixCommand : Command() {
    override fun execute(args: Array<String>) {
        super.execute(args)
            Info.COMMAND_PREFIX = args[0]
            Logger.warn("Your prefix is ["+ (args[0]) +"] now!",true)
    }
}