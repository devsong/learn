package com.gzs.learn.springcloud.service.impl;

import com.google.common.collect.Lists;
import com.gzs.learn.springcloud.entity.Address;
import com.gzs.learn.springcloud.entity.User;
import com.gzs.learn.springcloud.repository.AddressRepository;
import com.gzs.learn.springcloud.repository.UserRepository;
import com.gzs.learn.springcloud.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Address saveAddress(Address address) {
        Address addr = addressRepository.save(address);
        return addr;
    }

    public List<Address> saveBatchAddreass(List<Address> addresses) {
        Iterable<Address> iterable = addressRepository.saveAll(addresses);
        return Lists.newArrayList(iterable);
    }

    public Page<Address> findAllByUserId(long userId, Pageable pageable) {
        return addressRepository.findByUserId(userId,pageable);
    }

}
