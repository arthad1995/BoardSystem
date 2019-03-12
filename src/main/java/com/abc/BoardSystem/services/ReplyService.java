package com.abc.BoardSystem.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.BoardSystem.Daos.BoardDao;
import com.abc.BoardSystem.Daos.ReplyDao;
import com.abc.BoardSystem.beans.Reply;
import com.abc.BoardSystem.http.Response;


@Service
public class ReplyService {
	@Autowired
	private ReplyDao rd;
	
	@Autowired
	private BoardDao bd;
	
	//need to send notification in controller need to send to board poster and reply to
	public Response<List<Integer>> postReply(Reply reply){
		ArrayList<Integer> replyList = new ArrayList<>();
		try {
			  Timestamp ts = new Timestamp(System.currentTimeMillis());
			    long timestamp = ((ts.getTime()) / 1000) * 1000;
			    reply.setTime(new Timestamp(timestamp));
			rd.save(reply);
		}
		catch(Exception e) {
			return new Response<>(false,e.getMessage(),null);
		}
		try {
			
			replyList.add(reply.getReplyToUserId());

			if(reply.getReplyToUserId()!=bd.findById(reply.getBoardId()).get().getPosterId())
				replyList.add(bd.findById(reply.getBoardId()).get().getPosterId());
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new Response<>(false,e.getMessage(),replyList);
		}
		return new Response<>(true,replyList);
	}
	
	public Response<String> modifyReply(Reply reply){
		try {
			
			Reply b = rd.findById(reply.getId()).get();
		
			
		
			if(reply.getContent()!=null) {
				b.setContent(reply.getContent());
			}
			
			rd.save(b);
		}
		catch(Exception e) {
			return new Response<String>(false, e.getMessage());
		}
		return new Response<>(true);
	}
	public Response<String> deleteReply(int id){
		try {
			rd.deleteById(id);
		}
		catch(Exception e) {
			return new Response<String>(false, e.getMessage());
		}
		return new Response<>(true);
	}
	
	
	public Response<List<Reply>> getBoardReply(int id){
		List<Reply> b= new ArrayList<>();
		try {
			b=rd.findByBoardId(id);
		}
		catch(Exception e) {
			return new Response<List<Reply>>(false, b);
		}
		return new Response<>(true,b);
	}
	
	
}
