package pers.micro.oauth.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Gaofeng
 * @date 2021/3/30 下午4:33
 * @description: 可以根据传递的多个参数来判定登录方式
 */
@Service
public class CustomUserDetailsService implements pers.micro.oauth.service.CustomUserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username, String loginType) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
