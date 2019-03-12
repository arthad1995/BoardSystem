package com.abc.BoardSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.BoardSystem.beans.Notification;
import com.abc.BoardSystem.http.Response;
import com.abc.BoardSystem.services.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
	@Autowired
	private NotificationService notificationservice;
	
	
	@GetMapping("/{id}")
	public Response<List<Notification>> getNotifications(@PathVariable int id){
		return notificationservice.getNotifications(id);
	}
}
