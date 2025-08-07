package edu.hhuc.yixiang.common.lang.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/7/10 22:30:49
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(3);
        List<Thread> threadList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(parkingLot::enter, "car" + i);
            threadList.add(thread);
        }
        threadList.forEach(x -> x.start());
    }
}

class ParkingLot {
    private Semaphore semaphore;

    public ParkingLot(int permits) {
        this.semaphore = new Semaphore(permits);
    }

    public void enter() {
        try {
            semaphore.release();
            this.semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + ": 进入停车场");
            Random random = new Random();
            int seconds = 1000 * random.nextInt(10);
            Thread.sleep(seconds);
            System.out.println(Thread.currentThread().getName() + ": 停车" + seconds + "秒，已驶出停车场");
//            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}