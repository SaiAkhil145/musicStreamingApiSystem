package com.musicApp.musicStreamingApiSystem.Repository;

import com.musicApp.musicStreamingApiSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {


    User findFirstByUserEmail(String userEmail);

    User findFirstByEmail(String email);
}
