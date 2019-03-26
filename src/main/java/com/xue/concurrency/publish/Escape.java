package com.xue.concurrency.publish;

import com.xue.concurrency.annotations.NotRecommend;
import com.xue.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/** 对象溢出，即在一个对象未完成构造，就调用它（特别是多线程下）
 *  发布对象时，一定要在对象完成构造后，才发布
 * Created by Mingway on 2019/3/25.
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {
    private int a = 0;

    public Escape() {
        new innerClass();
    }

    private class innerClass{
        public innerClass() {
            log.info("{}", Escape.this.a);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
