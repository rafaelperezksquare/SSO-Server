package com.ksquare.sso.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksquare.sso.domain.User;
import com.ksquare.sso.domain.UserDTO;
import com.ksquare.sso.domain.UserRole;
import com.ksquare.sso.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/api/users")
@Api(tags = {"user-endpoints"})
@SwaggerDefinition(tags = {
    @Tag(name = "Swagger Resource", description = "Usage documentation for the ksquare-sso api")
})
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	/**
	 * Returns all registered users and an HTTP OK status code
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "getUsers",
			notes = "Gets the info of all the users. Returns a list with all the users info",
		    response = User.class,
		    responseContainer = "List")
	public ResponseEntity<?> getUsers() {
		List<UserDTO> usersDTO = userService.getUsers();
		logger.info("Listing all users");
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	/**
	 * Returns the complete user information of the user name provided along with an HTTP OK status code. 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/{username}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "getUser",
			notes = "Gets the info of a user. Receives the user name in the path and returns the correspondent info",
		    response = User.class)
	public ResponseEntity<?> getUser(@PathVariable String username) {
		UserDTO userDTO = userService.getUser(username);
		logger.info("Returning user info of " + username);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	/**
	 * Registers a new user to the server with USER role.
	 * Returns the newly created user and an HTTP CREATED status code.
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "addUser",
			notes = "Creates a new user. Receives a User object with the user info in the request body",
		    response = User.class)
	public ResponseEntity<?> addUser(@RequestBody User user) {
		logger.info("Adding user " + user.getUsername());
		user.setRoles(Arrays.asList(new UserRole("USER")));
		UserDTO userDTO = userService.addUser(user);
		return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
	}
	
	/**
	 * Registers a new user to the server with ADMIN and USER role.
	 * Returns the newly created user and an HTTP CREATED status code.
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "addAdminUser",
			notes = "Creates a new user with admin privileges. Receives a User object with the user info in the request body",
		    response = User.class)
	public ResponseEntity<?> addAdminUser(@RequestBody User user) {
		logger.info("Adding user " + user.getUsername());
		user.setRoles(Arrays.asList(new UserRole("ADMIN"), new UserRole("USER")));
		UserDTO userDTO = userService.addUser(user);
		return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
	}
	
	/**
	 * Updates the user information of the user name provided
	 * @param username
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/{username}", method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "updateUser",
			notes = "Updates a user info. Receives the username in the path and a User object with the updated user info in the request body")
	public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user) {
		logger.info("Updated user info of " + username);
		userService.updateUser(username, user);
		UserDTO userDTO = new UserDTO(user);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	/**
	 * Deletes the user with the user name provided and an HTTP OK status code.
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/{username}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "deleteUser",
			notes = "Deletes a user. Receives the name of the user to delete")
	public ResponseEntity<?> deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
		logger.info("Deleted user " + username);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Returns an HTTP OK status code if the token provided is still valid.
	 * @return HTTP status code
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "authUser",
			notes = "Validate if a user is registered",
    response = HttpStatus.class)
	public ResponseEntity<?> authUser() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Receives a list of usernames and returns back the list of not valid users.
	 * @param userNames
	 * @return
	 */
	@RequestMapping(value = "/areUsers", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "areUsers",
			notes = "Determines if a bunch of people are real users. Receives a String list with the names of posible users and retrieves a String list with the names that"
			+ " are not real users",
    response = String.class,
    responseContainer = "List")
	public ResponseEntity<?> areUsers(@RequestBody List<String> usernames) {
		List<String> invalidUsers = userService.areUsers(usernames);
		return new ResponseEntity<>(invalidUsers, HttpStatus.OK);
	}

}
