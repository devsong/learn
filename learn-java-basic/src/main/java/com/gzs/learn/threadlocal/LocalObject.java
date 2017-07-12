package com.gzs.learn.threadlocal;

import lombok.Data;

@Data
public class LocalObject {
    private SafeShareObject shareObject;

    private NotSafeShareObject notSafeShareObject;

    public LocalObject(SafeShareObject object, NotSafeShareObject notSafeShareObject) {
        this.shareObject = object;
        this.notSafeShareObject = notSafeShareObject;
    }
}
