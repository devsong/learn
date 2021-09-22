package com.gzs.learn.patterdesign.create.factory.concrete;

import com.gzs.learn.patterdesign.create.factory.Action;

public class Action4Dog implements Action {

    @Override
    public String actionToString() {
        return "eat meat";
    }
}
