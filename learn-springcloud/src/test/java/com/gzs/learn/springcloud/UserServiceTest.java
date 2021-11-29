package com.gzs.learn.springcloud;

import com.google.common.collect.Lists;
import com.gzs.learn.springcloud.entity.Address;
import com.gzs.learn.springcloud.entity.User;
import com.gzs.learn.springcloud.service.IUserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testAddUser() {
        List<Address> list = Lists.newArrayList();
        User user = User.builder().name("test").mainMobile("18202794850").createTime(new Date()).build();

        User u = userService.saveUser(user);
        list.add(Address.builder().postCode("010").detailAddress("test").province("010").city("010").country("cn").email("gzs@gmail.com").area("010").mobile("18202794850").createTime(new Date()).userId(u.getId()).build());
        list.add(Address.builder().postCode("010").detailAddress("test2").province("010").city("010").country("cn").email("gzs@gmail.com").area("010").mobile("18202794850").createTime(new Date()).userId(u.getId()).build());

        List<Address> addrs = userService.saveBatchAddreass(list);
        Assert.assertEquals(2, addrs.size());
    }

    @Test
    public void testGetUser() {
        long userId = 1L;
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Address> users = userService.findAllByUserId(userId, pageRequest);
        Assert.assertEquals(2, users.getTotalElements());
    }
}
