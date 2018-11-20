package com.gzs.learn.guava.eventbus;

import lombok.Data;

@Data
public class BtnEvent {
    private int index;
    private String name = "btn-event";
}
