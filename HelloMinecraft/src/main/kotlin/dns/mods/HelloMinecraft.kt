package dns.mods

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin


class HelloMinecraft : JavaPlugin() {

  override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
    logger.info { "Calling the plugin" }

    if (label == "hello") {
      server.broadcastMessage("Hello Minecraft plugin!!")
      return true
    }
    return false
  }

}
