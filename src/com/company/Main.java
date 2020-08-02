package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        CountDownLatch cd1 = new CountDownLatch(1);
        CountDownLatch cdu = new CountDownLatch(10);
        Semaphore semaphore = new Semaphore(3,true);


        new Uploader(cd1);
        for (int i = 1; i <= 10; i++) {
            new Downloader("Человек  " +i, semaphore,
                    cd1, cdu);
        }

        try {
            cdu.await();
            System.out.println("Файл удалился из сервера");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
