package com.xue.concurrency.container;

import com.xue.concurrency.annotations.NotThreadSafe;

import java.util.Vector;

/** Vector虽然是线程安全的容器，但是可能会因为两个同步方法顺序导致线程安全问题
 * Created by Mingway on 2019/3/26.
 */
@NotThreadSafe
public class VectorTest01 {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            });
            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.get(i);
                }
            });

            thread1.start();    //会出现数组越界的异常
            thread2.start();
        }

    }
}
