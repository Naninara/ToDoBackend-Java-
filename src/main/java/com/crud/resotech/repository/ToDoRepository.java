package com.crud.resotech.repository;

import com.crud.resotech.models.ToDoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Repository
public interface ToDoRepository extends JpaRepository<ToDoModel,Integer> {

    @Query("select b from ToDoModel b where b.status=:status and b.createdby =:id")
    public List<ToDoModel> findByStatus(String status,int id);

}
