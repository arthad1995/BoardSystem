package com.abc.BoardSystem.Daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.BoardSystem.beans.Reply;

public interface ReplyDao extends JpaRepository<Reply, Integer>{

	public List<Reply> findByBoardId(int id);
}
