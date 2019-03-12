package com.abc.BoardSystem.beans;

import java.sql.Timestamp;

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
@Table(name = "ABC_REPLYS")
public class Reply {
	@Id
	@SequenceGenerator(name="abc_replys_seq_gen", sequenceName="ABC_REPLYS_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="abc_replys_seq_gen")
	private int id;
	
	@Column
	private String content;
	
	@Column
	private int senderId;
	
	@Column
	private int boardId;
	
	@Column
	private int replyToUserId;
	
	@Column
	private Timestamp time;
	
	
	
}
