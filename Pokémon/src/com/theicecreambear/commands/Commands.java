package com.theicecreambear.commands;

public class Commands {
	public static void executeCommand(String command) {
		if (command == null) {
			return;
		}
		if (command.equalsIgnoreCase("/exit")) {
			System.exit(-1);
		}
		if (command.contains("/loadAdminSave")) {
			String[] args = command.split(" ");
		}
	}
}