package com.gzs.learn.springcloud.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "t_user_address")
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    private static final long serialVersionUID = 0L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    private String country;
    private String province;
    private String city;
    private String area;
    @Column(name = "detail_address")
    private String detailAddress;
    @Column(name = "post_code")
    private String postCode;
    private String email;
    private String mobile;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
}
