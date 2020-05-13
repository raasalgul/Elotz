package com.elotz.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elotz.bean.DailyUpdate;
import com.elotz.bean.MonthlyUpdatePost;
import com.elotz.exception.GenericException;
import com.elotz.repository.DailyUpdateRepository;
import com.elotz.util.Utility;

@Service
public class MonthlyUpdateService {
	List<DailyUpdate> dailyTaskList=new ArrayList<>();
	@Autowired
	DailyUpdateRepository dailyUpdateRepository;
	public Map<String, List<DailyUpdate>> monthlyUpdateViewService() {
		LocalDateTime date=LocalDateTime.now();
		Map<String, List<DailyUpdate>> monthlyUpdate=dailyUpdateRepository.findAll().stream().
		filter(data->data.getAddedDate()!=null).
		filter(data->data.getAddedDate().getYear()==date.getYear()).
		filter(data->data.getAddedDate().getMonthValue()==date.getMonthValue()).
		collect(Collectors.groupingBy(DailyUpdate::getTopic,Collectors.toList()));
		Map<String, List<DailyUpdate>> noData=new HashMap<>();
		noData.put("no Data", Arrays.asList(new DailyUpdate(null, "no Data", "no Data", "-1", false, date, date.toLocalDate())));
		return monthlyUpdate.size()>0?monthlyUpdate:noData;

	}
	public String postMonthlyUpdateService(MonthlyUpdatePost monthlyUpdatePost) throws GenericException {
		try {
			DailyUpdate dailyUpdate=new DailyUpdate();
			//List<DailyUpdate> test = dailyUpdateRepository.findAll();

			/*Make the string compare as case insensitive later.
			 * */

			Map<String, List<DailyUpdate>> topicMap = Utility.createDailyTaskList(dailyUpdateRepository.findAll());
			if(topicMap.containsKey(monthlyUpdatePost.getTopic()))
			{
				System.out.println("Inside existing topic");
				dailyTaskList=topicMap.get(monthlyUpdatePost.getTopic());
				Optional<DailyUpdate> dailyTaskInv = dailyTaskList.stream().filter(dailyTask->dailyTask.getTask().equalsIgnoreCase(monthlyUpdatePost.getTask())).findFirst();

				if(dailyTaskInv.isPresent())
				{
					System.out.println("Inside existing task");
					dailyUpdate.setTask(monthlyUpdatePost.getTask());
					dailyUpdate.setTime(monthlyUpdatePost.getTime());
					dailyUpdate.setTopic(monthlyUpdatePost.getTopic());
//					dailyUpdate.setActive(monthlyUpdatePost.getActive());
					dailyUpdate.setAddedLogon(LocalDateTime.now());
					LocalDate addedDate=dailyTaskInv.get().getAddedDate();
					LocalDate currentDate=LocalDate.now();
					currentDate=LocalDate.parse(currentDate.toString().replaceAll("-..-", String.format("-%s-",monthlyUpdatePost.getMonth())));
					if(addedDate.getYear()==currentDate.getYear())
					{
						dailyUpdate.set_id(dailyTaskInv.get().get_id());
						dailyUpdate.setAddedDate(addedDate);
						dailyUpdateRepository.delete(dailyUpdate);
					}
					dailyUpdate.setAddedDate(currentDate);
					dailyUpdateRepository.save(dailyUpdate);
				}
				else
				{
					System.out.println("Inside non-existing task");
					dailyUpdate.setTask(monthlyUpdatePost.getTask());
					dailyUpdate.setTime(monthlyUpdatePost.getTime());
					dailyUpdate.setTopic(monthlyUpdatePost.getTopic());
					//dailyUpdate.setActive(monthlyUpdatePost.getActive());
					LocalDate localDate=LocalDate.now();
					localDate=LocalDate.parse(localDate.toString().replaceAll("-..-", String.format("-%s-",monthlyUpdatePost.getMonth())));
					dailyUpdate.setAddedDate(localDate);
					LocalDateTime date=LocalDateTime.now();
					dailyUpdate.setAddedLogon(date);
					dailyUpdateRepository.save(dailyUpdate);
				}
			}
			else
			{
				System.out.println("Inside non-existing topic");

				dailyUpdate.setTask(monthlyUpdatePost.getTask());
				dailyUpdate.setTime(monthlyUpdatePost.getTime());
				dailyUpdate.setTopic(monthlyUpdatePost.getTopic());
				//dailyUpdate.setActive(monthlyUpdatePost.getActive());
				LocalDateTime date=LocalDateTime.now();
				LocalDate localDate=LocalDate.now();
				localDate=LocalDate.parse(localDate.toString().replaceAll("-..-", String.format("-%s-",monthlyUpdatePost.getMonth())));
				dailyUpdate.setAddedDate(localDate);
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
//	public static void main(String args[])
//	{
//		LocalDateTime currentDate=LocalDateTime.now();
//		System.out.println(currentDate);
//		currentDate=LocalDateTime.parse(currentDate.toString().replaceAll("-..-", "-12-"));
//		System.out.println(currentDate);
//	}
}
