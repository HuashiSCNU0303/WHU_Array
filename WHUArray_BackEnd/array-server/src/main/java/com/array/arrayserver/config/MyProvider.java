package com.array.arrayserver.config;

import com.array.commonmodule.bean.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

public class MyProvider extends DaoAuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        Authentication authentication1 = super.authenticate(authentication);
        System.out.println("b::" + authentication1);
        if(((User)authentication1.getPrincipal()) == null) {
            return null;
        }
        else {
            return authentication1;
        }
    }
}
