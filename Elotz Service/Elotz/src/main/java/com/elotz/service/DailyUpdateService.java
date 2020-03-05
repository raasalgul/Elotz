package com.elotz.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elotz.bean.DailyUpdate;
import com.elotz.bean.DailyUpdateCompartor;
import com.elotz.bean.DailyUpdateGet;
import com.elotz.bean.DailyUpdatePost;
import com.elotz.bean.DailyUpdateWrapper;
import com.elotz.exception.GenericException;
import com.elotz.exception.TopicNotFound;
import com.elotz.repository.DailyUpdateRepository;
import com.elotz.util.Utility;

@Service
public class DailyUpdateService {
	@Autowired
	DailyUpdateRepository dailyUpdateRepository;
	DailyUpdateGet dailyTask=new DailyUpdateGet();
	List<DailyUpdate> dailyTaskList=new ArrayList<>();
	public List<String> getDailyUpdateService()
	{
		//generate todays date pass that to the database and get the information of daily update.
		//Store the value of daily topics in redux when the page is loaded.
		//Once the topic is selected from drop down	 based on the topic get the daily task.
		return dailyUpdateRepository.findAll().stream().map(task->task.getTopic()).distinct().collect(Collectors.toList());
	}
	public DailyUpdateGet getDailyUpdateTasksService(String topic) throws TopicNotFound
	{
		DailyUpdateGet dailyUpdateGet=new DailyUpdateGet();
		dailyUpdateGet.setTopic(topic);
		List<DailyUpdate> matchedDailyUpdate = dailyUpdateRepository.findAll().stream().filter(task->task.getTopic().equalsIgnoreCase(topic)).collect(Collectors.toList());
		if(!matchedDailyUpdate.isEmpty())
		{
			Collections.sort(matchedDailyUpdate,new DailyUpdateCompartor());
			matchedDailyUpdate=matchedDailyUpdate.stream().map(DailyUpdateWrapper::new).distinct().map(DailyUpdateWrapper::unwrap).collect(Collectors.toList());
			List<String> taskList=new ArrayList<>();
			List<String> timeList=new ArrayList<>();
			List<Boolean> activeList=new ArrayList<>();
			List<LocalDateTime> addedLogonList=new ArrayList<>();
			for(DailyUpdate dailyUpdate:matchedDailyUpdate)
			{
				taskList.add(dailyUpdate.getTask());
				timeList.add(dailyUpdate.getTime());
				activeList.add(dailyUpdate.isActive());
				addedLogonList.add(dailyUpdate.getAddedLogon());
			}
			dailyUpdateGet.setTasks(taskList);
			dailyUpdateGet.setTime(timeList);
			dailyUpdateGet.setActive(activeList);
			dailyUpdateGet.setAddedLogon(addedLogonList);
			return dailyUpdateGet;
		}
		throw new TopicNotFound("Failure","Given Topic is not found");
	}
	public String postDailyUpdateService(DailyUpdatePost dailyUpdatePost) throws GenericException
	{
		try {
			DailyUpdate dailyUpdate=new DailyUpdate();
			//List<DailyUpdate> test = dailyUpdateRepository.findAll();

			/*Make the string compare as case insensitive later.
			 * */

			Map<String, List<DailyUpdate>> topicMap = Utility.createDailyTaskList(dailyUpdateRepository.findAll());
			if(topicMap.containsKey(dailyUpdatePost.getTopic()))
			{
				System.out.println("Inside existing topic");
				dailyTaskList=topicMap.get(dailyUpdatePost.getTopic());
				Optional<DailyUpdate> dailyTaskInv = dailyTaskList.stream().filter(dailyTask->dailyTask.getTask().equalsIgnoreCase(dailyUpdatePost.getTask())).findFirst();

				if(dailyTaskInv.isPresent())
				{
					System.out.println("Inside existing task");
					dailyUpdate.setTask(dailyUpdatePost.getTask());
					dailyUpdate.setTime(dailyUpdatePost.getTime());
					dailyUpdate.setTopic(dailyUpdatePost.getTopic());
					dailyUpdate.setActive(dailyUpdatePost.getActive());
					LocalDateTime addedLogOn=dailyTaskInv.get().getAddedLogon();
					LocalDateTime currentDate=LocalDateTime.now();
					if(addedLogOn.getDayOfMonth()==currentDate.getDayOfMonth() &&addedLogOn.getMonth()==currentDate.getMonth() &&addedLogOn.getYear()==currentDate.getYear())
					{
						dailyUpdate.set_id(dailyTaskInv.get().get_id());
						dailyUpdate.setAddedLogon(addedLogOn);
						dailyUpdateRepository.delete(dailyUpdate);
					}
					dailyUpdate.setAddedLogon(currentDate);
					dailyUpdateRepository.save(dailyUpdate);
				}
				else
				{
					System.out.println("Inside non-existing task");
					dailyUpdate.setTask(dailyUpdatePost.getTask());
					dailyUpdate.setTime(dailyUpdatePost.getTime());
					dailyUpdate.setTopic(dailyUpdatePost.getTopic());
					dailyUpdate.setActive(dailyUpdatePost.getActive());
					LocalDateTime date=LocalDateTime.now();
					dailyUpdate.setAddedLogon(date);
					dailyUpdateRepository.save(dailyUpdate);
				}
			}
			else
			{
				System.out.println("Inside non-existing topic");

				dailyUpdate.setTask(dailyUpdatePost.getTask());
				dailyUpdate.setTime(dailyUpdatePost.getTime());
				dailyUpdate.setTopic(dailyUpdatePost.getTopic());
				dailyUpdate.setActive(dailyUpdatePost.getActive());
				LocalDateTime date=LocalDateTime.now();
				dailyUpdate.setAddedLogon(date);
				dailyUpdateRepository.save(dailyUpdate);
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
