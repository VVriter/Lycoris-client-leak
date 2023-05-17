package ua.lycoris.client.systems.modules.impl.client

import ua.lycoris.client.DiscordRPC
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.systems.modules.ModuleManifest

@ModuleManifest(
    name = "DiscordRPC",
    category = Module.Category.CLIENT,
    description = ""
)
class DiscordRpcModule : Module() {
    override fun onEnable() {
        super.onEnable()
        DiscordRPC.run()
    }

    override fun onDisable() {
        super.onDisable()
        DiscordRPC.stop()
    }
}