package com.scop.androidmdm.userservice.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scop.androidmdm.userservice.server.entity.UserIdentity;

@Repository
public interface UserIdentityRepository extends JpaRepository<UserIdentity, Long> {

	UserIdentity findByEmailIdAndMobileNumber(String emailId, String mobileNumber);

	UserIdentity findByEmailIdOrMobileNumber(String emailId, String emailId2);

}
