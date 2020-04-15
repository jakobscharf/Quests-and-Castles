package com.SCHARF.engine.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	
	private BufferedImage image;
	private int[] pixels;
	
	public Image(String path) {
		image = null;
		try {
			image = ImageIO.read(Image.class.getResourceAsStream("/gfx/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (image == null) return;
	}
	
}
