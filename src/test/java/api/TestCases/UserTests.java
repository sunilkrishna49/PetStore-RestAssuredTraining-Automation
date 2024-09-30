package api.TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.javafaker.Faker;

import api.EndPoints.UserEndPoints;
import api.Payloads.User;
import io.restassured.response.Response;

public class UserTests {
	Faker faker;
	User userPayload;
	public Logger logger;  //create a variable logger with Logger class;
	
	@BeforeClass
	public void setup() {
		
			faker  = new Faker();
			userPayload = new User();
			
			userPayload.setId(faker.idNumber().hashCode());
			userPayload.setUsername(faker.name().fullName());
			userPayload.setFirstName(faker.name().firstName());
			userPayload.setLastName(faker.name().lastName());
			userPayload.setEmail(faker.internet().emailAddress());
			userPayload.setPassword(faker.internet().password(5,10));
			userPayload.setPhone(faker.phoneNumber().cellPhone());
			
			//logs
			logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostUser() {
		logger.info("***********************Create a User***********************");
		Response res = UserEndPoints.createUser(userPayload);

		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(),200);
		
		logger.info("***********************User is Created***********************");

		
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("***********************Read a User***********************");

		Response res = UserEndPoints.readUser(this.userPayload.getUsername());
				res.then().log().all();
				Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("***********************Displayed the user***********************");

	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		//update data using payload
		userPayload.setFirstName( faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		logger.info("***********************Update a User***********************");

		
		Response res = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		Response resUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		resUpdate.then().log().body();
		Assert.assertEquals(resUpdate.getStatusCode(), 200);
		
		logger.info("***********************User is updated***********************");

	}
	
	@Test(priority=4)
	public void testdeleteUserByName() {
		
		logger.info("***********************Delete a User***********************");

		Response res = UserEndPoints.deleteUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		
		logger.info("***********************User is Deleted***********************");

		
	}
}
