package com.abc.BoardSystem.Daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.BoardSystem.beans.Board;

public interface BoardDao extends JpaRepository<Board, Integer>{

}
