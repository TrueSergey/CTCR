package by.vstu.isap.zamok.lab5.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@EnableWebMvc
@ImportResource("classpath:spring-jpa.xml")
@ComponentScan("by.vstu.isap.zamok.lab5")
public class WebAppConfig extends WebMvcConfigurerAdapter {

}
