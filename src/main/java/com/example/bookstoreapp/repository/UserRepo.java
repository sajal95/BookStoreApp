package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepo extends JpaRepository<UserModel,Integer> {

    @Query("SELECT u FROM UserModel u WHERE u.email=:e")
    public UserModel getUserByEmail(@Param("e") String emailId );

}
