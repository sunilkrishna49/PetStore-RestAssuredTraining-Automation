package api.EndPoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.Payloads.User;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	 static ResourceBundle getUrlFromProperties() {
		 
		 ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file //name of the properties file
		 return routes;
		
	}
	
	 public static Response createUser(User payload) {
		
		String post_Url = getUrlFromProperties().getString("post_Url");
		
		Response res = given()
			.accept("application/json")
			.contentType("application/json")
			.body(payload)
			
		.when()
			.post(post_Url);
		
		return res;	
		
	}
	
	public static Response readUser(String username) {
		
		String get_Url = getUrlFromProperties().getString("get_Url");
		
		Response res = given()
			.pathParam("username", username)
		.when()
			.get(get_Url);
		
		return res;
		
	}
	
	public static Response updateUser(String username,User payload) {
		
		String update_Url = getUrlFromProperties().getString("update_Url");
		
		Response res = given()
			.pathParam("username", username)
			.accept("application/json")
			.contentType("application/json")
			.body(payload)
			
		.when()
			.put(update_Url);
		
		return res;
		
	}
	
	public static Response deleteUser(String username) {
		
		String delete_Url = getUrlFromProperties().getString("delete_Url");
		
		Response res = given()
			.pathParam("username", username)
		.when()
			.delete(delete_Url);
		
		return res;
		
	}

}
