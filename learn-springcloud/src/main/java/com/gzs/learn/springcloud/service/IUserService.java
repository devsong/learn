package com.gzs.learn.springcloud.service;

import com.gzs.learn.springcloud.entity.Address;
import com.gzs.learn.springcloud.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    boolean saveUser(User user);

    boolean saveAddress(Address address);

    boolean saveBatchAddreass(List<Address> addresses);

    Page<Address> findAllByUserId(long userId, Pageable pageable);
}
