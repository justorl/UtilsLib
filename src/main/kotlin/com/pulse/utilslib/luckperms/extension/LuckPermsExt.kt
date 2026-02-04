package com.pulse.utilslib.luckperms.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.luckperms.api.LuckPermsProvider
import org.bukkit.entity.Player

fun Player.getLuckPermsPrefix(): Component {
    val user = LuckPermsProvider.get().getPlayerAdapter(Player::class.java).getUser(this)
    return MiniMessage.miniMessage().deserialize(user.cachedData.metaData.prefix ?: "")
}

fun Player.getLuckPermsSuffix(): Component {
    val user = LuckPermsProvider.get().getPlayerAdapter(Player::class.java).getUser(this)
    return MiniMessage.miniMessage().deserialize(user.cachedData.metaData.suffix ?: "")
}