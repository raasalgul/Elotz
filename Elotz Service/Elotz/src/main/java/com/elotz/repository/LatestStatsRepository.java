package com.elotz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.elotz.dto.DailyUpdate;
import com.elotz.dto.LatestStats;

public interface LatestStatsRepository extends MongoRepository<LatestStats,Integer>{

}
