package org.hooks.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import org.hooks.javabrains.messenger.database.DatabaseClass;
import org.hooks.javabrains.messenger.model.Message;



public class MessageService {
	
	public MessageService(){
		messages.put(1L, new Message(1, "Hello world", "Hooks"));
		messages.put(2L, new Message(2, "Hello guy", "Hooks"));
	}
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	private SortedSet<Long> free = DatabaseClass.getFree();
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesByYear(int year){
		ArrayList<Message> returnList = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()){
			cal.setTimeInMillis(message.getCreated().getTime());
			if(cal.get(Calendar.YEAR) == year){
				returnList.add(message);
			}
		}		
		return returnList;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> returnList = new ArrayList<Message>(messages.values());
		if(start+size > returnList.size()){
			return returnList;
		}
		return returnList.subList(start, start+size);
	}
	
	
	public Message getMessage(long id){
		return messages.get(id);
	}
	
	public Message addMessage(Message message){
		if(free.isEmpty()){
			message.setId(messages.size() + 1);
		}
		else{
			message.setId(free.first());
			free.remove(free.first());
		}
		
		message.setCreated(new Date());
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId()<=0){
			return null;
		}
		message.setCreated(messages.get(message.getId()).getCreated());
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		free.add(id);
		return messages.remove(id);
		
	}
}
