package trplugins.menu.module.internal.hook.impl

import org.black_ixx.playerpoints.PlayerPoints
import org.black_ixx.playerpoints.PlayerPointsAPI
import org.bukkit.OfflinePlayer
import trplugins.menu.module.internal.hook.HookAbstract

/**
 * @author Arasple
 * @date 2021/1/26 22:18
 */
class HookPlayerPoints : HookAbstract() {

    private val playerPointsAPI: PlayerPointsAPI? = if (isHooked) (plugin as PlayerPoints).api else null
        get() {
            if (field == null) reportAbuse()
            return field
        }

    fun getPoints(player: OfflinePlayer): Int {
        return playerPointsAPI?.look(player.uniqueId) ?: -1
    }

    fun setPoints(player: OfflinePlayer, amount: Int) {
        playerPointsAPI?.set(player.uniqueId, amount)
    }

    fun hasPoints(player: OfflinePlayer, amount: Int): Boolean {
        return getPoints(player) >= amount
    }

    fun addPoints(player: OfflinePlayer, amount: Int) {
        playerPointsAPI?.give(player.uniqueId, amount)
    }

    fun takePoints(player: OfflinePlayer, amount: Int) {
        playerPointsAPI?.take(player.uniqueId, amount)
    }
}