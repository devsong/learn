package com.gzs.learn.backend.admin.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UUserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long uid;
    private Long rid;
}