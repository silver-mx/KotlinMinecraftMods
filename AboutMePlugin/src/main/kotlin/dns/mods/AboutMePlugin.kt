package dns.mods

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class AboutMePlugin : JavaPlugin() {

  override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

    when (label) {
      "myname" -> {
        if (sender is Player) {
          val myName = sender.displayName
          sender.sendMessage("Hola Markus y Palle, mi usuario es ${myName}")
          return true
        }
      }

      "mystats" -> {
        if (sender is Player) {
          sender.sendMessage("Food level: ${sender.foodLevel}")
          sender.sendMessage("Health: ${sender.health}")
          return true
        }
      }

      "heal" -> {
        if (sender is Player) {
          sender.health = 20.0
          sender.foodLevel = 20
          sender.sendMessage("Tienes energia llena!!")
          return true
        }
      }

      "vacio" -> {
        if (sender is Player) {
          sender.health = 7.0
          sender.foodLevel = 7
          sender.sendMessage("Tienes energia vacia!!")
          return true
        }
      }
    }




    return false
  }
}
