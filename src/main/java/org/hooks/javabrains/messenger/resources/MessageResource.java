package org.hooks.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hooks.javabrains.messenger.model.Message;
import org.hooks.javabrains.messenger.service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages(@QueryParam("year") int year, 
									 @QueryParam("start") int start, 
									 @QueryParam("size") int size){
		if(year > 0){
			return messageService.getAllMessagesByYear(year);
		}
		if(start > 0 && size > 0){
			return messageService.getAllMessagesPaginated(start-1, size);
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId){
		return messageService.getMessage(messageId);
	}
	
	@POST
	public Message postMessage(Message msg){
		return messageService.addMessage(msg);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message putMessage(@PathParam("messageId") long messageId, Message msg){
		msg.setId(messageId);
		return messageService.updateMessage(msg);
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("messageId") long messageId){
		return messageService.removeMessage(messageId);
	}
	
}
