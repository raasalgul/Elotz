package com.elotz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elotz.bean.DailyUpdate;
import com.elotz.bean.DailyUpdateGet;
import com.elotz.bean.DailyUpdatePost;
import com.elotz.exception.GenericException;
import com.elotz.exception.TopicNotFound;
import com.elotz.service.DailyUpdateService;

@CrossOrigin
@RestController
@RequestMapping("/Elotz-home")
public class Controller {
	@Autowired
	DailyUpdateService dailyUpdateService;
	DailyUpdateGet dailyTask=new DailyUpdateGet();
	List<DailyUpdate> dailyTaskList=new ArrayList<>();
	@RequestMapping("/dailyUpdate/topic")
	public List<String> getDailyUpdate()
	{
		//generate todays date pass that to the database and get the information of daily update.
		//Store the value of daily topics in redux when the page is loaded.
		//Once the topic is selected from drop down	 based on the topic get the daily task.
		return dailyUpdateService.getDailyUpdateService();
	}
	@GetMapping("/dailyUpdate/task/{topic}")
	public DailyUpdateGet getDailyUpdateTasks(@PathVariable String topic) throws TopicNotFound
	{
		return dailyUpdateService.getDailyUpdateTasksService(topic);
	}
	@PostMapping("dailyUpdate/post")
	public String postDailyUpdate(@RequestBody DailyUpdatePost dailyUpdatePost) throws GenericException
	{
		return dailyUpdateService.postDailyUpdateService(dailyUpdatePost);
	}
}