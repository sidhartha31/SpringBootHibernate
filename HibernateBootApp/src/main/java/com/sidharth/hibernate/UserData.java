package com.sidharth.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="userdata")
public class UserData {

	@Id
	//@GeneratedValue
	@Column(name="id")
	private int userID;
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private String userPass;
}
