package api.EndPoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.Payloads.User;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createUser(User payload) {
		
		Response res = given()
			.accept("application/json")
			.contentType("application/json")
			.body(payload)
			
		.when()
			.post(Routes.postUrl);
		
		return res;	
		
	}
	
	public static Response readUser(String username) {
		Response res = given()
			.pathParam("username", username)
		.when()
			.get(Routes.getUrl);
		
		return res;
		
	}
	
	public static Response updateUser(String username,User payload) {
		Response res = given()
			.pathParam("username", username)
			.accept("application/json")
			.contentType("application/json")
			.body(payload)
			
		.when()
			.put(Routes.updateUrl);
		
		return res;
		
	}
	
	public static Response deleteUser(String username) {
		
		Response res = given()
			.pathParam("username", username)
		.when()
			.delete(Routes.deleteUrl);
		
		return res;
		
	}

}
