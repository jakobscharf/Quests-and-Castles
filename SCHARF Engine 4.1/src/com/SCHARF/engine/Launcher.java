package com.SCHARF.engine;

public class Launcher {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 960;
	
	public Launcher() {
		Game game = new Game("SCHARF", WIDTH, HEIGHT, this);
		game.start();
	}
	
	
	
	public static void main(String[] args) {
		new Launcher();
	}
	
}
