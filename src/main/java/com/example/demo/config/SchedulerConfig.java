package com.example.demo.config;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronSequenceGenerator;

import com.example.demo.task.Task;

@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

	@Autowired
	Task myTask;

	@Value("${default.scheduler.hour}")
	String hour;

	@Value("${default.scheduler.minute}")
	String minute;

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(100);
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
		taskRegistrar.addTriggerTask(new Runnable() {
			@Override
			public void run() {
				myTask.execute();
			}
		}, new Trigger() {
			/**
			 * Default: Scheduler Will run ever
			 */
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				String cronExpression = "0 "+getMinute()+" "+getHour()+" * * *";
				System.out.println("CronExpression: "+cronExpression);
				CronSequenceGenerator generator = new CronSequenceGenerator(cronExpression);
				Date nextExecutionDate = generator.next(new Date());
				System.out.println("Next Execution Date: "+nextExecutionDate);
				return nextExecutionDate;
			}
		});
	}
}
