package com.acupt.dao;

import com.acupt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by liujie on 2017/8/16.
 */
public interface UserDAO extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "select * from user where name = ?")
    User getByName(String name);
}
