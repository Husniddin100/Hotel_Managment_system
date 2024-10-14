package com.example.Hotel.managment.system.repository;

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
}
