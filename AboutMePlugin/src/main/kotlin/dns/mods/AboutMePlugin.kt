package dns.mods

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class AboutMePlugin : JavaPlugin() {

  override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

    if (!(sender is Player)) {
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
        sender.health = 20.0
        sender.foodLevel = 20
        sender.sendMessage("Tienes energia llena!!")
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

      "fast-speed" -> {
        logger.info("Before walkspeed=${sender.walkSpeed}")
        sender.walkSpeed = 1f
        logger.info("After walkspeed=${sender.walkSpeed}")
        return true
      }

      "normal-speed" -> {
        logger.info("Before walkspeed=${sender.walkSpeed}")
        sender.walkSpeed = 0.2f
        logger.info("After walkspeed=${sender.walkSpeed}")
        return true
      }

      "slow-speed" -> {
        logger.info("Before walkspeed=${sender.walkSpeed}")
        sender.walkSpeed = -1f
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
