package com.tensquare.spit.service;

import com.tensquare.spit.controller.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

/** @Author lpt @Date 2019/6/6 14:11 @Version 1.0 */
@Service
public class SpitService {
  @Autowired private SpitDao spitDao;
  @Autowired private IdWorker idWorker;
  @Autowired private MongoTemplate mongoTemplate;

  public List<Spit> findAll() {
    return spitDao.findAll();
  }

  public Spit findById(String id) {
    return spitDao.findById(id).get();
  }

  public void update(Spit spit) {
    spitDao.save(spit);
  }

  public void deleteById(String id) {
    spitDao.deleteById(id);
  }

  public Page<Spit> pageQuery(String parentid, int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    return spitDao.findByParentid(parentid, pageable);
  }

  public void save(Spit spit) {
    //spit.set_id(idWorker.nextId() + "");
    // 初始化数据完善
    spit.setPublishtime(new Date()); // 发布日期
    spit.setVisits(0); // 浏览量
    spit.setShare(0); // 分享数
    spit.setThumbup(0); // 点赞数
    spit.setComment(0); // 回复数
    spit.setState("1"); // 状态
    // 判断父节点是否有
    if (spit.getParentid() != null && !"".equals(spit.getParentid())) {
      Query query = new Query();
      query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
      Update update = new Update();
      update.inc("comment", 1);
      mongoTemplate.updateFirst(query, update, Spit.class);
    }

    spitDao.save(spit);
  }

  public void addthumbup(String spitId) {

    Update update = new Update();
    update.inc("thumbup", 1);
    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(spitId));
    mongoTemplate.updateFirst(query, update, "spit");
  }
}
