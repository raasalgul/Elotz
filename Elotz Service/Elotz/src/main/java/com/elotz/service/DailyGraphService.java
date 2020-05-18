package com.elotz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elotz.bean.DailyUpdate;
import com.elotz.bean.GraphData;
import com.elotz.repository.DailyUpdateRepository;
import com.elotz.util.Utility;

@Service
public class DailyGraphService {
	@Autowired
	DailyUpdateRepository dailyUpdateRepository;
//	public List<Object> getWeekDayGraph() {
//		List<DailyUpdate> data = dailyUpdateRepository.findAll();
//		return Utility.processDailyGraph(data);
//	}
	
	public List<GraphData> getDailyGraph() {
		List<DailyUpdate> data = dailyUpdateRepository.findAll().stream().
				filter(d->d.getAddedLogon()!=null).
				filter(d->d.getActive()!=null).
				sorted((d1,d2)->d1.getAddedLogon().compareTo(d2.getAddedLogon())).
				filter(d->d.getActive()==true).collect(Collectors.toList());
		return Utility.processDailyGraph(data);
	}

}
