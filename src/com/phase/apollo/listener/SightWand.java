package com.phase.apollo.listener;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.phase.apollo.lib.PaintBall;

public class SightWand implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractBlock(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if (p.getItemInHand().getType() == Material.STICK) {
			Block b = p.getTargetBlock(null, 200);
			if (PaintBall.containsPlayer(p.getName())) {
				b.setType(PaintBall.getPaintType(p.getName()));
				b.getWorld().playEffect(b.getLocation(),
						Effect.STEP_SOUND, 1);
			}
		}
	}

}
