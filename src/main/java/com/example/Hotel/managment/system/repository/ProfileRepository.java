package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.dto.ForgotPasswordRequestDTO;
import com.example.Hotel.managment.system.entity.ProfileEntity;
import com.example.Hotel.managment.system.enums.ProfileRole;
import com.example.Hotel.managment.system.enums.ProfileStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity,String> {
    Optional<ProfileEntity> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update ProfileEntity set status=?2 where id=?1")
    void updateStatus(String id, ProfileStatus profileStatus);

    Optional<ProfileEntity> findByEmailAndPassword(String email, String encode);

    Collection<Object> findByRole(ProfileRole profileRole);

    @Query("from ProfileEntity where email=?1 and role=?2")
    ProfileEntity findByEmailAndRole(String email, String role);
/*
    @Modifying
    @Transactional
    @Query("update ProfileEntity set visible='false'  where id=?1")
    void updateVisible(ProfileEntity profile);*/

}
