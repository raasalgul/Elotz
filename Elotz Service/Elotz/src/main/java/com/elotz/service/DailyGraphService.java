package com.elotz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elotz.bean.DailyUpdate;
import com.elotz.repository.DailyUpdateRepository;
import com.elotz.util.Utility;

@Service
public class DailyGraphService {
	@Autowired
	DailyUpdateRepository dailyUpdateRepository;
	public List<Object> getWeekDayGraph() {
		List<DailyUpdate> data = dailyUpdateRepository.findAll();
		return Utility.processDailyGraph(data);
	}

}
