package dns.mods

import org.bukkit.DyeColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Cat
import org.bukkit.entity.Player
import org.bukkit.entity.Skeleton
import org.bukkit.entity.Wolf
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
                val numSkeletons = if (args.isNotEmpty()) args.first().trim().toInt() else 1
                (0..numSkeletons).forEach { sender.world.spawn(sender.location, Skeleton::class.java) }

                return true
            }

            "mydogs" -> {
                var numDogs = if (args.isNotEmpty()) args.first().trim().toInt() else 1
                numDogs = if (numDogs > 100) 100 else numDogs

                //FIXME: Count the number of dogs assigned to the player before adding more.

                (0..numDogs).forEach {
                    val dog = sender.world.spawn(sender.location, Wolf::class.java)
                    dog.owner = sender
                    val numColors = DyeColor.entries.size
                    dog.collarColor = DyeColor.entries.toTypedArray()[it % numColors]
                }
                return true;
            }

            "killmydogs" -> {
                var numDogs = if (args.isNotEmpty()) args.first().trim().toInt() else 1
                sender.getNearbyEntities(100.0, 100.0, 100.0).subList(0, numDogs)
                    .filter { it is Wolf && it.owner == sender }.forEach {dog ->
                        sender.world.strikeLightning(dog.location)
                    }

                return true;
            }
        }

        return false
    }
}
