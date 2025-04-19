package com.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class User 
{
	@Id

	private String uid;
	private String name;
	private String password;
	@Column(unique=true)
	private String email;
	
	public boolean enable=true;

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
				joinColumns = @JoinColumn(name="user_id"),
				inverseJoinColumns = @JoinColumn(name="role_id"))
//	 @JsonManagedReference
	@JsonIgnoreProperties("users")
	private Set<Role> roles;
	

	
//	@JsonManagedReference
//@OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//private List<Role> roles;

	

}

/*
  { "error": "Internal Server Error", "message":
  "Content-Type 'application/json;charset=UTF-8' is not supported" }
  
  ye error aane ka reason sirf ye annotation hai isko comment karte hi save
  hone laga data
  
  @JsonManagedReference
 */