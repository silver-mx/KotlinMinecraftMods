package dns.mods

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Cat
import org.bukkit.entity.Ocelot
import org.bukkit.entity.Player
import org.bukkit.entity.Skeleton
import org.bukkit.plugin.java.JavaPlugin

class MobsPlugin : JavaPlugin() {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            return false
        }

        when (label) {
            "mycat" -> {
                val cat = sender.world.spawn(sender.location, Cat::class.java)
                cat.owner = sender
                cat.catType = Cat.Type.WHITE
                return true
            }

            "myskeleton" -> {
                val skeleton = sender.world.spawn(sender.location, Skeleton::class.java)
                return true
            }
        }

        return false
    }
}
