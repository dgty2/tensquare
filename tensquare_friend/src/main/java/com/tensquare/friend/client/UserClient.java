package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** @Author lpt @Date 2019/6/14 14:05 @Version 1.0 */
@FeignClient("tensquare-user")
public interface UserClient {

  @RequestMapping(value = "/user/{userid}/{friendid}/{x}", method = RequestMethod.PUT)
  public void updatefanscountandfollowcount(
      @PathVariable("userid") String userid,
      @PathVariable("friendid") String friendid,
      @PathVariable("x") int x);
}
