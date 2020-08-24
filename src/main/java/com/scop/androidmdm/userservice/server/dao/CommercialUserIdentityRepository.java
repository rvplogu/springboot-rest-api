package com.scop.androidmdm.userservice.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scop.androidmdm.userservice.server.entity.CommercialUserAddress;

@Repository
public interface CommercialUserIdentityRepository extends JpaRepository<CommercialUserAddress, Long> {
}
