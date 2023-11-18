package com.haomeng.wisdomputian;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WisdomputianApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        //设置控制台输出的标志文字
        SpringApplication springApplication = new SpringApplication(WisdomputianApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        SpringApplication.run(WisdomputianApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //return super.configure(builder);
        return builder.sources(WisdomputianApplication.class);
    }
}
