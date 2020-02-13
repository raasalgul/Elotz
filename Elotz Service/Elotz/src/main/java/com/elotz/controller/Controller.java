package com.elotz.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elotz.bean.DailyUpdatePost;
import com.elotz.exception.GenericException;
import com.elotz.exception.TopicNotFound;

@RestController
@RequestMapping("/Elotz-home")
public class Controller {
	List<String> dailyTopics;
	Map<String,List> dailyTasks=new HashMap<>();
	@Bean
	public void initializeData()
	{
		dailyTopics=new ArrayList<>(Arrays.asList("React","Java","Spring"));
		dailyTopics.add("sathish");
		List<String> react=Arrays.asList("Loops","Variables","Hooks");
		dailyTasks.put("React", react);
	}
	@RequestMapping("/dailyUpdate/topic")
	public List<String> getDailyUpdate()
	{
		//generate todays date pass that to the database and get the information of daily update.
		//Store the value of daily topics in redux when the page is loaded.
		//Once the topic is selected from drop down based on the topic get the daily task.
		System.out.println(dailyTopics.get(0));
		return dailyTopics;
	}
	@GetMapping("/dailyUpdate/task/{topic}")
	public List getDailyUpdateTasks(@PathVariable String topic) throws TopicNotFound
	{
		if(dailyTasks.containsKey(topic))
		return dailyTasks.get(topic);
		else
			throw new TopicNotFound("Failure","Given Topic is not found");
	}
	@PostMapping("dailyUpdate/post")
	public String postDailyUpdate(@RequestBody DailyUpdatePost dailyUpdatePost) throws GenericException
	{
		try {
			dailyTopics.add(dailyUpdatePost.getTask());
			if(dailyTasks.containsKey(dailyUpdatePost.getTask()))
			{
				List<String> temp=dailyTasks.get(dailyUpdatePost.getTask());
				temp.add( dailyUpdatePost.getTopic());
				dailyTasks.put(dailyUpdatePost.getTask(),temp);
			}
			else
			{
				List<String> temp=new ArrayList<>();
				temp.add( dailyUpdatePost.getTopic());
				dailyTasks.put(dailyUpdatePost.getTask(),temp);
			}
			return "success";
		}
		catch(Exception e)
		{
			System.out.println(e);
			throw new GenericException("Failure",e.toString());
		}
	}
}
