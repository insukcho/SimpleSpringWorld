package com.chris.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

	@RequestMapping("/data")
	public String data() {
		return "Return data with repo";
	}
}
