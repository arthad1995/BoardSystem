package com.abc.BoardSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.BoardSystem.Daos.BoardDao;
import com.abc.BoardSystem.beans.Board;
import com.abc.BoardSystem.beans.Notification;
import com.abc.BoardSystem.http.Response;



@Service
public class BoardService {
	@Autowired
	private BoardDao bd;
	
	public Response<String> addBoard(Board board){
		try {
			bd.save(board);
		}
		catch(Exception e) {
			return new Response<>(false,e.getMessage());
		}
		return new Response<>(true);
	}
	
	public Response<String> updateBoard(Board board){
		try {
			
			Board b = bd.findById(board.getId()).get();
		
			if(board.getTittle()!=null) {
				b.setTittle(board.getTittle());
			}
		
			if(board.getContent()!=null) {
				b.setContent(board.getContent());;
			}
			
			bd.save(b);
		}
		catch(Exception e) {
			return new Response<String>(false, e.getMessage());
		}
		return new Response<>(true);
	}
	public Response<String> deleteBoard(int id){
		try {
			bd.deleteById(id);
		}
		catch(Exception e) {
			return new Response<String>(false, e.getMessage());
		}
		return new Response<>(true);
	}
	
	
	public Response<Board> getBoard(int id){
		Board b= null;
		try {
			b=bd.findById(id).get();
		}
		catch(Exception e) {
			return new Response<Board>(false, b);
		}
		return new Response<>(true,b);
	}
	
	public Response<List<Board>> getAllBoard(){
		List<Board> b = null;
		try {
			b=bd.findAll();
		}
		catch(Exception e) {
			return new Response<List<Board>>(false, b);
		}
		return new Response<>(true,b);
	}
	
}
