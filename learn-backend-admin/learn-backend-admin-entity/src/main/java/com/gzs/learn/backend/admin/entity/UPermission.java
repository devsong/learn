package com.gzs.learn.backend.admin.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UPermission implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String url;
	private String name;
}