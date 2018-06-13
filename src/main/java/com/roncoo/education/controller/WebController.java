/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.roncoo.education.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.bean.RoncooUser;
import com.roncoo.education.component.RoncooMongodbComponent;

/**
 * @author wujing
 */
@Controller
@RequestMapping(value = "/web")
public class WebController {
	
	@Autowired
	private RoncooMongodbComponent roncooMongodbComponent;

	@RequestMapping(value = "index")
	public String index(ModelMap map) {
		map.put("title", "freemarker hello word");
		return "index"; // 开头不要加上/，linux下面会出错
	}

	@RequestMapping(value = "error")
	public String error(ModelMap map) {
		throw new RuntimeException("测试异常");
	}
	
	@RequestMapping(value = "save")
	@ResponseBody
	public String save() {
		RoncooUser roncooUser = new RoncooUser();
		roncooUser.setId(1);
		roncooUser.setName("无境1");
		roncooUser.setCreateTime(new Date());
		roncooMongodbComponent.insert(roncooUser);
		return "save ok";
	}

	@RequestMapping(value = "get")
	@ResponseBody
	public RoncooUser get() {
		RoncooUser roncooUser = roncooMongodbComponent.selectById(1);
		return roncooUser;
	}
	
	@RequestMapping(value = "update")
	@ResponseBody
	public RoncooUser update() {
		RoncooUser roncooUser = new RoncooUser();
		roncooUser.setId(1);
		roncooUser.setName("测试修改");
		roncooUser.setCreateTime(new Date());
		roncooMongodbComponent.updateById(roncooUser);
		RoncooUser roncooUser1 = roncooMongodbComponent.selectById(1);
		return roncooUser1;
	}
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete() {
		roncooMongodbComponent.deleteById(1);
		return "删除成功";
	}
}
