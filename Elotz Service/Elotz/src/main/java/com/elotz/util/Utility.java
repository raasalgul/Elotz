package com.elotz.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.elotz.bean.DailyUpdate;
import com.elotz.bean.DailyUpdateCompartor;

public class Utility {

	public static Map<String, List<DailyUpdate>> createDailyTaskList(List<DailyUpdate> list)
	{
		Collections.sort(list,new DailyUpdateCompartor());
		Map<String, List<DailyUpdate>> dailyUpdateMap=list.stream().collect(Collectors.groupingBy(DailyUpdate::getTopic,Collectors.toList()));
		System.out.println(dailyUpdateMap);
		return dailyUpdateMap;
	}
	public static List<Object> processDailyGraph(List<DailyUpdate> data)
	{
		List<Map> time=new ArrayList<>();
		Set<String> topicList=new HashSet<>();
		data.stream().forEach(value->{
			topicList.add(value.getTopic()+" "+value.getTask());
			value.setAddedDate(value.getAddedLogon().toLocalDate());
		});
		
		Map<LocalDate, List<DailyUpdate>> dailyUpdateMapUnSorted=data.stream().collect(Collectors.groupingBy(DailyUpdate::getAddedDate,Collectors.toList()));
		List<Object> res=new ArrayList<>();
		res.add(topicList);
		//Collections.synchronizedSortedMap(dailyUpdateMap);
		TreeMap<LocalDate, List<DailyUpdate>> dailyUpdateMap = new TreeMap<>(); 
		dailyUpdateMap.putAll(dailyUpdateMapUnSorted);
		
		
		List<Map<String,String>> result=new ArrayList<>();
		Map<String,String> previous=new LinkedHashMap<>();
		
		dailyUpdateMap.keySet().stream().forEach(test->{
			List<Integer> timeList=new ArrayList<>();
			Map<String,String> tempTopicTime=new LinkedHashMap<>();
			System.out.println(test);
			if(previous.isEmpty())
				topicList.stream().forEach(data2->{
				tempTopicTime.put(data2, "0");
			});
			else
				topicList.stream().forEach(data1->{
					tempTopicTime.put(data1,previous.get(data1));
				});
			dailyUpdateMap.get(test).stream().forEach(dailyUpdate->{
				System.out.println(dailyUpdate.getTopic()+" "+dailyUpdate.getTask());
				int previousInt=0;
				if(!previous.isEmpty())
					previousInt=Integer.parseInt(previous.get(dailyUpdate.getTopic()+" "+dailyUpdate.getTask()));
				int currentInt=Integer.parseInt(dailyUpdate.getTime());
				if(previous.isEmpty() || previousInt==0 || previousInt>currentInt)
				tempTopicTime.put(dailyUpdate.getTopic()+" "+dailyUpdate.getTask(),dailyUpdate.getTime());
				else
					tempTopicTime.put(dailyUpdate.getTopic()+" "+dailyUpdate.getTask(),previous.get(dailyUpdate.getTopic()+" "+dailyUpdate.getTask()));
			//	timeList.add(previous.get(dailyUpdate.getTopic()+" "+dailyUpdate.getTask()));
			});
			result.add(tempTopicTime);
			tempTopicTime.keySet().stream().forEach(value->{
				timeList.add(Integer.parseInt(tempTopicTime.get(value)));
			});
			res.add(timeList);
			previous.putAll(tempTopicTime);
		});
		System.out.println(result);
		
		return res;
		
		
		
		
		
		
//		dailyUpdateMap.keySet().stream().forEach(value->{
//			Map<String,Integer> topicTimeMap=new HashMap<String,Integer>();
//			//ArrayList<Map> topicTime=new ArrayList<>();
//			dailyUpdateMap.get(value).stream().forEach(indv->{
//				//topicList.stream().forEach(topic->{
//				//topicTime.add(Integer.parseInt(indv.getTime()));
//				//else
//					System.out.print(indv.getTopic()+" "+indv.getTask()+indv.getTime()+", ");
//					topicTimeMap.put(indv.getTopic()+" "+indv.getTask(),Integer.parseInt(indv.getTime()));
//					//topicTime.add(topicTimeMap);
//			//});
//			}
//					);
//			System.out.println();
//			time.add(topicTimeMap);
//		}
//				);
//		List<Object> res=new ArrayList<>();
//		res.add(topicList);
//	//	time.stream().forEach(indv->res.add(indv));
//		time.stream().forEach(indv->{
//			ArrayList<Integer> topicTime=new ArrayList<>();
//			topicList.stream().forEach(topic->{
//				if(indv.containsKey(topic))
//						{
//					topicTime.add((Integer) indv.get(topic));
//						}
//				else
//				{
//					topicTime.add(0);
//				}
//			});
//			res.add(topicTime);
//		});
//		return res;

	}
}
