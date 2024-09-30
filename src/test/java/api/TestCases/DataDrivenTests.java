package api.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.EndPoints.UserEndPoints;
import api.Payloads.User;
import api.Utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority=1, dataProvider ="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(int id,String username, String firstName, String lastName, String email, String password, String phone) {
		User payLoad = new User();
		payLoad.setId(id);
		payLoad.setUsername(username);
		payLoad.setFirstName(firstName);
		payLoad.setLastName(lastName);
		payLoad.setEmail(email);
		payLoad.setPassword(password);
		payLoad.setPhone(phone);
		
		
		
		
		Response res = UserEndPoints.createUser(payLoad);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}
	
	@Test(priority=2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String username) {
		System.out.println("Attempting to delete user: " + username);
		
		Response res = UserEndPoints.deleteUser(username);
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
	}

}
