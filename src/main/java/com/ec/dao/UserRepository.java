package com.ec.dao;

import com.ec.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * jpa自动实现crud
 */
public interface UserRepository extends JpaRepository<User,Long>{

    User findByUsernameAndPassword(String username,String password);

}
