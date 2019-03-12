package com.abc.BoardSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.abc.BoardSystem.Daos.NotificationDao;
import com.abc.BoardSystem.beans.Notification;
import com.abc.BoardSystem.http.Response;


@Service
public class NotificationService {
	@Autowired
	private NotificationDao nd;
	
	public Response<String> sendNotification(Notification notification){
		try {
			nd.save(notification);
		}
		catch(Exception e) {
			return new Response<>(false,e.getMessage());
		}
		return new Response<>(true);
	}
	
	public Response<List<Notification>> getNotifications(int id){
		return new Response<>(true,nd.findByReceiverId(id));
	}
}
