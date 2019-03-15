package com.gzs.learn.backend.admin.permission.bo;

import java.io.Serializable;

import com.gzs.learn.backend.admin.entity.URole;
import com.gzs.learn.backend.admin.utils.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class URoleBo extends URole implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID (用String， 考虑多个ID，现在只有一个ID)
	 */
	private String userId;
	/**
	 * 是否勾选
	 */
	private String marker;

	public boolean isCheck(){
		return StringUtils.equals(userId,marker);
	}
	
}
