package com.chat;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

public class ChatKeyListener implements KeyListener {

	private JTextArea input;
	private JTextArea mainArea;
	
	public ChatKeyListener(JTextArea input, JTextArea mainArea) {
		this.input = input;
		this.mainArea = mainArea;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(false);
			
			// Get the text and clear the text from the text area.
			String msg = input.getText().trim().toLowerCase();
			input.setText("");
			
			// add text to the view area.
			if(null != msg && msg.trim().length() > 0) {
				addTextToDisplay(msg, "** You ***: ");
				
				addTextToDisplay(AI.getMsg(msg), "*** ChatBot ***: ");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		input.setEditable(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Nothing goes here.
	}
	
	
	private void addTextToDisplay(String msg, String user) {
		mainArea.append(user + msg);
		mainArea.append("\n");
	}
}
