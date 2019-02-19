package com.gzs.learn.backend.admin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class URole  implements Serializable{
	private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String type;
    private List<UPermission> permissions = new ArrayList<UPermission>();
}