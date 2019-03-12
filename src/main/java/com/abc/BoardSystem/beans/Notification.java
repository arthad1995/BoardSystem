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
@Table(name = "ABC_NOTIFICATION")
public class Notification {
	@Id
	@SequenceGenerator(name="abc_notification_seq_gen", sequenceName="ABC_NOTIFICATION_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="abc_notification_seq_gen")
	private int id;
	
	@Column
	private int receiverId;
	////////////RECEVIERID
	
	
	@Column
	private String content;
	
	@Column
	private Timestamp time;

	public Notification(int receiverId, String content, Timestamp time) {
		super();
		this.receiverId = receiverId;
		this.content = content;
		this.time = time;
	}

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	

}
