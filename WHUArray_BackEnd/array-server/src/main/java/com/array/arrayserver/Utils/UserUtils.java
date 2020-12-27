package com.array.arrayserver.Utils;

import com.array.arrayserver.service.UserService;
import com.array.commonmodule.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author yee
 */
@Component
public class UserUtils {
    private static UserService userService;
    @Autowired(required = true)
    public void setDocUserService(UserService userService) {
        UserUtils.userService = userService;
    }
    public static User getCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = (User)userService.loadUserByUsername(name);
        return user;
    }

}
