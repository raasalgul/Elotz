package com.elotz.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.elotz.bean.DailyUpdateCompartor;
import com.elotz.bean.GraphData;
import com.elotz.dto.DailyUpdate;
import com.elotz.dto.Records;

public class Utility {

	public static Map<String, List<DailyUpdate>> createDailyTaskList(List<DailyUpdate> list)
	{
//		Collections.sort(list,new DailyUpdateCompartor());
		Map<String, List<DailyUpdate>> dailyUpdateMap=list.stream().
				collect(Collectors.groupingBy(DailyUpdate::getTopic,Collectors.toList()));
		System.out.println(dailyUpdateMap);
		return dailyUpdateMap;
	}
	public static List<GraphData> processDailyGraph(List<DailyUpdate> data)
	{
//		Map<String, List<DailyUpdate>> dailyUpdateMap=data.stream().collect(Collectors.groupingBy(DailyUpdate::getTopic,Collectors.toList()));
//		Map<String, List<DailyUpdate>> dailyUpdateMap=data.stream().collect(Collectors.groupingBy(DailyUpdate::getTask,Collectors.toList()));
		List<GraphData> graphDataList=new ArrayList<>();
		
/*		dailyUpdateMap.forEach((k,v)->{
			GraphData graphData=new GraphData();
			graphData.setLabel(k);
			graphData.setData(v.stream().map(d->d.getRecords()).map(d->d.ge).collect(Collectors.toList()));
			graphData.setBackgroundColor(Utility.colorGenerator());
			graphDataList.add(graphData);
		});*/
		for(DailyUpdate daily:data)
		{
			GraphData graphData=new GraphData();
			graphData.setLabel(daily.getTask());
			graphData.setData(daily.getRecords().stream().sorted((d1,d2)->d1.getAddedLogon().compareTo(d2.getAddedLogon()))
					.map(d->d.getTime()).collect(Collectors.toList()));
			graphData.setBackgroundColor(Utility.colorGenerator());
			graphDataList.add(graphData);
		}
	return graphDataList;
	}
/*	public static List<Object> processDailyGraph(List<DailyUpdate> data)
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

	}*/
	private static String colorGenerator() {
		String color="#";
	Random hexa=new Random();
	for(int i=0;i<6;i++)
	{
	Integer rand=hexa.nextInt(16);
	color+=Integer.toHexString(rand);
	}
		return color;
	}
}
