package com.elotz.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elotz.bean.MonthlyUpdatePost;
import com.elotz.dto.DailyUpdate;
import com.elotz.dto.LatestStats;
import com.elotz.dto.Records;
import com.elotz.exception.GenericException;
import com.elotz.repository.DailyUpdateRepository;
import com.elotz.repository.LatestStatsRepository;
import com.elotz.util.Utility;

@Service
public class MonthlyUpdateService {
	List<DailyUpdate> dailyTaskList=new ArrayList<>();
	@Autowired
	DailyUpdateRepository dailyUpdateRepository;
	@Autowired
	LatestStatsRepository latestStatsRepository;
	public Map<String, List<LatestStats>> monthlyUpdateViewService() {
		LocalDateTime date=LocalDateTime.now();
		Map<String, List<LatestStats>> monthlyUpdate=latestStatsRepository.findAll().stream().
		filter(data->data.getAddedDate()!=null).
		filter(data->data.getAddedDate().getYear()==date.getYear()).
		filter(data->data.getAddedDate().getMonthValue()==date.getMonthValue()).sorted(Comparator.comparing(LatestStats::getAddedDate)).
		collect(Collectors.groupingBy(LatestStats::getTopic,Collectors.toList()));
		Map<String, List<LatestStats>> noData=new HashMap<>();
		noData.put("no Data", Arrays.asList(new LatestStats(null, "no Data", "no Data", "-1", false, date, date.toLocalDate())));
		return monthlyUpdate.size()>0?monthlyUpdate:noData;

	}
	public String postMonthlyUpdateService(MonthlyUpdatePost monthlyUpdatePost) throws GenericException {
		try {
			DailyUpdate dailyUpdate=new DailyUpdate();
			Records record=new Records();
			LatestStats latestStats=new LatestStats();
			LocalDateTime localDateTime=LocalDateTime.now();
			LocalDate localDate=LocalDate.now();
			latestStats.setTask(monthlyUpdatePost.getTask());
			latestStats.setTopic(monthlyUpdatePost.getTopic());
			latestStats.setTime(monthlyUpdatePost.getTime());
			latestStats.setAddedLogon(localDateTime);
			latestStats.setAddedDate(localDate);
			latestStats.setActive(false);
			dailyUpdate.setActive(false);
			ArrayList<Records> records=new ArrayList<>();
			record.setAddedLogon(localDateTime);
			record.setTime(monthlyUpdatePost.getTime());
			dailyUpdate.setTask(monthlyUpdatePost.getTask());
			dailyUpdate.setTopic(monthlyUpdatePost.getTopic());
			//List<DailyUpdate> test = dailyUpdateRepository.findAll();

			/*Make the string compare as case insensitive later.
			 * */

			Map<String, List<DailyUpdate>> topicMap = Utility.createDailyTaskList(dailyUpdateRepository.findAll());
			String monthlyUpdateDate=monthlyUpdatePost.getDate();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate currentDate=LocalDate.parse(monthlyUpdateDate,format);
			//This currentDateTime is not used in code, having these couple of lines to know how to create datetime from date.
			LocalDateTime currentDateTime=currentDate.atStartOfDay();
			System.out.println(currentDateTime);
			if(topicMap.containsKey(monthlyUpdatePost.getTopic()))
			{
				System.out.println("Inside existing topic");
				dailyTaskList=topicMap.get(monthlyUpdatePost.getTopic());
				Optional<DailyUpdate> dailyTaskInv = dailyTaskList.stream().filter(dailyTask->dailyTask.getTask().equalsIgnoreCase(monthlyUpdatePost.getTask())).findFirst();

				if(dailyTaskInv.isPresent())
				{
					System.out.println("Inside existing task");
					Optional<LatestStats> dailyTaskLatestStats = latestStatsRepository.findAll().stream().filter(dailyTask->dailyTask.getTask().equalsIgnoreCase(monthlyUpdatePost.getTask())).findFirst();
//					dailyUpdate.setActive(monthlyUpdatePost.getActive());
//					LocalDate addedDate=dailyTaskInv.get().getAddedDate();
//					LocalDate currentDate=LocalDate.now();
//					currentDate=LocalDate.parse(currentDate.toString().replaceAll("-..-", String.format("-%s-",monthlyUpdatePost.getMonth())));
//					if(addedDate.getYear()==currentDate.getYear())
//					{
						records=dailyTaskInv.get().getRecords();
						latestStats.setActive(dailyTaskInv.get().getActive());
						dailyUpdate.setActive(dailyTaskInv.get().getActive());
						dailyUpdate.set_id(dailyTaskInv.get().get_id());
// While updating no need to add added date because added must be the date that this task has been created. We can
// get updated date from addedLog
						record.setAddedDate(dailyTaskLatestStats.get().getAddedDate());
						records.add(record);
						dailyUpdate.setRecords(records);
//						dailyUpdateRepository.save(dailyUpdate);
//						dailyUpdateRepository.delete(dailyUpdate);
//					}
						System.out.println(dailyUpdate);
						latestStats.setAddedDate(dailyTaskLatestStats.get().getAddedDate());
						latestStats.set_id(dailyTaskLatestStats.get().get_id());
						latestStatsRepository.save(latestStats);
					dailyUpdateRepository.save(dailyUpdate);
				}
				else
				{
					System.out.println("Inside non-existing task");
					//dailyUpdate.setActive(monthlyUpdatePost.getActive());
//					LocalDate localDate=LocalDate.now();
//					localDate=LocalDate.parse(localDate.toString().replaceAll("-..-", String.format("-%s-",monthlyUpdatePost.getMonth())));
					record.setAddedDate(currentDate);
					records.add(record);
					dailyUpdate.setRecords(records);
					latestStatsRepository.save(latestStats);
					dailyUpdateRepository.save(dailyUpdate);
				}
			}
			else
			{
				System.out.println("Inside non-existing topic");
				record.setAddedDate(currentDate);
				records.add(record);
				dailyUpdate.setRecords(records);
				latestStatsRepository.save(latestStats);
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
//		String date="Thu Mar 18 2021 00:00:00 GMT+0530 (India Standard Time)";
//		date=date.substring(0,date.indexOf(" 00:"));
//		DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MMM dd yyyy", Locale.ENGLISH);
//		LocalDate currentDate=LocalDate.parse(date,format);
//		System.out.println(currentDate);
//		LocalDateTime dateTime=currentDate.atStartOfDay();
////		dateTime.parse(currentDate.toString());
//		System.out.println(dateTime);
//	}
}
