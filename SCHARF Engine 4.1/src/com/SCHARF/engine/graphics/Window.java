package com.SCHARF.engine.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = -7369219131772047142L;

	private JFrame frame;
	private BufferedImage image;
	private int[] pixels;
	
	private String title;
	private int width;
	private int height;

	public Window(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		
		initializeFrame();
	}
	
	private void initializeFrame() {
		frame = new JFrame(title);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(22, 22);
		frame.setVisible(true);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.add(this);
		frame.pack();
	}
	
	public boolean isBuffered() {
		BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(3);
			return false;
		}
		return true;
	}
	
	public void clearScreen() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x000000;
		}
	}
	
	public void setPixel(int x, int y, int color) {
		if (0 <= x && x < width && 0 <= y && y < height) pixels[x + width * y] = color;
	}
	
	public void show() {
		BufferStrategy buffer = getBufferStrategy();
		Graphics g = buffer.getDrawGraphics();
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
		g.drawImage(image, 0, 0, width, height, null);
		
		g.dispose();
		buffer.show();
	}

	public void setTitle(String title) {
		this.title = title;
		frame.setTitle(title);
	}
}
