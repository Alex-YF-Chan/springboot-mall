package com.example.springboot.dao;

import com.example.springboot.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRespository extends JpaRepository<Message,Integer> {
    List<Message> findByCustomerId(String id);
}
