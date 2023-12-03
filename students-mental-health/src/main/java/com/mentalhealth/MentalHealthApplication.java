package com.mentalhealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MentalHealthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(MentalHealthApplication.class, args);
    }
}