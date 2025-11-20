package com.pulse.utilsLib.plugin.bukkit

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace

fun Material.fire(): Material {
    return when (this) {
        Material.IRON_ORE,
        Material.DEEPSLATE_IRON_ORE -> Material.IRON_INGOT
        Material.GOLD_ORE,
        Material.DEEPSLATE_GOLD_ORE -> Material.GOLD_INGOT
        Material.COPPER_ORE,
        Material.DEEPSLATE_COPPER_ORE -> Material.COPPER_INGOT
        Material.ANCIENT_DEBRIS -> Material.NETHERITE_SCRAP
        Material.COBBLESTONE -> Material.STONE
        Material.STONE -> Material.STONE

        Material.BEEF -> Material.COOKED_BEEF
        Material.PORKCHOP -> Material.COOKED_PORKCHOP
        Material.CHICKEN -> Material.COOKED_CHICKEN
        Material.MUTTON -> Material.COOKED_MUTTON
        Material.RABBIT -> Material.COOKED_RABBIT
        Material.COD -> Material.COOKED_COD
        Material.SALMON -> Material.COOKED_SALMON
        Material.POTATO -> Material.BAKED_POTATO

        Material.SAND,
        Material.RED_SAND -> Material.GLASS

        Material.CLAY_BALL -> Material.BRICK
        Material.CACTUS -> Material.GREEN_DYE

        Material.OAK_LOG,
        Material.SPRUCE_LOG,
        Material.BIRCH_LOG,
        Material.JUNGLE_LOG,
        Material.ACACIA_LOG,
        Material.DARK_OAK_LOG,
        Material.MANGROVE_LOG,
        Material.CHERRY_LOG,
        Material.CRIMSON_STEM,
        Material.WARPED_STEM -> Material.CHARCOAL

        else -> this
    }
}

fun Block.getShift(face: BlockFace): Location {
    return this.location.add(face.direction)
}

fun Location.getInRadius(radius: Int): MutableList<Location> {
    val locs = mutableListOf<Location>()
    val world = this.world ?: return locs

    for (x in -radius..radius) {
        for (y in -radius..radius) {
            for (z in -radius..radius) {
                val loc = Location(
                    world,
                    this.blockX + x.toDouble(),
                    this.blockY + y.toDouble(),
                    this.blockZ + z.toDouble()
                )

                locs.add(loc)
            }
        }
    }
    return locs
}