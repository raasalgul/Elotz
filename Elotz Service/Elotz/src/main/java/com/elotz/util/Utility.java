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
}
