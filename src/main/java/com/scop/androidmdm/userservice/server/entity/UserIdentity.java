package com.scop.androidmdm.userservice.server.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserIdentity")
public class UserIdentity implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String emailId;
	private String mobileNumber;
	private byte[] salt;
	private byte[] hash;
	private String firstName;
	private String lastName;
	private String businessType;
	private Timestamp createdOn;
	private String createdBy;
	private String updatedBy;
	private Timestamp updatedOn;
	private boolean otpValidated;
	private boolean mailValidated;
	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleIdentity roleEntity;
}
