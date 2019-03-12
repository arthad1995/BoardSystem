package com.abc.BoardSystem.Daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.BoardSystem.beans.Notification;

public interface NotificationDao extends JpaRepository<Notification, Integer>{
	public List<Notification> findByReceiverId(int id);
}
