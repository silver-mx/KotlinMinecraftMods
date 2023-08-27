package dns.mods

import org.bukkit.Material
import org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class AboutMePlugin : JavaPlugin() {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) {
            return false
        }

        when (label) {
            "myname" -> {
                val myName = sender.displayName
                sender.sendMessage("Hola Markus y Palle, mi usuario es ${myName}")
                return true
            }

            "mystats" -> {
                sender.sendMessage("Food level: ${sender.foodLevel}")
                sender.sendMessage("Health: ${sender.health}")
                return true
            }

            "heal" -> {
                sender.health = sender.getAttribute(GENERIC_MAX_HEALTH)?.baseValue ?: 20.0
                sender.foodLevel = 20
                sender.sendMessage("Tienes energia llena!!")
                return true
            }

            "armor" -> {
                sender.inventory.helmet = ItemStack(Material.NETHERITE_HELMET)
                sender.inventory.boots = ItemStack(Material.NETHERITE_BOOTS)
                sender.inventory.chestplate = ItemStack(Material.NETHERITE_CHESTPLATE)
                sender.inventory.leggings = ItemStack(Material.NETHERITE_LEGGINGS)

                val sword = ItemStack(Material.NETHERITE_SWORD)
                sender.inventory.addItem(sword)
                return true
            }

            "hearts" -> {
                val numHearts = args.first().toDouble() * 2
                val attribute = sender.getAttribute(GENERIC_MAX_HEALTH)
                attribute?.baseValue = numHearts

                return true
            }

            "absortion" -> {
                try {
                    val absortionHearts = args.first().toDouble() * 2
                    sender.absorptionAmount = absortionHearts
                } catch (e: Exception) {
                    logger.severe("Error executing absortion command: ${e.message}")
                    sender.sendMessage("Error: ${e.message}")
                }
                return true
            }

            "vacio" -> {
                sender.health = 7.0
                sender.foodLevel = 7
                sender.sendMessage("Tienes energia vacia!!")
                return true
            }

            "mylocation" -> {
                val loc = sender.location
                sender.sendMessage("My location is x=${loc.x}, y=${loc.y}, z=${loc.z}")
                return true
            }

            "jump" -> {
                val loc = sender.location
                loc.y += 20
                sender.teleport(loc)
                return true
            }

            "faster-speed" -> {
                logger.info("Before walkspeed=${sender.walkSpeed}")
                if (sender.walkSpeed + 0.2f <= 1.0f) {
                    sender.walkSpeed += 0.2f
                }
                logger.info("After walkspeed=${sender.walkSpeed}")
                return true
            }

            "normal-speed" -> {
                logger.info("Before walkspeed=${sender.walkSpeed}")
                sender.walkSpeed = 0.2f
                logger.info("After walkspeed=${sender.walkSpeed}")
                return true
            }

            "slower-speed" -> {
                logger.info("Before walkspeed=${sender.walkSpeed}")
                if (sender.walkSpeed - 0.2f >= -1f) {
                    sender.walkSpeed -= 0.2f
                }
                logger.info("After walkspeed=${sender.walkSpeed}")
                return true
            }

            "level-up" -> {
                sender.level += 1
            }

            "level-down" -> {
                sender.level -= 1
            }

            "fire-ticks" -> {
                sender.fireTicks = 60
            }
        }

        return false
    }
}
