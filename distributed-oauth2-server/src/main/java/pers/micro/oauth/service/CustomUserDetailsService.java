package pers.micro.oauth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Gaofeng
 * @date 2021/3/24 下午5:29
 * @description: 继承原来的UserDetailsService新增自定义方法
 */
public interface CustomUserDetailsService extends UserDetailsService {

    UserDetails loadUserByUsername(String var1, String var2) throws UsernameNotFoundException;
}
