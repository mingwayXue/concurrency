package com.xue.concurrency.publish;

import com.xue.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/** 发布对象
 * Created by Mingway on 2019/3/25.
 */
@Slf4j
@NotThreadSafe
public class UnSafePublish {
    private String[] a = {"a", "b", "c"};

    public String[] getA() {
        return this.a;
    }

    public static void main(String[] args) {
        UnSafePublish unSafePublish = new UnSafePublish();

        log.info(Arrays.toString(unSafePublish.a));

        unSafePublish.a[0] = "d";   //不安全的发布对象，容易造成线程不安全的问题

        log.info(Arrays.toString(unSafePublish.a));
    }
}
