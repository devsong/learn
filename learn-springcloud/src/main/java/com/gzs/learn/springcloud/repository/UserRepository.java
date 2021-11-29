package com.gzs.learn.springcloud.repository;

import com.gzs.learn.springcloud.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
