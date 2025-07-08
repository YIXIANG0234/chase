package edu.hhuc.yixiang.common.lang.demo;

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            int a = 0;
            while (!ready) {
                a = a + 1;
                try {
//                    Thread.sleep(2);
                    if (a % 50 == 0) {
                        System.out.println("run ... ...");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws Exception {
        new ReaderThread().start();
        Thread.sleep(1000L);
        number = 42;
        ready = true;
    }
}