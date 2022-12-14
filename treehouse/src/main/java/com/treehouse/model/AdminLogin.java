package com.treehouse.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class AdminLogin {
	
	@Id
	private Integer adminId;
	private String key;
	private LocalDateTime timestamp;
	

}
