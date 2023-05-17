package ua.lycoris.client.utils.color

import java.awt.Color

object RainbowUtil {
    fun getRainbow(speed: Int, offset: Int, s: Float): Int {
        val hue = ((System.currentTimeMillis() + offset) % speed).toFloat()
        return Color.getHSBColor(hue / speed, s, 1.0f).rgb
    }

    const val DEFAULT_COLOR_SATURATION = 0.95f
    const val DEFAULT_COLOR_BRIGHTNESS = 0.95f
    fun generateRainbowFadingColor(offset: Float, drastic: Boolean): Int {
        //long offset_ = (drastic ? 200000000L : 20000000L) * offset;
        val offset_ = ((if (drastic) 200000000L else 20000000L) * offset).toLong()
        val hue = (System.nanoTime() + offset_) / 4.0E9f % 1.0f
        return Integer.toHexString(Color.HSBtoRGB(hue, DEFAULT_COLOR_SATURATION, DEFAULT_COLOR_BRIGHTNESS)).toLong(16)
            .toInt()
    }

    fun astolfoColors(yOffset: Int, yTotal: Int): Int {
        var hue: Float
        val speed = 2900.0f
        hue = (System.currentTimeMillis() % speed.toInt().toLong()).toFloat() + ((yTotal - yOffset) * 9).toFloat()
        while (hue > speed) {
            hue -= speed
        }
        if (speed.let { hue /= it; hue }.toDouble() > 0.5) {
            hue = 0.5f - (hue - 0.5f)
        }
        return Color.HSBtoRGB(0.5f.let { hue += it; hue }, 0.5f, 1.0f)
    }

     /**
     * Applies a color via a lambda
     *
     * @param color the color
     * @param apply apply lambda
     */
    fun applyColor(color: Int, apply: ColorApply) {
        val alpha = (color shr 24 and 0xff) / 255f
        val red = (color shr 16 and 0xff) / 255f
        val green = (color shr 8 and 0xff) / 255f
        val blue = (color and 0xff) / 255f
        apply.apply(red, green, blue, alpha)
    }

    fun interface ColorApply {
        fun apply(r: Float, g: Float, b: Float, a: Float)
    }
}