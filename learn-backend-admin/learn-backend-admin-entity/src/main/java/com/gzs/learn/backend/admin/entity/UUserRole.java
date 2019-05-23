package com.gzs.learn.backend.admin.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UUserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long uid;
    private Long rid;
    private Date createTime;
    private Date timestamp;

    public UUserRole(Long userId, Long rid) {
        this(userId, rid, new Date(), null);
    }
}