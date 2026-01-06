package com.pulse.utilsLib.paper.extensions

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

fun Location.getInRadius(radius: Int): MutableList<Location> = getInRadius(radius, radius, radius)

fun Location.getInRadius(radiusX: Int, radiusY: Int, radiusZ: Int): MutableList<Location> {
    val world = world ?: return mutableListOf()

    val sizeX = radiusX * 2 + 1
    val sizeY = radiusY * 2 + 1
    val sizeZ = radiusZ * 2 + 1

    val locs = ArrayList<Location>(sizeX * sizeY * sizeZ)

    for (x in -radiusX..radiusX)
        for (y in -radiusY..radiusY)
            for (z in -radiusZ..radiusZ)
                locs.add(
                    Location(world, blockX + x.toDouble(), blockY + y.toDouble(), blockZ + z.toDouble())
                )

    return locs
}

fun Block.getBlocksNearby(radius: Int, type: Material? = null): List<Block> = location.getBlocksNearby(radius, type)

fun Location.getBlocksNearby(radius: Int, type: Material? = null): List<Block> {
    val world = world ?: return emptyList()
    val blocks = mutableListOf<Block>()

    for (x in -radius..radius)
        for (y in -radius..radius)
            for (z in -radius..radius) {
                val block = world.getBlockAt(blockX + x, blockY + y, blockZ + z)
                if (type == null || block.type == type) blocks.add(block)
            }

    return blocks
}
