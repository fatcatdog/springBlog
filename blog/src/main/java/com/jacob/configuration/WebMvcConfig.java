//@Author: Jacob Duchen
 //Bcrypt for our Spring Security system. Can't save plain passwords in our db!
//Just like Spring Security, WebMvcConfigurer probably impliments a ton of methods that I am unaware of and still need to understand. 

package com.jacob.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
 @Bean
 public BCryptPasswordEncoder passwordEncoder() {
  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
  return bCryptPasswordEncoder;
 }
 
}