package com.gzs.learn.springboot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AjaxResponse {
	private int code = 200;
	private String message = "success";
	private Object data;

	public AjaxResponse(Object data) {
		this(200, "success", data);
	}
}
