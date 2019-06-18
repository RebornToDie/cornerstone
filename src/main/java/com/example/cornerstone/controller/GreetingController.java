package com.example.cornerstone.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greeting")
public class GreetingController {

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE,path = "/hello")
	public String helloUser() {
		return "Hello World !!!";
	}
}
