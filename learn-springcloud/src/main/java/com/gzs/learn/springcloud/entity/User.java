package com.gzs.learn.springcloud.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "t_user")
public class User {
    @Id
    private  Long id;
    private String name;
    private String mainPhone;
    @OneToMany()
    @JoinColumn()
    private List<Address> addresses;
}
