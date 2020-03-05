package com.elotz.bean;

import java.util.Comparator;

public class DailyUpdateCompartor implements Comparator<DailyUpdate>{

	@Override
	public int compare(DailyUpdate o1, DailyUpdate o2) {
		return o2.getAddedLogon().compareTo(o1.getAddedLogon());
	}

}
