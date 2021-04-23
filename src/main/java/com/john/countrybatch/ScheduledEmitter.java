package com.john.countrybatch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledEmitter {

    @Value("${file.input}")
    private String fileInput;

//    @Scheduled(initialDelay = 0, fixedRate = 3000)
    public void sayHey() {
        System.out.println(fileInput);
    }
}
