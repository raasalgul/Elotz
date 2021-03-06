package com.elotz.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elotz.bean.AllData;
import com.elotz.repository.DailyUpdateRepository;
@Service
public class AllDataService {
	@Autowired
	DailyUpdateRepository dailyUpdateRepository;
	public Map<String, Map<String, List<AllData>>> allDataView() {
		List<AllData>updatedData=new ArrayList<>();
				dailyUpdateRepository.findAll().forEach(data->{
					AllData allData=new AllData(data._id,data.topic,data.task,data.time,
							data.active,data.addedDate.getYear()+"",data.addedDate.getMonth()+"",data.addedLogon,data.addedDate);	
					updatedData.add(allData);
				});
		LocalDateTime date=LocalDateTime.now();
		Map<String, Map<String, List<AllData>>> monthlyUpdate=updatedData.stream().
				collect(Collectors.groupingBy(AllData::getYear,Collectors.groupingBy(AllData::getMonth)));
		Map<String, Map<String, List<AllData>>> noData=new HashMap<>();
		Map<String,List<AllData>> noAllData=new HashMap<>();
		noAllData.put("no Data",Arrays.asList(new AllData(null, "no Data", "no Data", "-1", false,"-1","-1", date, date.toLocalDate())));
		noData.put("no Data",noAllData);
		return monthlyUpdate.size()>0?monthlyUpdate:noData;
	}
	
}
