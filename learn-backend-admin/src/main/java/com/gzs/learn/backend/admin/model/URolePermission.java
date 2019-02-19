package com.gzs.learn.backend.admin.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class URolePermission implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long rid;
    private Long pid;

    public URolePermission() {
    }
}