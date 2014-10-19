package com.phase.apollo.cmd;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.phase.apollo.lib.PaintBall;
import com.phase.apollo.lib.WandSelection;
import com.phase.apollo.main.Apollo;

public class CommandManager implements Listener {

	public static HashMap<String, Boolean> paintBallers = new HashMap<String, Boolean>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void ApolloCommand(AsyncPlayerChatEvent e){
		Player p =  e.getPlayer();
		String[] m = e.getMessage().split(" ");
		String c = m[0];
		
		if(!c.startsWith(".") || !p.isOp()){
			return;
		}
		
		e.setCancelled(true);
		
	if(c.equalsIgnoreCase(".help")){
		p.sendMessage(Apollo.prefix + ".paint <toggle|set> [material]");
		p.sendMessage(Apollo.prefix + ".setwand <item>");
		p.sendMessage(Apollo.prefix + ".set <block>");
	}
		
		
	if(c.equalsIgnoreCase(".paint")){
			if (m.length == 1) {
				p.sendMessage(Apollo.prefix + ".paint <toggle|set> [material]");
				return;
			}

			if (m[1].equalsIgnoreCase("set")) {
				if (m.length == 2) {
					p.sendMessage(Apollo.prefix + ".paint <toggle|set> [material]");
					return;
				}
				Material m1 = Material.SNOW_BLOCK;
				try {
					m1 = Material.getMaterial(Integer.parseInt(m[1]));
				} catch (Exception e1) {
					m1 = Material.getMaterial(m[2].toUpperCase());
				}

				PaintBall.putPaintType(p.getName(), m1);
				p.sendMessage(Apollo.prefix + "You have specified " + ChatColor.UNDERLINE +  m1.toString().toLowerCase() + ChatColor.RESET + Apollo.blue + " as your Paint Type!");
				return;
			} else if (m[1].equalsIgnoreCase("toggle")) {
				if (paintBallers.containsKey(p.getName())) {
					paintBallers.remove(p.getName());
					System.out.println(Apollo.prefix + "Toggled state of PaintBall off for: " + p.getName());
					p.sendMessage(Apollo.prefix + "Paint Toggled Off.");
					return;
				}

				if (!paintBallers.containsKey(p.getName())) {
					paintBallers.put(p.getName(), true);
					System.out.println(Apollo.prefix + "Toggled state of PaintBall on for: " + p.getName());
					p.sendMessage(Apollo.prefix + "Paint Toggled On.");
					return;
				}
			}
		}
	if(c.equalsIgnoreCase(".setwand")){
			if (m.length == 1) {
				p.sendMessage(Apollo.prefix + ".setwand <item>");
				return;
			}
			
			
			Material m1 = Material.BLAZE_ROD;
			try {
				m1 = Material.getMaterial(Integer.parseInt(m[1]));
			} catch (Exception e1) {
				m1 = Material.getMaterial(m[1].toUpperCase());
			}
			
			if(m1.isBlock()){
				p.sendMessage(Apollo.prefix + "Wand must be an Item.");
				return;
			}
            WandSelection.setWandMaterial(m1);
			p.sendMessage(Apollo.prefix + "You have specified " + ChatColor.UNDERLINE +  m1.toString().toLowerCase() + ChatColor.RESET + Apollo.blue + " as the Wand! This will reset upon restarting the server.");
			return;
	}
	if(c.equalsIgnoreCase(".set")){
		if (m.length == 1) {
			p.sendMessage(Apollo.prefix + ".set <block>");
			return;
		}
		Material m1 = Material.STONE;
		try {
			m1 = Material.getMaterial(Integer.parseInt(m[1]));
		} catch (Exception e1) {
			m1 = Material.getMaterial(m[1].toUpperCase());
		}
		
		if(!m1.isBlock()){
			p.sendMessage(Apollo.prefix + "Material must be an Block.");
			return;
		}
		
		for(Block b : WandSelection.blocksFromTwoPoints(WandSelection.getLocation1(p), WandSelection.getLocation2(p))){
			b.setType(m1);
		}
		p.sendMessage(Apollo.prefix + "Set Blocks to " + ChatColor.UNDERLINE +  m1.toString().toLowerCase() + ChatColor.RESET + Apollo.blue + "!");
	}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
