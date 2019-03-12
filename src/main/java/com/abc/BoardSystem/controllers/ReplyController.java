package com.abc.BoardSystem.controllers;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.BoardSystem.beans.Notification;
import com.abc.BoardSystem.beans.Reply;
import com.abc.BoardSystem.http.Response;
import com.abc.BoardSystem.services.NotificationService;
import com.abc.BoardSystem.services.ReplyService;

@RestController
@RequestMapping("/replys")
public class ReplyController {
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private ReplyService replyService;
	
	@PostMapping
	public Response<String> postReply(@RequestBody Reply reply){
		Response<List<Integer>> res =replyService.postReply(reply);
		if(!res.isSuccess()) {
			return new Response<>(false);
		}
			List<Integer> list = res.getPayload();
		   Timestamp ts = new Timestamp(System.currentTimeMillis());
		    long timestamp = ((ts.getTime()) / 1000) * 1000;
		
		if(list.size()==2) {
			
			    /////////////need to get username via JMS use reply sender
			notificationService.sendNotification(new Notification(list.get(0), "someone replys to your comment", new Timestamp(timestamp)));
			notificationService.sendNotification(new Notification(list.get(1), "someone replys to your post", new Timestamp(timestamp)));
		}
		else if(list.size()==1) {
			notificationService.sendNotification(new Notification(list.get(0), "someone replys to your comment", new Timestamp(timestamp)));
		}
		return new Response<>(true);
	}
	
	@PutMapping
	public Response<String> modifyReply(@RequestBody Reply reply){
		return replyService.modifyReply(reply);
	}
	
	
	@DeleteMapping("/{id}")
	public Response<String> deleteReply(@PathVariable int id){
		return replyService.deleteReply(id);
	}
	
	@GetMapping("/{id}")
	public Response<List<Reply>> getBoardReply(@PathVariable int id){
		return replyService.getBoardReply(id);
	}
	
	
}
