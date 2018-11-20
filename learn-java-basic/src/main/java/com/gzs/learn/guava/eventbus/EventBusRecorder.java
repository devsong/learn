package com.gzs.learn.guava.eventbus;

import java.util.concurrent.atomic.AtomicLong;

import com.google.common.eventbus.Subscribe;

// @Slf4j
public class EventBusRecorder {
    private int index;
    private AtomicLong atomicIndex = new AtomicLong(0);

    @Subscribe
    public void recordMouseEvent(MouseEvent event) {
        // log.info("recv change msg:{}", event);
        index++;
        atomicIndex.incrementAndGet();
    }

    @Subscribe
    public void recordBtnEvent(BtnEvent event) {
        // log.info("recv btn msg:{}", event);
        index++;
        atomicIndex.incrementAndGet();
    }

    public int getIndex() {
        return index;
    }

    public int getAtomicIndex() {
        return (int) atomicIndex.get();
    }
}
