package com.elotz.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elotz.bean.DailyUpdateGet;
import com.elotz.bean.DailyUpdatePost;
import com.elotz.exception.GenericException;
import com.elotz.exception.TopicNotFound;

@RestController
@RequestMapping("/Elotz-home")
public class Controller {
	DailyUpdateGet dailyTask=new DailyUpdateGet();
	List<DailyUpdateGet> dailyTaskList=new ArrayList<>();
	@Bean
	public void initializeData()
	{
		dailyTaskList.add(new DailyUpdateGet("React",Arrays.asList("Loops","Variables","Hooks"),Arrays.asList("4","3","10")));
		dailyTaskList.add(new DailyUpdateGet("Java",Arrays.asList("Loops","Variables","Oops"),Arrays.asList("4","3","10")));
		//dailyTaskList.add(new DailyUpdateGet("Spring",null,null));
	}
	@RequestMapping("/dailyUpdate/topic")
	public List<String> getDailyUpdate()
	{
		//generate todays date pass that to the database and get the information of daily update.
		//Store the value of daily topics in redux when the page is loaded.
		//Once the topic is selected from drop down	 based on the topic get the daily task.
		System.out.println(dailyTaskList.get(0).getTopic());
		return dailyTaskList.stream().map(task->task.getTopic()).collect(Collectors.toList());
	}
	@GetMapping("/dailyUpdate/task/{topic}")
	public DailyUpdateGet getDailyUpdateTasks(@PathVariable String topic) throws TopicNotFound
	{
		for(DailyUpdateGet dailyTopic:dailyTaskList)
			if(dailyTopic.topic.equalsIgnoreCase(topic))
				return dailyTopic;
		throw new TopicNotFound("Failure","Given Topic is not found");
	}
	@PostMapping("dailyUpdate/post")
	public String postDailyUpdate(@RequestBody DailyUpdatePost dailyUpdatePost) throws GenericException
	{
		try {
			DailyUpdateGet dailyUpdate = new DailyUpdateGet();
			if(dailyTaskList.stream().anyMatch(dailyTask->dailyTask.getTopic().equalsIgnoreCase(dailyUpdatePost.getTopic())))
			{
				System.out.println("Inside existing topic");
				for(DailyUpdateGet update:dailyTaskList)
				{
					if(update.getTopic().equalsIgnoreCase(dailyUpdatePost.getTopic()))
					{
						dailyUpdate=update;
						break;
					}
				}
				if(dailyTaskList.stream().anyMatch(dailyTask->dailyTask.getTasks().contains(dailyUpdatePost.getTask())))
				{
					System.out.println("Inside existing task");
					dailyTaskList.remove(dailyUpdate);
					int presentTask=-1;
					for(String task:dailyUpdate.tasks)
					{
						presentTask++;
						if(task.equalsIgnoreCase(dailyUpdatePost.getTask()))
						{
							break;
						}
					}
					ArrayList<String> listTask=new ArrayList<>(dailyUpdate.getTasks());
					ArrayList<String> listTime=new ArrayList<>(dailyUpdate.getTime());
					listTask.remove(presentTask);
					listTime.remove(presentTask);
					listTask.add(presentTask, dailyUpdatePost.getTask());
					listTime.add(presentTask, dailyUpdatePost.getTime());
					dailyUpdate.setTasks(listTask);
					dailyUpdate.setTime(listTime);
					dailyTaskList.add(dailyUpdate);
				}
				else
				{
					System.out.println("Inside non-existing task");
					dailyTaskList.remove(dailyUpdate);
					ArrayList<String> listTask=new ArrayList<>(dailyUpdate.getTasks());
					ArrayList<String> listTime=new ArrayList<>(dailyUpdate.getTime());
					listTask.add(dailyUpdatePost.getTask());
					listTime.add(dailyUpdatePost.getTime());
					dailyUpdate.setTasks(listTask);
					dailyUpdate.setTime(listTime);
					dailyTaskList.add(dailyUpdate);
				}
			}
			else
			{
				dailyUpdate.setTopic(dailyUpdatePost.getTopic());
				dailyUpdate.setTasks(Arrays.asList(dailyUpdatePost.getTask()));
				dailyUpdate.setTime(Arrays.asList(dailyUpdatePost.getTime()));
				dailyTaskList.add(dailyUpdate);
				System.out.println("Inside non-existing topic");
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
