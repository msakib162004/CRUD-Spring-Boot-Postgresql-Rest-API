package com.crud.usercrud.controller;
import com.crud.usercrud.model.User;
import com.crud.usercrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //private UserService userService;
    @GetMapping("usersInfo")
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }


    @PostMapping("create")
    public User createUser(@RequestBody @Valid User user){
        return this.userRepository.save(user);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(name = "id") long userId,
                                           @RequestBody @Valid User userDetails){
        User user = userRepository.findById(userId).get();

        user.setName(userDetails.getName());
        user.setAddress(userDetails.getAddress());
        user.setDob(userDetails.getDob());
        user.setEmail(userDetails.getEmail());
        user.setGender(userDetails.getGender());

        return ResponseEntity.ok(this.userRepository.save(user));

    }

    @DeleteMapping("user/{id}")
    public String deleteUser(@PathVariable(name = "id") long id){
        this.userRepository.deleteById(id);
        return "Deleted Successfully";
    }

}
