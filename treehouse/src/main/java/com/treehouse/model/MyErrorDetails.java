package com.treehouse.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MyErrorDetails {

	private LocalDateTime timestamp;
	private String message;
	private String details;

}
