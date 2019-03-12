package com.abc.BoardSystem.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ABC_BOARD")
public class Board {
	@Id
	@SequenceGenerator(name="abc_board_seq_gen", sequenceName="ABC_BOARD_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="abc_board_seq_gen")
	private int id;
	
	@Column
	private String content;
	
	@Column
	private int posterId;
	
	@Column
	private String tittle;
	
}
