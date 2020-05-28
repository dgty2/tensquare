package com.tensquare.spit.controller.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/** @Author lpt @Date 2019/6/6 14:12 @Version 1.0 */
public interface SpitDao extends MongoRepository<Spit, String> {

  Page<Spit> findByParentid(String parentid, Pageable pageable);
}
