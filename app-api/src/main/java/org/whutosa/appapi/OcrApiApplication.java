package org.whutosa.appapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

/**
 * @author bobo
 */

@SpringBootApplication(scanBasePackages = "org.whutosa")
public class OcrApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OcrApiApplication.class, args);

    }

}
