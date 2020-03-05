package com.elotz.bean;

import java.util.Objects;

public class DailyUpdateWrapper {
	    private DailyUpdate dailyUpdate;

	    public DailyUpdateWrapper(DailyUpdate dailyUpdate) {
	        this.dailyUpdate = dailyUpdate;
	    }

	    public DailyUpdate unwrap() {
	        return this.dailyUpdate;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        DailyUpdateWrapper that = (DailyUpdateWrapper) o;
	        return Objects.equals(dailyUpdate.getTask(), that.dailyUpdate.getTask());
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(dailyUpdate.getTopic());
	    }
	
}
