package com.zipcode.socialStream.repositories;

import com.zipcode.socialStream.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, String> {
    Users findByUsername(String username);
}
