package com.study.daniil.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.study.daniil.repository", "com.study.daniil.service", "com.study.daniil.util"})
public class ContextConfig {
}
