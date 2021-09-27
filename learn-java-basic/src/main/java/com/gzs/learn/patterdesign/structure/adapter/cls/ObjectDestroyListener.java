package com.gzs.learn.patterdesign.structure.adapter.cls;

public class ObjectDestroyListener extends AbsObjectLifyCycle {

    private ConcreteTargetDestroy destroy = new ConcreteTargetDestroy();

    @Override
    public void destroy() {
        super.destroy();
        destroy.remove();
    }
}
