package com.SCHARF.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[120];
	private boolean[] last_keys = new boolean[120];

	public boolean isKey(int keyCode) {
		return keys[keyCode];
	}
	
	public boolean isKeyDown(int keyCode) {
		return keys[keyCode] && !last_keys[keyCode];
	}
	
	public boolean isKeyUp(int keyCode) {
		return !keys[keyCode] && last_keys[keyCode];
	}
	
	public void update() {
		last_keys = keys;
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}
}
