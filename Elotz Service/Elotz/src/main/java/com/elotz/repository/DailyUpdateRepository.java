package com.elotz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.elotz.bean.DailyUpdate;

public interface DailyUpdateRepository extends MongoRepository<DailyUpdate,Integer>{

}
