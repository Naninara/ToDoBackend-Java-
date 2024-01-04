package com.crud.resotech.repository;

import com.crud.resotech.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {
    public UserModel findByUsername(String username);
}
