package com.boilerplate.boilerplateapi.features.user;

import com.boilerplate.boilerplateapi.features.user.password.PasswordDto;
import com.boilerplate.boilerplateapi.features.user.password.change.ChangePasswordDto;
import com.boilerplate.boilerplateapi.features.user.password.forgot.ForgotPasswordDto;
import com.boilerplate.boilerplateapi.features.user.password.reset.ResetPasswordDto;
import com.boilerplate.boilerplateapi.utils.Endpoints;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

	private final IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping(path = Endpoints.SAVE_USER, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@Validated({User.Create.class}) @RequestBody User user) {

		return userService.saveUser(user);
	}

	@GetMapping(path = Endpoints.GET_USER)
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public User getAuthenticatedUser() {

		return userService.getAuthenticatedUser();
	}

	@PutMapping(path = Endpoints.UPDATE_USER)
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public User updateUser(@Validated({User.Update.class}) @RequestBody User user) {

		return userService.updateUser(user);
	}

	@DeleteMapping(path = Endpoints.DELETE_USER)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("isAuthenticated()")
	public void deleteUser(@Valid @RequestBody PasswordDto passwordDto) {

		userService.deleteUser(passwordDto);
	}

	@PostMapping(path = Endpoints.CHANGE_PASSWORD)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("isAuthenticated()")
	public void changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto) {

		userService.changePassword(changePasswordDto);
	}

	@PostMapping(path = Endpoints.FORGOT_PASSWORD)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void forgotPassword(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto) {

		userService.forgotPassword(forgotPasswordDto);
	}

	@PostMapping(path = Endpoints.RESET_PASSWORD)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void resetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto) {

		userService.resetPassword(resetPasswordDto);
	}

}
