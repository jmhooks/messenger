package org.hooks.javabrains.messenger.database;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hooks.javabrains.messenger.model.Message;
import org.hooks.javabrains.messenger.model.Profile;

public class DatabaseClass {
	
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	private static SortedSet<Long> free = new TreeSet<Long>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	
	public static SortedSet<Long> getFree() {
		return free;
	}
}
