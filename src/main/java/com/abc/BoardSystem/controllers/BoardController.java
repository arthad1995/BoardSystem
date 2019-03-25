package com.abc.BoardSystem.controllers;

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

import com.abc.BoardSystem.beans.Board;
import com.abc.BoardSystem.http.Response;
import com.abc.BoardSystem.services.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping
	public Response<String> addBoard(@RequestBody Board board){
		
		return boardService.addBoard(board);
	}
	
	@PutMapping
	public Response<String> updateBoard(@RequestBody Board board){
		return boardService.updateBoard(board);
	}
	
	@GetMapping
	public Response<List<Board>> getAllBoard(){
		return boardService.getAllBoard();
	}
	
	
	@DeleteMapping("/{id}")
	public Response<String> deleteBoard(@PathVariable int id){
		return boardService.deleteBoard(id);
	}
	
	@GetMapping("/{id}")
	public Response<Board> getBoard(@PathVariable int id){
		return boardService.getBoard(id);
	}

}
