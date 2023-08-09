package dns.mods

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.lang.IllegalArgumentException

class OurWorldPlugin : JavaPlugin() {

  override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

    if (sender !is Player) {
      return false
    }

    when (label) {
      "onblock" -> {
        val loc = sender.location
        loc.y--
        val block = loc.block

        if (args.isNotEmpty()) {
          val firstArg = args.first().uppercase()
          block.type = Material.valueOf(firstArg)
        }

        sender.sendMessage("You are standing on ${block.type}")

        return true
      }
    }

    return false
  }

}
