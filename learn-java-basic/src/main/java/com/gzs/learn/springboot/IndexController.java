package com.gzs.learn.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping
@Slf4j
public class IndexController {

	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	@RequestMapping("ajax")
	@ResponseBody
	public AjaxResponse ajax(@RequestBody ReqData d) {
		log.info("recv client data:{}", d);
		return new AjaxResponse(200, "success", Lists.newArrayList(1, 2, 3));
	}

	@Data
	static class ReqData {
		private String k1;
		private String k2;
	}
}
