package com.elotz.bean;

import java.util.Comparator;

import com.elotz.dto.LatestStats;
import com.elotz.dto.Records;

public class DailyUpdateCompartor implements Comparator<Records>{

	@Override
	public int compare(Records o1, Records o2) {
		return o2.getAddedLogon().compareTo(o1.getAddedLogon());
	}

}
