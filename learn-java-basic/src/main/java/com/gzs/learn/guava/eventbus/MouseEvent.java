package com.gzs.learn.guava.eventbus;

import lombok.Data;

@Data
public class MouseEvent {
    private int index;
    private String name = "mouse-event";
}
