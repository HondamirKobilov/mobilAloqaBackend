package com.example.mobilaloqakompaniyabackend.SETTINGS;

import com.example.mobilaloqakompaniyabackend.SERVICE.ServiceImplements;
import com.example.mobilaloqakompaniyabackend.TOKEN.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Properties;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class XafsizlikSozlamalari extends WebSecurityConfigurerAdapter {
    @Autowired
    ServiceImplements serviceImplements;
    @Autowired
    Filter filter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/kompaniyaApi/kompaniyaAdd").permitAll()
                .antMatchers("/filialApi/filialAdd/{kompaniyaId}").permitAll()
                .antMatchers("/xodimApi/registr").permitAll()
                .antMatchers("/simkartaKodApi/simkartaKodAdd").permitAll()
                .antMatchers("/shaxlarApi/shaxslarAdd").permitAll()
                .antMatchers("/paketlarApi/paketlarAdd").permitAll()
                .antMatchers("/tariflarApi/tariflarAdd").permitAll()
                .antMatchers("/simkartaApi/simkartaAdd/{{simkartaTurId}}/{{shaxslarId}}/{{tariflarId}}").permitAll()
                .antMatchers("/xodimApi/tasdiqlash").permitAll()
                .antMatchers("/xodimApi/xodimlogin").permitAll()
                .antMatchers("/xizmatlarApi/xizmatlarAdd").permitAll()
                .antMatchers("/ussdApi/ussdAdd").permitAll()
                .antMatchers("/tulovApi/simkartaAdd/{{simkartaId}}").permitAll()
                .antMatchers("/simkartaIDAndXizmatlarIDApi/simkartaIDAndXizmatlarIDAdd/{{xizmatId}}/{{simkartaId}}").permitAll()
                .antMatchers("/simkartaApi/daqiqaIshlatish/{simkartaId}").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .disable();
        http
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("hondamirkobilov02@gmail.com");
        mailSender.setPassword("oiixbitsqbivjznn");
        Properties properties=mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.debug","true");
        return mailSender;

    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(serviceImplements)
                .passwordEncoder(passwordEncoder());

    }
}
