package pers.micro.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pers.micro.oauth.service.CustomUserDetailsService;

/**
 * @author Gaofeng
 * @date 2021/3/20 下午9:46
 * @description: Security安全配置类
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Lazy  //解决循环依赖注入问题
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 安全拦截机制（最重要）
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/oauth/**").authenticated()
//                .and()
//                .formLogin().permitAll();

        http.headers().frameOptions().disable();
        http.headers().cacheControl();
        //允许跨域访问并关闭跨站请求防护
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                //这里表示放行oauth接口不需要权限校验,如获取token的/oauth/token
                //检验token的/oauth/check_token等
                .antMatchers("/oauth/**").permitAll()
                //放行OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
    }

    @Bean(name="customAuthenticationProvider")
    public AuthenticationProvider customAuthenticationProvider() {
        CustomAuthenticationProvider customAuthenticationProvider= new CustomAuthenticationProvider();
        customAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        customAuthenticationProvider.setHideUserNotFoundExceptions(false);
        customAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return customAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider());
    }
}
