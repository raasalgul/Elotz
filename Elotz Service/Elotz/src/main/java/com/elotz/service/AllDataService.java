package com.elotz.service;

import static java.util.Comparator.comparing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elotz.bean.AllData;
import com.elotz.repository.DailyUpdateRepository;
import com.elotz.repository.LatestStatsRepository;
@Service
public class AllDataService {
	@Autowired
	LatestStatsRepository latestStatsRepository;
	public Map<String, Map<String, List<AllData>>> allDataView() {
		List<AllData>updatedData=new ArrayList<>();
		latestStatsRepository.findAll().forEach(data->{
					AllData allData=new AllData(data._id,data.topic,data.task,data.time,
							data.active,data.addedDate.getYear()+"",data.addedDate.getMonth()+"",data.addedLogon,data.addedDate);	
					updatedData.add(allData);
				});
		LocalDateTime date=LocalDateTime.now();
		Map<String, Map<String, List<AllData>>> monthlyUpdate=updatedData.stream()
				.sorted(Comparator.comparing(AllData::getAddedLogon).reversed())
				.sorted(Comparator.comparing(AllData::getAddedDate).reversed())
				.collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(comparing(AllData::getTask))),
                                                           ArrayList::new)).stream()
				.collect(Collectors.groupingBy(AllData::getYear,Collectors.groupingBy(AllData::getMonth)));
		Map<String, Map<String, List<AllData>>> noData=new HashMap<>();
		Map<String,List<AllData>> noAllData=new HashMap<>();
		noAllData.put("no Data",Arrays.asList(new AllData(null, "no Data", "no Data", "-1", false,"-1","-1", date, date.toLocalDate())));
		noData.put("no Data",noAllData);
		return monthlyUpdate.size()>0?monthlyUpdate:noData;
	}
}
