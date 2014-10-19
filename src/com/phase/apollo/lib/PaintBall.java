package com.phase.apollo.lib;

import java.util.HashMap;
import org.bukkit.Material;

public class PaintBall {

	public static PaintBall getInstance() {
		return new PaintBall();
	}

	private static HashMap<String, Material> paintType = new HashMap<String, Material>();

	public static Material getPaintType(String s) {
		return paintType.get(s);
	}

	public static void putPaintType(String p, Material m) {
		paintType.put(p, m);
	}

	public static boolean containsPlayer(String s) {
		if (paintType.containsKey(s) && paintType.get(s) != null)
			return true;
		else
			return false;
	}

}