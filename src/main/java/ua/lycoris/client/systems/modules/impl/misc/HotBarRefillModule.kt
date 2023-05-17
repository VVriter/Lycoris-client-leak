package ua.lycoris.client.systems.modules.impl.misc

import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.network.play.client.CPacketCloseWindow
import net.minecraft.network.play.client.CPacketEntityAction
import net.minecraft.network.play.client.CPacketEntityAction.Action
import ua.lycoris.client.events.player.UpdateWalkingPlayerEvent
import ua.lycoris.client.settings.impl.numerical.IntegerSetting
import ua.lycoris.client.settings.impl.primitive.BooleanSetting
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.systems.modules.ModuleManifest
import ua.lycoris.client.utils.client.MathUtil
import ua.lycoris.client.utils.player.InventoryUtil
import ua.lycoris.client.utils.player.ItemUtil
import ua.puncakecat.beet.Listener
import ua.puncakecat.beet.Subscribe


/**
 * @author PunCakeCat/Kristofer
 */
@ModuleManifest(
    name = "HotBarRefill",
    category = Module.Category.MISC,
    description = "Automatically refills the hotbar"
)
class HotBarRefillModule : Module() {

    private val packetSpoof by BooleanSetting("PacketSpoof", true)
    private val refillPercent by IntegerSetting("Percent", 30, 1 ,100)

    private var cache: HashMap<Int, Item>? = null

    override fun onEnable() {
        super.onEnable()
        cache = HashMap()
    }

    override fun onDisable() {
        super.onDisable()
        cache = null
    }

    @Subscribe
    private val motionListener = Listener{ event: UpdateWalkingPlayerEvent ->
        if(fullNullCheck() && mc.currentScreen != null) return@Listener

        val header = cache!!.entries.stream()
            .filter{ entry ->
                ((entry.value !== Items.AIR)
                        && (InventoryUtil.findItemSlotNoHotBar(entry.value) != -1)
                        && (ItemUtil.getItemStackPercentage(InventoryUtil.getItemStackFromID(entry.key)) <= refillPercent
                        || entry.value !== InventoryUtil.getItemStackFromID(entry.key).item))
            }
            .findFirst().orElse(null)
        if(header == null){
            cache()
            return@Listener
        }

        val itemStack = InventoryUtil.getItemStackFromID(header.key)
        val count = MathUtil.booleanToNumber(!itemStack.isEmpty).toInt() * itemStack.count
        val back = count + InventoryUtil.getItemStackFromID(InventoryUtil.findItemSlotNoHotBar(header.value)).count > header.value.getItemStackLimit()
        val origin = InventoryUtil.findItemSlotNoHotBar(header.value)

        if(packetSpoof)
            mc.player.connection.sendPacket(CPacketEntityAction(mc.player, Action.OPEN_INVENTORY))

        InventoryUtil.clickSlot(origin)
        InventoryUtil.clickSlot(header.key)
        if(back)
            InventoryUtil.clickSlot(origin)

        if(packetSpoof)
            mc.player.connection.sendPacket(CPacketCloseWindow())
    }

    private fun cache(){
        for(i in 0..8)
            cache!![i] = InventoryUtil.getItemStackFromID(i).item
    }
}