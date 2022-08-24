package com.example.bookatable.Service;
import com.example.bookatable.Model.User;
import com.example.bookatable.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public void register(User user){
        String hashedPassword= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }
    public User getByUserId(User user) {
        return this.userRepository.findUserById(user.getId());
    }

    public boolean editUser(User user, Integer id) {
        User oldUser=userRepository.findUserById(id);
        oldUser.setUsername(user.getUsername());
        String hashedPassword= new BCryptPasswordEncoder().encode(user.getPassword());
        oldUser.setPassword(hashedPassword);
        oldUser.setEmail(user.getEmail());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(oldUser);
        return true;
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }


}
