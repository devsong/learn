package com.gzs.learn.springboot;

import org.springframework.core.Ordered;

public class DsAop implements Ordered {

    @Override
    public int getOrder() {
        return 1;
    }

}
