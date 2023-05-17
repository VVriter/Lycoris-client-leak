package ua.lycoris.client.systems.commands.impl

import ua.lycoris.client.Logger
import ua.lycoris.client.systems.commands.Command
import ua.lycoris.client.systems.commands.CommandManifest

@CommandManifest(
    name = "server",
    description = "Use it command to get all information about server!"
)
class ServerInfoCommand : Command() {
    override fun execute(args: Array<String>) {
        super.execute(args)
        if (mc.currentServerData == null) {
            Logger.error("You cant use this command in single player.",true)
            return
        }

        val data = mc.currentServerData ?: return

        Logger.log("Info about server:",false)
        Logger.log("Name: §8${data.serverName}",false)
        Logger.log("IP: §8${data.serverIP}",false)
        Logger.log("Players: §8${data.populationInfo}",false)
        Logger.log("MOTD: §8${data.serverMOTD}",false)
        Logger.log("ServerVersion: §8${data.gameVersion}",false)
        Logger.log("ProtocolVersion: §8${data.version}",false)
        Logger.log("Ping: §8${data.pingToServer}",false)
    }
}