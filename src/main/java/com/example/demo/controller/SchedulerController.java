package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.SchedulerConfig;

@RestController
@RequestMapping(value="/scheduler")
public class SchedulerController {
	
	@Autowired
	SchedulerConfig schedulerConfig;
	
	
	@GetMapping(value="/setMinute/{minute}")
	public String setMinute(@PathVariable("minute") String minute) {
		schedulerConfig.setMinute(minute);
		return "success";
		
	}
	
	@GetMapping(value="/setHour/{hour}")
	public String setHour(@PathVariable("hour") String hour) {
		schedulerConfig.setHour(hour);
		return "success";
		
	}

}
