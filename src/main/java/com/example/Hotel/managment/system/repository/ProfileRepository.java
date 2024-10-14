package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity,String> {
    Optional<ProfileEntity> findByEmail(String email);
}
