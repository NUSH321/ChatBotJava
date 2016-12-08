package com.chat;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatDialog extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPnl = new JPanel();
	private JTextArea displayArea = new JTextArea(20, 50);
	private JTextArea inputArea = new JTextArea(2, 50);
	private JScrollPane scrollPane = new JScrollPane(displayArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	public ChatDialog() {
		this.setTitle("ChatBot Tutorial");
		this.setSize(600, 420);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		displayArea.setEditable(false);
		
		// add components to panel.
		mainPnl.add(scrollPane);
		mainPnl.add(inputArea);
		
		// add mainPnl component to frame.
		this.add(mainPnl);
		
		mainPnl.setBackground(new Color(59, 89, 152));
		
		// add keylistener event to inputArea.
		inputArea.addKeyListener(new ChatKeyListener(inputArea, displayArea));
		
		this.setVisible(true);
	}
}
