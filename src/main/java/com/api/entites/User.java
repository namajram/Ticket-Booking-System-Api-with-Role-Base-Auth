package com.api.entites;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "order" })
public class User {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(nullable = false)
private long userId;

@Column(nullable = false,length = 50)
private String name;

@Column(nullable = false,unique = true)
private String email;

@Column(nullable = false)
@JsonIgnore
private String password;

@Enumerated(EnumType.STRING)
@Column(nullable = false)
@JsonIgnore
private UserRole roleName;

@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
private List<Order> order  = new ArrayList<>();





}
