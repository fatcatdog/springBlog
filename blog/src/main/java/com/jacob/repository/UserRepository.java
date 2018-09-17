////@Author: Jacob Duchen
////I initially used JPA when creating this project. And then switched to JDBC. Here is part of the JPA implementation that is not currently used. 


//package com.jacob.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.jacob.model.User;
//
//@Repository("userRepository")
//public interface UserRepository extends JpaRepository<User, Long> {
// 
// User findByEmail(String email);
// User findById(int id);
//}