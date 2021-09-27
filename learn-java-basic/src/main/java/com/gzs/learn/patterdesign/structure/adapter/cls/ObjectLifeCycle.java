package com.gzs.learn.patterdesign.structure.adapter.cls;

public interface ObjectLifeCycle {

    void preInit();

    void init();

    void preDestroy();

    void destroy();
}
