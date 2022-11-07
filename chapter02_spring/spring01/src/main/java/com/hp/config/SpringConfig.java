package com.hp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-07 12:13
 */
@Configuration
@ComponentScan("com.hp")
@Import(JdbcConfig.class)
public class SpringConfig {

}
