package com.boilerplate.boilerplateapi.features.email;

import com.boilerplate.boilerplateapi.features.user.User;
import com.boilerplate.boilerplateapi.features.user.password.reset.PasswordToken;

import javax.mail.MessagingException;


public interface IEmailService {
	void sendForgotPasswordEmail(User user, PasswordToken passwordToken) throws MessagingException;
}
