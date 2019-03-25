package com.abc.BoardSystem.services;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.abc.BoardSystem.Daos.NotificationDao;
import com.abc.BoardSystem.beans.Notification;
import com.abc.BoardSystem.http.Response;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class NotificationService {
	@Autowired
	private NotificationDao nd;
	   @Autowired
	    private JmsTemplate jmsQueueTemplate;
	public Response<String> sendNotification(Notification notification){
		
		
//		try {
//			nd.save(notification);
//		}
//		catch(Exception e) {
//			return new Response<>(false,e.getMessage());
//		}

	
		ObjectMapper mapper = new ObjectMapper();
		
		    // convert user object to json string and return it 
		    try {
		    	final String	s= mapper.writeValueAsString(notification);
		    	
		    	 jmsQueueTemplate.send("findUsername", new MessageCreator() {
			            @Override
			            public Message createMessage(Session session) throws JMSException {
			                return session.createObjectMessage(s);
			            }
			        });
		    	 
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		return new Response<>(true);
	}
	
	
	
	@JmsListener(destination = "sendUsernme", containerFactory = "sendNotification")
	public void receive(Message msg) throws JMSException {
		System.out.println("********  Getting MSG  ***********");
		System.out.println(msg);
		ObjectMessage objectMessage = (ObjectMessage) msg;
		String s = (String) objectMessage.getObject();
		
		
		try {
			Notification notification = new ObjectMapper().readValue(s, Notification.class);		
		
			nd.save(notification);
		
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	public Response<List<Notification>> getNotifications(int id){
		return new Response<>(true,nd.findByReceiverId(id));
	}
	

}
