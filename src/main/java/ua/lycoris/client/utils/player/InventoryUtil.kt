package ua.lycoris.client.utils.player

import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.inventory.ClickType
import net.minecraft.item.*
import net.minecraft.network.play.client.CPacketHeldItemChange
import ua.lycoris.client.utils.block.BlockUtil
import ua.lycoris.client.utils.interfaces.Globals


object InventoryUtil : Globals {
    var mc = Minecraft.getMinecraft()

    fun clickSlot(id: Int) {
        mc.playerController.windowClick(
            mc.player.openContainer.windowId,
            getClickingSlot(id),
            0,
            ClickType.PICKUP,
            mc.player
        )
    }

    fun getItemCount(item: Item): Int {
        var count = mc.player.inventory.mainInventory.stream()
            .filter { itemStack -> itemStack.item === item }
            .mapToInt { itemStack -> itemStack.count }.sum()
        if (mc.player.heldItemOffhand.item === item)
            count += mc.player.heldItemOffhand.count
        return count
    }

    fun getItemStackFromID(id: Int): ItemStack = mc.player.inventory.getStackInSlot(id)

    fun findItemInHotBar(item: Item): Int {
        for (i in 0..8) {
            val itemStack = mc.player.inventory.getStackInSlot(i)
            if (itemStack.item === item)
                return i
        }
        return -1
    }

    fun findItemSlotNoHotBar(input: Item): Int {
        for (i in 36 downTo 9) {
            val item = mc.player.inventory.getStackInSlot(i).item
            if (item === input)
                return i
        }
        return -1
    }

    fun switchToSlot(slot: Int) {
        if (mc.player.inventory.currentItem == slot || slot == -1)
            return
        //Send packet to server
        mc.player.connection.sendPacket(CPacketHeldItemChange(slot))
        mc.player.inventory.currentItem = slot
        mc.playerController.updateController()
    }

    fun findBlockInHotBar(block: Block?): Int {
        return findItemInHotBar(Item.getItemFromBlock(block))
    }

    fun isItemInHotbar(item: Item): Boolean {
        var isItemPresent = false
        for (i in 0..8) {
            val itemStack = mc.player.inventory.getStackInSlot(i)
            if (itemStack.item === item)
                isItemPresent = true
        }
        return isItemPresent
    }

    fun isItemInInventory(item: Item): Boolean {
        for (slot in mc.player.inventoryContainer.inventorySlots)
            if (slot.stack.item === item) return true
        return false
    }

    fun fastestMiningTool(toMineBlockMaterial: Block): Int {
        var fastestSpeed = 1.0f
        var theSlot = mc.player.inventory.currentItem
        for (i in 0..8) {
            val itemStack = mc.player.inventory.getStackInSlot(i)
            if (itemStack.isEmpty || !(itemStack.item is ItemTool || itemStack.item is ItemSword || itemStack.item is ItemHoe || itemStack.item is ItemShears)) continue
            val mineSpeed: Float = BlockUtil.blockBreakSpeed(toMineBlockMaterial.getDefaultState(), itemStack)
            if (mineSpeed > fastestSpeed) {
                fastestSpeed = mineSpeed
                theSlot = i
            }
        }
        return theSlot
    }

    fun itemSlotIDinInventory(item: Item): Int {
        for (i in 0..44)
            if (mc.player.inventoryContainer.inventorySlots[i].stack.item === item) return i
        return -1
    }

    fun getClickingSlot(id: Int): Int {
        var id = id
        if (id == -1)
            return id
        else if (id < 9) {
            id += 36
            return id
        } else if (id == 39)
            id = 5
        else if (id == 38)
            id = 6
        else if (id == 37)
            id = 7
        else if (id == 36)
            id = 8
        else if (id == 40)
            id = 45
        return id
    }
}