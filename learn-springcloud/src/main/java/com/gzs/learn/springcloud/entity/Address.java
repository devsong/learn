package com.gzs.learn.springcloud.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_user_address")
public class Address {
    @Id
    private Long id;
    private Long userId;
    private String postCode;
    private String email;
    private String phone;
    private String country;
    private String province;
    private String city;
    private String area;
    private String detailAddress;

}
