package com.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Role 
{
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//	@JsonBackReference
	@JsonIgnoreProperties("roles")
	private Set<User> users;

//	@JsonBackReference
//	 @ManyToOne(fetch = FetchType.EAGER)
//	 @JoinColumn(name = "uid", referencedColumnName = "uid", foreignKey = @ForeignKey(name = "fk_dId"))
//	private User user;
}


