package com.phase.apollo.main;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.phase.apollo.cmd.CommandManager;
import com.phase.apollo.lib.WandSelection;
import com.phase.apollo.listener.PaintListener;
import com.phase.apollo.listener.SightWand;

public class Apollo extends JavaPlugin {

	public static String prefix = ChatColor.RED + "Ap" + ChatColor.DARK_RED
			+ "ol" + ChatColor.GOLD + "lo" + ChatColor.GRAY + " ↠ "
			+ ChatColor.AQUA + "" + ChatColor.BOLD + " ";
	public static String blue = ChatColor.AQUA + "" + ChatColor.BOLD;
	public static String bad = ChatColor.RESET + "" + ChatColor.RED + ""
			+ ChatColor.BOLD;

	public void onEnable() {
		// Listeners
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PaintListener(), this);
		pm.registerEvents(new CommandManager(), this);
		pm.registerEvents(new SightWand(), this);
		pm.registerEvents(new WandSelection(), this);
	}

	public void onDisable() {
		/*
		 * TODO Find use for this.
		 */
	}

}
