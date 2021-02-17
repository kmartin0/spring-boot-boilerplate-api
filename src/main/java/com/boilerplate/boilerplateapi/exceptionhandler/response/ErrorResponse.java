package com.boilerplate.boilerplateapi.exceptionhandler.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse implements Serializable {

	// The Status
	private String error;

	// A developer-facing human-readable error description in English.
	private String description;

	// The HTTP Status Code
	private int code;

	// (Optional) Additional error information that the client code can use to handle
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, String> details;

	public ErrorResponse(ApiErrorCode apiErrorCode, String description, TargetError... details) {
		this.code = apiErrorCode.getHttpStatus().value();
		this.description = description;
		this.error = apiErrorCode.name();
		this.details = detailsArrToHashMap(details);
	}

	public ErrorResponse(ApiErrorCode apiErrorCode, String description) {
		this.code = apiErrorCode.getHttpStatus().value();
		this.description = description;
		this.error = apiErrorCode.name();
	}

	public ErrorResponse(ApiErrorCode apiErrorCode, String description, Map<String, String> details) {
		this.code = apiErrorCode.getHttpStatus().value();
		this.description = description;
		this.error = apiErrorCode.name();
		this.details = details;
	}

	private HashMap<String, String> detailsArrToHashMap(TargetError... details) {
		if (details == null) return null;

		HashMap<String, String> tmpDetails = new HashMap<>();
		for (TargetError targetError : details) {
			tmpDetails.put(targetError.getTarget(), targetError.getError());
		}
		return tmpDetails;
	}
}
