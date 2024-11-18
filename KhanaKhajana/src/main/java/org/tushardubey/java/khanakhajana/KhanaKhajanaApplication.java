package org.tushardubey.java.khanakhajana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class KhanaKhajanaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KhanaKhajanaApplication.class, args);
    }

}
