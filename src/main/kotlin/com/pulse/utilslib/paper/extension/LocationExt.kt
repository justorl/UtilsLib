package com.pulse.utilslib.paper.extension

import org.bukkit.Location

fun Location.behind(distance: Double): Location {
    val direction = direction
        .normalize()
        .multiply(distance)

    return this.clone().subtract(direction)
}

fun Location.forward(distance: Double): Location {
    val direction = direction
        .normalize()
        .multiply(distance)

    return this.clone().add(direction)
}

fun Location.forwardFlat(distance: Double): Location {
    val direction = direction
        .normalize()
        .setY(0)
        .multiply(distance)

    return this.clone().add(direction)
}

fun Location.above(distance: Double): Location =
    this.clone().add(0.0, distance, 0.0)

fun Location.below(distance: Double): Location =
    this.clone().add(0.0, -distance, 0.0)

fun Location.left(distance: Double): Location {
    val direction = direction
        .normalize()
        .rotateAroundY(Math.PI / 2)
        .multiply(distance)

    return this.clone().add(direction)
}

fun Location.right(distance: Double): Location {
    val direction = direction
        .normalize()
        .rotateAroundY(Math.PI / 2)
        .multiply(distance)

    return this.clone().subtract(direction)
}