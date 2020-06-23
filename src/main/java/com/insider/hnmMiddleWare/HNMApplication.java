package com.insider.hnmMiddleWare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by raman on 12/06/20
 */
@SpringBootApplication(exclude = JmxAutoConfiguration.class)
@ComponentScan({"com.insider.hnmMiddleWare"})
public class HNMApplication {

  public static void main(String[] args) {

     SpringApplication.run(HNMApplication.class, args);

  }
}
