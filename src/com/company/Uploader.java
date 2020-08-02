package com.company;

import java.util.concurrent.CountDownLatch;

public class Uploader extends Thread {
    private CountDownLatch cdl;
    private int sizeOfFile = 500;
    private int speedOfDownload = 20;
    private int downloadOfSize = 0;
    private int sleepTime = sizeOfFile / speedOfDownload * 100;

    public Uploader(CountDownLatch countDownLatch) {
        this.cdl = countDownLatch;
        start();
    }

    public int getDownloadOfSize() {
        return downloadOfSize;
    }
    public void setDownloadOfSize(int downloadSize) {
        this.downloadOfSize = downloadSize;
    }

    @Override
    public synchronized void run() {
        System.out.println("Загрузка на сервер пошла!");
        try {
            for (int i = 1; i < 11; i++) {
                setDownloadOfSize(getDownloadOfSize()+50);
                System.out.println("Скачано "+ i*10 + "% " + getDownloadOfSize() + " мгб");
                sleep(sleepTime);
            }
        } catch (InterruptedException ignored) {
        }
        cdl.countDown();
    }
}
