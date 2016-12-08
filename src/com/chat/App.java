package com.chat;

import java.awt.EventQueue;

public class App {
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				new ChatDialog();
			}
		};
		
		EventQueue.invokeLater(r);
	}
}
