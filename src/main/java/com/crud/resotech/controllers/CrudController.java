package com.crud.resotech.controllers;

import com.crud.resotech.models.ToDoModel;
import com.crud.resotech.repository.ToDoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class CrudController {

    @Autowired
    ToDoRepository repo;

    @GetMapping("/")
    public static String hi(){
        return "hiiii";
    }

    @PostMapping(value = "/user/addtask")
    public ResponseEntity<String> add(@Valid @RequestBody ToDoModel input)
    {

        try{
        ToDoModel model= new ToDoModel(input.getName(),input.getStart(),input.getEnd(),input.getStatus(),input.getCreatedby());
        repo.save(model);

        return new ResponseEntity<>("Task Created",HttpStatus.CREATED);
        }
        catch (Exception e){

            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/getalltasks")
    public List<ToDoModel> getall(){

        return repo.findAll();
    }



    @GetMapping(value = "/user/getsingle/{id}")
    public ToDoModel getSingleData(@PathVariable int id){
        return repo.findById(id).get();
    }

    @PatchMapping("/user/update")
    public ResponseEntity<String> update(@RequestBody ToDoModel input){
        try{
            repo.save(input);
            return ResponseEntity.status(201).body("Updated Successsfully");
        }
        catch (Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }

    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        try {
            repo.deleteById(id);
            return ResponseEntity.status(200).body("Deleted Sucessfully");
        }
        catch (Exception e){

            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/user/get")
    public List<ToDoModel> findByStatus(@RequestParam int id,@RequestParam String status){

        return repo.findByStatus(status,id);
    }
}
