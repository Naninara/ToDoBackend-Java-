package com.crud.resotech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class ToDoModel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int Id;


    @NotBlank(message = "Name Should not empty")
    String name;

    @NotBlank(message = "Start Should not empty")
    String start;

    @NotBlank(message = "End Should not empty")
    String end;

    @NotBlank(message = "Status Should not empty")
    String status;


    int createdby;




    public ToDoModel(String name, String start, String end, String status,int createdby) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.status = status;
        this.createdby=createdby;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getCreatedby() {
        return createdby;
    }

    public void setCreatedby(int createdby) {
        this.createdby = createdby;
    }

    public ToDoModel() {
    }
}
