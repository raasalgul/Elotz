package com.elotz.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;
import static java.util.Comparator.comparing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elotz.bean.DailyUpdateCompartor;
import com.elotz.bean.DailyUpdateGet;
import com.elotz.bean.DailyUpdatePost;
import com.elotz.bean.DailyUpdateWrapper;
import com.elotz.dto.DailyUpdate;
import com.elotz.dto.LatestStats;
import com.elotz.dto.Records;
import com.elotz.exception.GenericException;
import com.elotz.exception.TopicNotFound;
import com.elotz.repository.DailyUpdateRepository;
import com.elotz.repository.LatestStatsRepository;
import com.elotz.util.Utility;

@Service
public class DailyUpdateService {
	@Autowired
	DailyUpdateRepository dailyUpdateRepository;
	@Autowired
	LatestStatsRepository latestStatsRepository;
	DailyUpdateGet dailyTask=new DailyUpdateGet();
	List<DailyUpdate> dailyTaskList=new ArrayList<>();
	public List<String> getDailyUpdateService()
	{
		//generate todays date pass that to the database and get the information of daily update.
		//Store the value of daily topics in redux when the page is loaded.
		//Once the topic is selected from drop down	 based on the topic get the daily task.
		return latestStatsRepository.findAll().stream().map(task->task.getTopic()).distinct().collect(Collectors.toList());
	}
	public DailyUpdateGet getDailyUpdateTasksService(String topic) throws TopicNotFound
	{
		DailyUpdateGet dailyUpdateGet=new DailyUpdateGet();
		dailyUpdateGet.setTopic(topic);
		List<LatestStats> matchedDailyUpdate = latestStatsRepository.findAll().stream().filter(task->task.getTopic().equalsIgnoreCase(topic)).collect(Collectors.toList());
		if(!matchedDailyUpdate.isEmpty())
		{
//			Collections.sort(matchedDailyUpdate,new DailyUpdateCompartor());
//			matchedDailyUpdate=matchedDailyUpdate.stream().map(DailyUpdateWrapper::new).distinct().map(DailyUpdateWrapper::unwrap).collect(Collectors.toList());
			List<String> taskList=new ArrayList<>();
			List<String> timeList=new ArrayList<>();
			List<Boolean> activeList=new ArrayList<>();
			List<LocalDate> dateList=new ArrayList<>();
			for(LatestStats dailyUpdate:matchedDailyUpdate)
			{
				taskList.add(dailyUpdate.getTask());
				timeList.add(dailyUpdate.getTime());
				activeList.add(dailyUpdate.isActive());
				dateList.add(dailyUpdate.getAddedDate());
			}
			dailyUpdateGet.setTasks(taskList);
			dailyUpdateGet.setTime(timeList);
			dailyUpdateGet.setActive(activeList);
			dailyUpdateGet.setDate(dateList);
			return dailyUpdateGet;
		}
		throw new TopicNotFound("Failure","Given Topic is not found");
	}
	public String postDailyUpdateService(DailyUpdatePost dailyUpdatePost) throws GenericException
	{
		try {
			DailyUpdate dailyUpdate=new DailyUpdate();
			Records record=new Records();
			LatestStats latestStats=new LatestStats();
			LocalDateTime localDateTime=LocalDateTime.now();
			LocalDate localDate=LocalDate.now();
			latestStats.setTask(dailyUpdatePost.getTask());
			latestStats.setTopic(dailyUpdatePost.getTopic());
			latestStats.setActive(dailyUpdatePost.getActive());
			latestStats.setTime(dailyUpdatePost.getTime());
			latestStats.setAddedLogon(localDateTime);
			ArrayList<Records> records=new ArrayList<>();
			record.setAddedDate(localDate);
			record.setAddedLogon(localDateTime);
			record.setTime(dailyUpdatePost.getTime());
			dailyUpdate.setTask(dailyUpdatePost.getTask());
			dailyUpdate.setTopic(dailyUpdatePost.getTopic());
			dailyUpdate.setActive(dailyUpdatePost.getActive());
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
					Optional<LatestStats> dailyTaskLatestStats = latestStatsRepository.findAll().stream().filter(dailyTask->dailyTask.getTask().equalsIgnoreCase(dailyUpdatePost.getTask())).findFirst();
					records=dailyTaskInv.get().getRecords();
					localDate=records.get(records.size()-1).getAddedDate();
//					LocalDateTime addedLogOn=records.get(records.size()-1).getAddedLogon();
//					if(addedLogOn.getDayOfMonth()==currentDate.getDayOfMonth() &&addedLogOn.getMonth()==currentDate.getMonth() &&addedLogOn.getYear()==currentDate.getYear())
//					{
//						dailyUpdate.set_id(dailyTaskInv.get().get_id());
//						dailyUpdate.setAddedLogon(addedLogOn);
//						dailyUpdateRepository.delete(dailyUpdate);
//					}
					record.setAddedDate(localDate);
					latestStats.setAddedDate(localDate);
					records.add(record);
					dailyUpdate.setRecords(records);
					dailyUpdate.set_id(dailyTaskInv.get().get_id());
					latestStats.set_id(dailyTaskLatestStats.get().get_id());
					latestStatsRepository.save(latestStats);
					dailyUpdateRepository.save(dailyUpdate);
				}
				else
				{
					System.out.println("Inside non-existing task");
					records.add(record);
					dailyUpdate.setRecords(records);
					latestStats.setAddedDate(localDate);
					latestStatsRepository.save(latestStats);
					dailyUpdateRepository.save(dailyUpdate);
				}
			}
			else
			{
				System.out.println("Inside non-existing topic");

				records.add(record);
				dailyUpdate.setRecords(records);
				latestStats.setAddedDate(localDate);
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
	public Map<String, List<LatestStats>> dailyUpdateViewService() {
		LocalDateTime date=LocalDateTime.now();
//		return dailyUpdateRepository.findAll().stream().filter(data->data.getAddedDate().getYear()==date.getYear()).filter(data->data.getAddedDate().getDayOfYear()==date.getDayOfYear()).collect(Collectors.groupingBy(DailyUpdate::getTopic,Collectors.toList()));
		Map<String, List<LatestStats>>dailyUpdate= latestStatsRepository.findAll().stream()
				.filter(data->data.getActive()!=null)
				.filter(data->data.getActive()==true)
//				.sorted(Comparator.comparing(LatestStats::getAddedLogon).reversed())
//				.sorted(Comparator.comparing(LatestStats::getAddedDate).reversed())
//				.collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(comparing(LatestStats::getTask))),
//                                                           ArrayList::new)).stream()
				.collect(Collectors.groupingBy(LatestStats::getTopic,Collectors.toList()));
		Map<String, List<LatestStats>> noData=new HashMap<>();
		noData.put("no Data", Arrays.asList(new LatestStats(null, "no Data", "no Data", "-1", false, date, date.toLocalDate())));
		return dailyUpdate.size()>0?dailyUpdate:noData;
	}

}
