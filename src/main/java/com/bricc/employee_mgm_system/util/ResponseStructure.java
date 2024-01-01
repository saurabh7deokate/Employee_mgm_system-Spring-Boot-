package com.bricc.employee_mgm_system.util;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private String message;
	private Integer status;
	private T data;

	public ResponseStructure(String message, Integer status, T data) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
	}
}
