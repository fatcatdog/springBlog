//@Author: Jacob Duchen

package com.jacob.model;

import java.util.Set;

public class User {
 

 private int id;
 
 private String email;
 
 private String firstname; 
 
 private String lastname;
 
 private String password;
 
 private int active;
 
 private Set<Role> roles;

 public User(int id, String email, String firstname, String lastname, String password, int active, Set<Role> roles) {
	super();
	this.id = id;
	this.email = email;
	this.firstname = firstname;
	this.lastname = lastname;
	this.password = password;
	this.active = active;
	this.roles = roles;
}
 
 public User(int id, String email, String firstname, String lastname, String password, int active) {
	super();
	this.id = id;
	this.email = email;
	this.firstname = firstname;
	this.lastname = lastname;
	this.password = password;
	this.active = active;
}
 
 public User(int id, String email, String firstname, String lastname, String password) {
	super();
	this.id = id;
	this.email = email;
	this.firstname = firstname;
	this.lastname = lastname;
	this.password = password;
}

public User() {
	super();
}



public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getFirstname() {
  return firstname;
 }

 public void setFirstname(String firstname) {
  this.firstname = firstname;
 }

 public String getLastname() {
  return lastname;
 }

 public void setLastname(String lastname) {
  this.lastname = lastname;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public int getActive() {
  return active;
 }

 public void setActive(int active) {
  this.active = active;
 }

 public Set<Role> getRoles() {
  return roles;
 }

 public void setRoles(Set<Role> roles) {
  this.roles = roles;
 }
}