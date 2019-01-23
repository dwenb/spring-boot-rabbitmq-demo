package com.example.demo;

import com.example.demo.produce.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {
    static int count = 0;
    @Autowired
    private Message message;


    @Test
    public void contextLoads() {
    }


    @Test
    public void message() {
        long begin = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            RunnableThread runnableThread = new RunnableThread();
            executorService.execute(runnableThread);
        }

        long time = System.currentTimeMillis() - begin;
        System.out.println("花费时间(毫秒)：" + time);
    }

    class RunnableThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                message.send();
                System.out.println(++count);
            }
        }
    }
}

