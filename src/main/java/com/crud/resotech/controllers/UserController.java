package com.crud.resotech.controllers;

import com.crud.resotech.models.UserModel;
import com.crud.resotech.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository repo;

    @PostMapping("/login/signup")
    public ResponseEntity<String> signup(@RequestBody UserModel requestData){

        UserModel findIfExist = repo.findByUsername(requestData.getUsername());
        if(findIfExist!=null){
            return  ResponseEntity.status(409).body("User Already Exists");
        }

        UserModel data = new UserModel(requestData.getUsername(),requestData.getPassword(),requestData.getEmail());
        data.setRoles("user");
        return ResponseEntity.status(201).body("User Created");
    }

    @PostMapping("/login/signin")
    public ResponseEntity<Object> signin(@RequestBody UserModel requestData){
        UserModel FoundUserData = repo.findByUsername(requestData.getUsername());
        if(FoundUserData==null){
            return ResponseEntity.status(400).body("No User Found With That User Name");
        }

        if(!FoundUserData.getPassword().equals(requestData.getPassword())){
            return ResponseEntity.status(400).body("In Valid Password");

        }
        return ResponseEntity.status(200).body(FoundUserData);

    }

}
