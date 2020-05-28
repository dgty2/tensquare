package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** @Author lpt @Date 2019/6/6 14:11 @Version 1.0 */
@RestController
@RequestMapping("/spit")
@CrossOrigin
public class SpitController {

  @Autowired private SpitService spitService;
  @Autowired private RedisTemplate redisTemplate;

  @RequestMapping(method = RequestMethod.GET)
  public Result findAll() {
    return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
  }

  @RequestMapping(value = "/{spitId}", method = RequestMethod.GET)
  public Result findById(@PathVariable String spitId) {
    return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));
  }

  @RequestMapping(method = RequestMethod.POST)
  public Result save(@RequestBody Spit spit) {
    spitService.save(spit);
    return new Result(true, StatusCode.OK, "添加成功");
  }

  @RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
  public Result update(@PathVariable String spitId, @RequestBody Spit spit) {
    spit.set_id(spitId);
    spitService.update(spit);
    return new Result(true, StatusCode.OK, "修改成功");
  }

  @RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
  public Result delete(@PathVariable String spitId) {
    spitService.deleteById(spitId);
    return new Result(true, StatusCode.OK, "删除成功");
  }

  @RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
  public Result comment(
      @PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
    Page<Spit> pageData = spitService.pageQuery(parentid, page, size);
    return new Result(
        true,
        StatusCode.OK,
        "查询成功",
        new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
  }

  @RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
  public Result addthumbup(@PathVariable String spitId) {
    String userId = "111";
    if (redisTemplate.opsForValue().get("spit_" + userId + "_" + spitId) != null) {
      return new Result(false, StatusCode.REPERROR, "重复点赞");
    }
    spitService.addthumbup(spitId);
    redisTemplate.opsForValue().set("spit_" + userId + "_" + spitId, spitId);
    return new Result(true, StatusCode.OK, "点赞成功");
  }
}
