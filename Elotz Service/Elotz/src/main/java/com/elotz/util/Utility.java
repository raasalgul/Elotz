package com.elotz.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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
	public static void main(String args[])
	{
		List<String> uniqueKey=Arrays.asList("Java Oops","Java Variables","REACT Hooks","MONGO transations","MONGO DML");
		Map<String,List<DailyUpdate>> dateMap=new LinkedHashMap<>();
		dateMap.put("test1",Arrays.asList(
				new DailyUpdate(null,"Java","Oops","20",true,null),
				new DailyUpdate(null,"Java","Variables","30",true,null)
				//new DailyUpdate(null,"Java","Date","23",true,null)
				));
		dateMap.put("test2",Arrays.asList(
				//			new DailyUpdate(null,"REACT","Functional components","12",true,null),
				//			new DailyUpdate(null,"REACT","Class components","21",true,null),
				//			new DailyUpdate(null,"MONGO DB","DDL","32",true,null)
				new DailyUpdate(null,"REACT","Hooks","12",true,null),
				new DailyUpdate(null,"Java","Oops","10",true,null)
				));
		dateMap.put("test3",Arrays.asList(
				//			new DailyUpdate(null,"Java","Collections","12",true,null),
				//			new DailyUpdate(null,"Java","Loops","22",true,null),
				//			new DailyUpdate(null,"Java","String	","32",true,null)
				new DailyUpdate(null,"MONGO","transations","26",true,null),
				new DailyUpdate(null,"Java","Oops","5",true,null)
				));
		dateMap.put("test4",Arrays.asList(
				new DailyUpdate(null,"MONGO","transations","16",true,null),
				new DailyUpdate(null,"Java","Variables","20",true,null),
				new DailyUpdate(null,"MONGO","DML","52",true,null)
				));
		//	List<DailyUpdate> dailyUpdateList=Arrays.asList(
		//			new DailyUpdate(null,"Java","Oops","20",true,null),
		//			new DailyUpdate(null,"Java","Collections","20",true,null),
		//			new DailyUpdate(null,"Java","Variables","20",true,null),
		//			new DailyUpdate(null,"Java","Loops","20",true,null),
		//			new DailyUpdate(null,"Java","Date","20",true,null),
		//			new DailyUpdate(null,"Java","String	","20",true,null),
		//			new DailyUpdate(null,"REACT","Functional components","20",true,null),
		//			new DailyUpdate(null,"REACT","Hooks","20",true,null),
		//			new DailyUpdate(null,"REACT","Class components","20",true,null),
		//			new DailyUpdate(null,"MONGO DB","transations","20",true,null),
		//			new DailyUpdate(null,"MONGO DB","DDL","20",true,null),
		//			new DailyUpdate(null,"MONGO DB","DML","20",true,null)
		//			);
		List<Map<String,String>> result=new ArrayList<>();
		Map<String,String> previous=new LinkedHashMap<>();
		dateMap.keySet().stream().forEach(test->{
			Map<String,String> tempTopicTime=new LinkedHashMap<>();
			System.out.println(test);
			if(previous.isEmpty())
			uniqueKey.stream().forEach(data->{
				tempTopicTime.put(data, "0");
			});
			else
				uniqueKey.stream().forEach(data->{
					tempTopicTime.put(data,previous.get(data));
				});
			dateMap.get(test).stream().forEach(dailyUpdate->{
				System.out.println(dailyUpdate.getTopic()+" "+dailyUpdate.getTask());
				int previousInt=0;
				if(!previous.isEmpty())
					previousInt=Integer.parseInt(previous.get(dailyUpdate.getTopic()+" "+dailyUpdate.getTask()));
				int currentInt=Integer.parseInt(dailyUpdate.getTime());
				if(previous.isEmpty() || previousInt==0 || previousInt>currentInt)
				tempTopicTime.put(dailyUpdate.getTopic()+" "+dailyUpdate.getTask(),dailyUpdate.getTime());
				else
					tempTopicTime.put(dailyUpdate.getTopic()+" "+dailyUpdate.getTask(),previous.get(dailyUpdate.getTopic()+" "+dailyUpdate.getTask()));
			});
			result.add(tempTopicTime);
			previous.putAll(tempTopicTime);
		});
		System.out.println(result);
	}
}
