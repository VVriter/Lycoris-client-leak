package ua.lycoris.client.utils.render2d.animation

import java.lang.StrictMath.pow

/**
 * @author PunCakeCat/Kristofer
 */
enum class Easing(val easing: (Double) -> Double) {

    Linear({ x -> x }),

    InQuint({ x -> x * x * x * x * x }),

    OutQuint({ x -> 1 - pow(1 - x, 5.0) }),

    InOutQuint({ x -> if(x < 0.5) 16 * x * x * x * x * x else 1 - pow(-2 * x + 2, 5.0) / 2 }),

    InCubic({ x -> x * x * x }),

    OutCubic({ x -> 1 - pow(1 - x, 3.0) }),

    InOutCubic({ x -> if(x < 0.5) 4 * x * x * x else 1 - pow(-2 * x + 2, 3.0) / 2 }),

    InQuart({ x -> x * x * x * x}),

    OutQuart({ x -> 1 - pow(1 - x, 4.0)}),

    InOutQuart({ x -> if (x < 0.5) 8 * x * x * x * x else 1 - pow(-2 * x + 2, 4.0) / 2})
}