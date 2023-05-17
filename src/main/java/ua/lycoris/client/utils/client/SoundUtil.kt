package ua.lycoris.client.utils.client

import net.minecraft.client.Minecraft
import net.minecraft.util.ResourceLocation
import java.io.BufferedInputStream
import java.io.InputStream
import javax.sound.sampled.AudioSystem

object SoundUtil {
    //Util to play sounds in minecraft | Using:  SoundUtill.playSound(new ResourceLocation("audio/file.wav"));
    fun playSound(rl: ResourceLocation?) {
        Thread {
            try {
                val clip = AudioSystem.getClip()
                val bufferedInputStream: InputStream =
                    BufferedInputStream(Minecraft.getMinecraft().resourceManager.getResource(rl).inputStream)
                val inputStream = AudioSystem.getAudioInputStream(
                    bufferedInputStream
                )
                clip.open(inputStream)
                clip.start()
            } catch (e: Exception) {
                System.err.println(e)
            }
        }.start()
    }
}