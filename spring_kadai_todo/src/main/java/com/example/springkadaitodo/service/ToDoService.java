package com.example.springkadaitodo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.repository.ToDoRepository;

@Service
public class ToDoService {
	private final ToDoRepository todoRepository;
	
	public ToDoService(ToDoRepository todoRepository) {
		this.todoRepository=todoRepository;
	}
	
	public void createToDo(String title,String priority, String status) {
		if(title==null || title.isEmpty()) {
			throw new IllegalArgumentException("タイトルを入力してください。");
		}
		
		if(!todoRepository.findByTitle(title).isEmpty()) {
			throw new IllegalArgumentException("そのタイトルは既に使用されています。");
		}
		
		ToDo todo = new ToDo();
		todo.setTitle(title);
		todo.setPriority(priority);
		todo.setStatus(status);
		
		todoRepository.save(todo);
	}
	
	public List<ToDo> getAllToDo(){
		return todoRepository.findAll();
	}
}
