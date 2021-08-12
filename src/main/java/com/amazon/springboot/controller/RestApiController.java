package com.amazon.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.springboot.model.User;
import com.amazon.springboot.service.UserService;
import com.amazon.springboot.util.CustomErrorType;

@CrossOrigin(origins = "http://ec2-13-126-235-222.ap-south-1.compute.amazonaws.com:8081")
@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	UserService userService; 
	
	// -------------------Retrieve Results------------------------------------------

		@RequestMapping(value = "/user/path", method = RequestMethod.POST)
		public ResponseEntity<?> getUser(@RequestBody User user) {
			logger.info("Fetching User {}", user);
			
			if (user.getGraphPath() == null || user.getGraphPath().isEmpty()) {
			logger.error("Graph Path is Empty.");
			return new ResponseEntity(new CustomErrorType("Graph Path is Empty."), HttpStatus.NO_CONTENT);
		    }
			
			//"AB3, BC9, CD3, DE6, AD4, DA5, CE2, AE4, EB1"
			//"A-B-C", "A-E-B-C-D", "A-E-D"
			userService.populateVertexMap(user.getGraphPath());
			int s = 0, d = 2, pathCount = 0;
			int totalPath = userService.countPaths(s, d, pathCount);
			logger.info("Total Path between A and C is : {} ", totalPath);
			logger.info("What is the path 1 {} : ", user.getWeight_1());
			logger.info("What is the path 2 {} : ", user.getWeight_2());
			logger.info("What is the path 3 {} : ", user.getWeight_3());
			
			User currentUser = new User();
			currentUser.setGraphPath(user.getGraphPath());
			currentUser.setTotalPath(totalPath);
			
			currentUser.setWeight_1(userService.retrieveWeight(user.getWeight_1()) == 0 ?  "Path Not Found" : String.valueOf(userService.retrieveWeight(user.getWeight_1())));
			currentUser.setWeight_2(userService.retrieveWeight(user.getWeight_2()) == 0 ?  "Path Not Found" : String.valueOf(userService.retrieveWeight(user.getWeight_2())));
			currentUser.setWeight_3(userService.retrieveWeight(user.getWeight_3()) == 0 ?  "Path Not Found" : String.valueOf(userService.retrieveWeight(user.getWeight_3())));
			userService.resetArrays();
			logger.info("Final Object : {} ", currentUser);
			
			return new ResponseEntity<User>(currentUser, HttpStatus.OK);
		}
}