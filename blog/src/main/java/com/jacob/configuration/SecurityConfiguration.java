//@Author: Jacob Duchen
//Spring Security is still a bit of a mystery to me be honest. Boilerplate login and signup can be acheived by using these common configuration settings. But I do not really understand what is happening under the hood here.

package com.jacob.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

 @Autowired
 private BCryptPasswordEncoder bCryptPasswordEncoder;

 @Autowired
 private DataSource dataSource;

 private final String usersQuery = "select email, password, active from bloguser where email=?";
 private final String rolesQuery = "select u.email, r.role from bloguser u inner join user_role ur on (u.id = ur.user_id) inner join role r on (ur.role_id=r.role_id) where u.email=?";


 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  auth.jdbcAuthentication()
   .usersByUsernameQuery(usersQuery)
   .authoritiesByUsernameQuery(rolesQuery)
   .dataSource(dataSource)
   .passwordEncoder(bCryptPasswordEncoder);
 }

 @Override
 protected void configure(HttpSecurity http) throws Exception{
  http.authorizeRequests()
   .antMatchers("/").permitAll()
   .antMatchers("/login").permitAll()
   .antMatchers("/signup").permitAll()
   .antMatchers("/css/**").permitAll()
   .antMatchers("/home/**").hasAuthority("ADMIN").anyRequest()
   .authenticated().and().csrf().disable()
   .formLogin().loginPage("/login").failureUrl("/login?error=true")
   .defaultSuccessUrl("/home")
   .usernameParameter("email")
   .passwordParameter("password")
   .and().logout()
   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
   .logoutSuccessUrl("/")
   .and().rememberMe()
   .tokenRepository(persistentTokenRepository())
   .tokenValiditySeconds(60*60)
   .and().exceptionHandling().accessDeniedPage("/access_denied");
  
 }

 @Bean
 public PersistentTokenRepository persistentTokenRepository() {
  JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
  db.setDataSource(dataSource);
  return db;
 }
}
