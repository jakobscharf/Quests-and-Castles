package com.SCHARF.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.SCHARF.engine.graphics.Image;
import com.SCHARF.engine.graphics.Window;
import com.SCHARF.engine.input.Keyboard;

public class Game implements Runnable{

		private Window window;
		private Thread thread;
		private Keyboard keyboard;
		
		private String title;
		private int width;
		private int height;
		
		private float limit = 4;
		private boolean bool;
		
		private Image image = new Image("GrassTile.png");
		
		private boolean running;
		
		public Game(String title, int width, int height, Launcher launcher) {
			this.title = title;
			this.width = width;
			this.height = height;
			
			initialize();
		}
		
		private void initialize() {
			window = new Window(title, width, height);
			keyboard = new Keyboard();
			window.addKeyListener(keyboard);
			//test = ResourceLoader.load("/textures/TileCity.png");
			running = false;
		}
		
		public void run() {
			long lastTime = System.nanoTime();
			double ticksPerSecond = 1000.0;
			double framesPerSecond = 60.0;
			double tick_ns = 1000000000.0 / ticksPerSecond;
			double frame_ns = 1000000000.0 / framesPerSecond;
			double tick_delta = 0;
			double frame_delta = 0;
			long timer = System.currentTimeMillis();
			int updates = 0;
			int frames = 0;
			
			
			while(running) {
				long now = System.nanoTime();
				tick_delta += (now - lastTime) / tick_ns;
				frame_delta += (now - lastTime) / frame_ns;
				lastTime = now;
				
				while(tick_delta >= 1) {
					tick();
					updates++;
					tick_delta--;
				}
				if (running && frame_delta >= 1) {
					render();
					frames++;
					frame_delta--;
				}
				
				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					window.setTitle(title + " | UPS: " + updates + " FPS: " + frames);
					updates = 0;
					frames = 0;
				}
				
			}
			stop();
		}
		
		private void tick() {
			if (keyboard.isKeyDown(37)) bool = !bool;
			if (keyboard.isKey(39)) bool = !bool;
			keyboard.update();
		}
		
		private void render() {
			if (!window.isBuffered()) return;
			
			window.clearScreen();
			if (bool) {
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						window.setPixel(x, y, 0xff00ff);
					}
				}
			}
			
			window.show();
		}
		
		private int mandelbrot(long x, long y) {
			double hun = 200;
			double xr = x/hun-2;
			double yr = y/hun-2;
			
			int n = 0;
			
			double x1 = xr;
			double y1 = yr;
			
			double x2 = 0;
			double y2 = 0;
			
			while (n < 150 && x1*x1 + y1*y1 <= limit) {
				x2 = x1*x1 - y1 * y1 + xr;
				y2 = 2*x1*y1 + yr;
				x1 = x2;
				y1 = y2;
				n++;
			}
			return n;
		}
		
		public synchronized void start() {
			if (running) return;
			thread = new Thread(this);
			thread.start();
			running = true;
		}
		
		public synchronized void stop() {
			if (!running) return;
			try {
				thread.join();
				running = false;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	
}
