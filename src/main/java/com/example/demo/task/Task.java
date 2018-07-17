package com.example.demo.task;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Task {
	
	public void execute() {
		System.out.println("Task Executed... Date: "+new Date());
	}

}
