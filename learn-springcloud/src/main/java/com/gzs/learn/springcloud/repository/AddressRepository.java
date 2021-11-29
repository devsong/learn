package com.gzs.learn.springcloud.repository;

import com.gzs.learn.springcloud.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,Long> {

    @Query(value = "select * from t_user where user_id =: userId",nativeQuery = true)
    Page<Address> findByUserId(long userId, Pageable pageable);
}
