package com.phase.apollo.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.phase.apollo.main.Apollo;

public class WandSelection implements Listener {
	// Hashmap of the locations. Player Locations.
	static HashMap<String, String> locations = new HashMap<String, String>();

	private static String splitter = ",";
	private static Material m = Material.BLAZE_ROD;

	// Creates the selection.
	@EventHandler
	public void createSelection(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() != m)
			return;

		if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			p.sendMessage(Apollo.prefix + "Location 1 saved!");
			setLocation1(p, e.getClickedBlock().getLocation());
		} else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			p.sendMessage(Apollo.prefix + "Location 2 saved!");
			setLocation2(p, e.getClickedBlock().getLocation());
		}
	}

	// Gets location 1.
	public static Location getLocation1(Player p) {
		try {
			String ls = locations.get(p.getName());
			String[] array = ls.split(splitter);

			World w = Bukkit.getWorld(array[0]);
			int x = Integer.parseInt(array[1]);
			int y = Integer.parseInt(array[2]);
			int z = Integer.parseInt(array[3]);
			Location loc = new Location(w, x, y, z);
			return loc;
		} catch (Exception e) {
			e.printStackTrace();
			p.sendMessage(Apollo.prefix
					+ ChatColor.RESET
					+ ""
					+ ChatColor.RED
					+ ""
					+ ChatColor.BOLD
					+ "There was a problem with the location 1, please set new ones. The old ones were cleared");
			return null;
		}
	}

	// Sets Location 1
	public static void setLocation1(Player p, Location l) {
		String ls = locations.get(p.getName());
		String[] array = ls.split(splitter);
		locations.put(p.getName(),
				l.getWorld().getName() + splitter + l.getBlockX() + splitter
						+ l.getBlockY() + splitter + l.getBlockZ() + splitter
						+ "||" + splitter + array[5] + splitter + array[6]
						+ splitter + array[7] + splitter + array[8]);
	}

	// Gets location 2.
	public static Location getLocation2(Player p) {
		try {
			String ls = locations.get(p.getName());
			String[] array = ls.split(splitter);

			World w = Bukkit.getWorld(array[5]);
			int x = Integer.parseInt(array[6]);
			int y = Integer.parseInt(array[7]);
			int z = Integer.parseInt(array[8]);
			Location loc = new Location(w, x, y, z);
			return loc;
		} catch (Exception e) {
			e.printStackTrace();
			p.sendMessage(Apollo.prefix
					+ ChatColor.RESET
					+ ""
					+ ChatColor.RED
					+ ""
					+ ChatColor.BOLD
					+ "There was a problem with the location 2, please set new ones. The old ones were cleared");
			return null;
		}
	}

	public static void setLocation2(Player p, Location l) {
		String ls = locations.get(p.getName());
		String[] array = ls.split(splitter);
		locations.put(p.getName(), array[0] + splitter + array[1] + splitter
				+ array[2] + splitter + array[3] + splitter + "||" + splitter
				+ l.getWorld().getName() + splitter + l.getBlockX() + splitter
				+ l.getBlockY() + splitter + l.getBlockZ());
	}

	// Sets the Material of the wand!
	public static void setWandMaterial(Material wandType) {
		m = wandType;
	}

	// Gets a list of all the blocks.
	public static List<Block> blocksFromTwoPoints(Location loc1, Location loc2) {
		List<Block> blocks = new ArrayList<Block>();

		int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX()
				: loc1.getBlockX());
		int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2
				.getBlockX() : loc1.getBlockX());

		int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY()
				: loc1.getBlockY());
		int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2
				.getBlockY() : loc1.getBlockY());

		int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ()
				: loc1.getBlockZ());
		int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2
				.getBlockZ() : loc1.getBlockZ());

		for (int x = bottomBlockX; x <= topBlockX; x++) {
			for (int z = bottomBlockZ; z <= topBlockZ; z++) {
				for (int y = bottomBlockY; y <= topBlockY; y++) {
					Block block = loc1.getWorld().getBlockAt(x, y, z);

					blocks.add(block);
				}
			}
		}

		return blocks;
	}

	// Cancel block breaks from the wand.
	@EventHandler
	public void breakBlock(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() == m) {
			e.setCancelled(true);
			return;
		}
	}

}