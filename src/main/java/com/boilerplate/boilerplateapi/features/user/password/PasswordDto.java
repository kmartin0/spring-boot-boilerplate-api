package com.boilerplate.boilerplateapi.features.user.password;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDto {
	@NotBlank
	private String password;
}
