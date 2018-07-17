package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/scheduler")
public class SchedulerController {
	
	@GetMapping(value="/setMinute/{minute}")
	public String setMinute(@PathVariable("minute") String minute) {
		
		return "success";
		
	}
	
	@GetMapping(value="/setHour/{hour}")
	public String setHour(@PathVariable("hour") String hour) {
		
		return "success";
		
	}

}
