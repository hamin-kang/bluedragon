package com.bluedragontrain.bluedragon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BluedragonApplication { // bluedragon 프로젝트의 메인 클래스
    public static void main(String[] args) {
        SpringApplication.run(BluedragonApplication.class, args);
    }
}