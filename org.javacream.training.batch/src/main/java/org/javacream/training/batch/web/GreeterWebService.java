package org.javacream.training.batch.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreeterWebService {

	@GetMapping(path = "api/greeter/{name}", produces = MediaType.TEXT_PLAIN_VALUE) public String greet(@PathVariable("name") String name) {
		System.out.println("received name " + name);
		return "Hello " + name;
	}
}
