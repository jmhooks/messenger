package org.hooks.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hooks.javabrains.messenger.database.DatabaseClass;
import org.hooks.javabrains.messenger.model.Profile;



public class ProfileService {
	
	public ProfileService(){
		//Profile(long id, String profileName, String firstName, String lastName)
		profiles.put("Hooks1", new Profile("Hooks1", "Josh", "Hooks"));
		profiles.put("Hooks2", new Profile("Hooks2", "Josh", "Hooks"));
	}
	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		if(profiles.containsKey(profile.getProfileName())){
			return null;
		}
		profile.setCreated(new Date());
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profiles.containsKey(profile.getProfileName())){
			profile.setCreated(profiles.get(profile.getProfileName()).getCreated());
			profiles.put(profile.getProfileName(), profile);
			return profile;
		}
		return null;
	}
	
	public Profile removeProfile(String profileName){
		if(profiles.containsKey(profileName)){
			return profiles.remove(profileName);
		}
		return null;
		
	}
}
