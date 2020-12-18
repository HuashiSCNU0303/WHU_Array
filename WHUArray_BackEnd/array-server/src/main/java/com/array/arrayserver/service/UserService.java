package com.array.arrayserver.service;

import com.array.arrayserver.client.StudentClientFeign;
import com.array.arrayserver.client.TeacherClientFeign;
import com.array.commonmodule.bean.Student;
import com.array.commonmodule.bean.Teacher;
import com.array.commonmodule.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    StudentClientFeign studentClientFeign;
    @Autowired
    TeacherClientFeign teacherClientFeign;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentClientFeign.findStudentByName(username);
        Teacher teacher = teacherClientFeign.findTeacherByName(username);
        if(student != null) {
            return student;
        }
        else if(teacher != null) {
            return teacher;
        }
        else {
            return new User();
        }
    }
}
