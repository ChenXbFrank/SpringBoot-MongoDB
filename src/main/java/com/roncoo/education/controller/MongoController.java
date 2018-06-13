/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.roncoo.education.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.bean.RoncooUserLog;
import com.roncoo.education.dao.RoncooUserLogMongoDao;

/**
 * @author wujing
 */
@Controller
@RequestMapping(value = "/mongo")
public class MongoController {
	
	@Autowired
	private RoncooUserLogMongoDao roncooUserLogMongoDao;

	
	@RequestMapping(value = "save")
	@ResponseBody
	public RoncooUserLog save() {
		RoncooUserLog entity = new RoncooUserLog();
		entity.setId(1);
		entity.setUserName("无境");
		entity.setUserIp("192.168.0.1");
		entity.setCreateTime(new Date());
		roncooUserLogMongoDao.save(entity);
		RoncooUserLog roncooUserLog = roncooUserLogMongoDao.findOne(1);
		return roncooUserLog;
	}

	@RequestMapping(value = "get")
	@ResponseBody
	public RoncooUserLog get() {
		RoncooUserLog roncooUserLog = roncooUserLogMongoDao.findOne(1);
		return roncooUserLog;
	}
	
	@RequestMapping(value = "get2")
	@ResponseBody
	public RoncooUserLog get2() {
		RoncooUserLog result = roncooUserLogMongoDao.findByUserName("无境2");
		return result;
	}
	
	@RequestMapping(value = "update")
	@ResponseBody
	public RoncooUserLog update() {
		RoncooUserLog entity = new RoncooUserLog();
		entity.setId(2);
		entity.setUserName("无境2");
		entity.setUserIp("192.168.0.1");
		entity.setCreateTime(new Date());
		roncooUserLogMongoDao.save(entity);
		RoncooUserLog roncooUserLog = roncooUserLogMongoDao.findOne(2);
		return roncooUserLog;
	}
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete() {
		roncooUserLogMongoDao.delete(1);
		return "删除成功";
	}
	
	@RequestMapping(value = "list")
	@ResponseBody
	public List<RoncooUserLog> list() {
		Pageable pageable = new PageRequest(0, 20, new Sort(new Order(Direction.DESC, "id")));
		// Page<RoncooUserLog> result = roncooUserLogDao.findByUserName("无境2", pageable);
		Page<RoncooUserLog> result = roncooUserLogMongoDao.findAll(pageable);
		return result.getContent();
	}
}
