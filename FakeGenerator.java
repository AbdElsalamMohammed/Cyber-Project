package com;

import java.util.Random;

public class FakeGenerator implements Runnable {

       private Random rand = new Random();

        public void run()
             //@Override  run method
        {
        try {
            while (true) {
                // Generate random integers in range 0 to 9999
                int rand_int1 = rand.nextInt(10000);
                System.out.println(rand_int1);
                // generate random sleeping intervals form 0 to 5 seconds
                Thread.sleep((long)(Math.random() *5000));

            }
        }
        catch (Exception e){}
        }//end of run().

    public static void main(String[] args) throws InterruptedException
    {

        Thread MyThread = new Thread(new FakeGenerator());
        MyThread.start();
    }
}//end of FakeGenerator.
