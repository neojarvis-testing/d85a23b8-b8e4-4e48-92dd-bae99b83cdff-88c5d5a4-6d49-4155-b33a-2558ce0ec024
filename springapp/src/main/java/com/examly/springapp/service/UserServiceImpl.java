package com.examly.springapp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.examly.springapp.exception.PasswordNotMatchedException;
import com.examly.springapp.exception.UserAlreadyExistException;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder encoder;

    public User registration(User user) {

        if (userRepo.findByEmail(user.getEmail()) != null)

            throw new UserAlreadyExistException("User with Same email:" + user.getEmail() + " already exists!!");

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User loginUser(User user) {
        User userexist = userRepo.findByEmail(user.getEmail());
        if (userexist == null) {

             System.out.println("User not found");
        }

        if (!encoder.matches(user.getPassword(), userexist.getPassword()))
            throw new PasswordNotMatchedException("Password is Incorrect!");

        return userexist;
    }

}
