package com.elotz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.elotz.dto.DailyUpdate;

public interface DailyUpdateRepository extends MongoRepository<DailyUpdate,Integer>{

}
