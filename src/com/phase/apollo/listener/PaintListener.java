package com.phase.apollo.listener;

import java.util.HashMap;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

import com.phase.apollo.cmd.CommandManager;
import com.phase.apollo.lib.PaintBall;
import com.phase.apollo.main.Apollo;

public class PaintListener implements Listener {

	private HashMap<String, Boolean> msg = new HashMap<String, Boolean>();

	@EventHandler
	public void SnowBall(ProjectileHitEvent e) {
		LivingEntity l = e.getEntity().getShooter();

		if (l instanceof Player) {
			Player p = (Player) l;
			Projectile n = e.getEntity();
			if (!(n instanceof Snowball)) {
				return;
			}

			if (!CommandManager.paintBallers.containsKey(p.getName())) {
				return;
			}

			if (p.getGameMode() != GameMode.CREATIVE) {
				p.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 1));
			}

			if (!PaintBall.containsPlayer(p.getName())) {
				if (!msg.containsKey(p.getName())) {
					p.sendMessage(Apollo.prefix
							+ "You must set a Paint Material first! Do .paint set <Material> to set a Paint Material!");
					msg.put(p.getName(), true);
				}
			}

			BlockIterator iterator = new BlockIterator(n.getWorld(), e
					.getEntity().getLocation().toVector(), n.getVelocity()
					.normalize(), 0.0D, 4);
			Block hitBlock = null;
			while (iterator.hasNext()) {
				hitBlock = iterator.next();
				if (hitBlock.getType() != Material.AIR) {
					if (hitBlock.getType() == Material.WATER) {
						hitBlock.getWorld().playEffect(hitBlock.getLocation(),
								Effect.STEP_SOUND, 1);
						Block waterAblock = hitBlock.getLocation().subtract(0, -1, 0).getBlock();
						waterAblock.setType(PaintBall.getPaintType(p.getName()));
					} else {
						hitBlock.getWorld().playEffect(hitBlock.getLocation(),
								Effect.STEP_SOUND, 1);
						hitBlock.setType(PaintBall.getPaintType(p.getName()));
					}

				}

			}
		}
	}

}
