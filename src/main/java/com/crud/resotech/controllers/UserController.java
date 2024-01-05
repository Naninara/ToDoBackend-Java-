package com.crud.resotech.controllers;

import com.crud.resotech.models.UserModel;
import com.crud.resotech.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
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

        repo.save(data);
        return ResponseEntity.status(201).body("User Created");
    }

    @PostMapping("/login/signin")
    public ResponseEntity<Object> signin(@RequestBody UserModel requestData){
        try{

        UserModel FoundUserData = repo.findByUsername(requestData.getUsername());
        if(FoundUserData==null){
            return ResponseEntity.status(400).body("No User Found With That User Name");
        }

        if(!FoundUserData.getPassword().equals(requestData.getPassword())){
            return ResponseEntity.status(400).body("In Valid Password");

        }
            return ResponseEntity.status(200).body(FoundUserData);

        }
        catch (Exception e){
            return ResponseEntity.status(400).body("Bad Request");
        }


    }

    @GetMapping("/login/test")
    public String test(@RequestParam String id,@RequestParam String status){
        System.out.println(id+" "+status);
        return "ok";
    }

}
