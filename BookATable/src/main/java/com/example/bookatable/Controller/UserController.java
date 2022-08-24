package com.example.bookatable.Controller;

import com.example.bookatable.Model.Api;
import com.example.bookatable.Model.User;
import com.example.bookatable.Service.UserService;
import com.example.bookatable.gto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    //get all users
    //@GetMapping("admin/all")
    @GetMapping("/all")
    public ResponseEntity<List> getUsers(@AuthenticationPrincipal User user) {
        if(user.getRole().equals("admin")){
        List<User> users=userService.getUsers();
        return ResponseEntity.status(200).body(users);}
        else{
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        userService.register(user);
        return ResponseEntity.status(201).body(new ApiResponse("New user added !",201));
    }
    @GetMapping("/admin")
    public ResponseEntity admin(){
        return ResponseEntity.status(200).body(new Api("Hello Admin",200));
    }
    @GetMapping("/user")
    public ResponseEntity user(){
        return ResponseEntity.status(200).body(new Api("Hello User",200));
    }

    @GetMapping("/details")
    public ResponseEntity<User> getByUserId(@AuthenticationPrincipal User user1) {
        User user2=userService.getByUserId(user1);
        return ResponseEntity.status(200).body(user2);
    }

    //update user not boolean
    @PutMapping("/edit")
    ResponseEntity<Api> editUsers(@RequestBody @Valid User user1,@AuthenticationPrincipal User user) {
        boolean  isValid = userService.editUser(user1,user.getId());
        if (!isValid)
            return ResponseEntity.status(400).body(new Api("Invalid UserID",400 ));
        else
            return ResponseEntity.status(200).body(new Api("User is updated",200));
    }
    @DeleteMapping("/delete")
    ResponseEntity<Api> deleteUserById(@AuthenticationPrincipal User user) {
        userService.removeUser(user);
        return ResponseEntity.status(200).body(new Api("User "+user.getId()+" deleted",200));}


}



