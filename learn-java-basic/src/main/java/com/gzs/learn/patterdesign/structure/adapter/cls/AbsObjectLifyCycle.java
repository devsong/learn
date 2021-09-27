package com.gzs.learn.patterdesign.structure.adapter.cls;

public abstract class AbsObjectLifyCycle implements ObjectLifeCycle {

    @Override
    public void preInit() {
        // default do nothing
    }

    @Override
    public void init() {
        // default do nothing
    }

    @Override
    public void preDestroy() {
        // default do nothing
    }

    @Override
    public void destroy() {
        // default do nothing
    }
}
