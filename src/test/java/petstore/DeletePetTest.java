package petstore;

import java.util.Random;
import io.restassured.RestAssured;
import io.restassured.response.*;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import org.junit.Test;

public class DeletePetTest{

	@Test
	public void deletePetTest(){
		Random randomId = new Random();
		long id = randomId.nextLong();
		Argument category = new Argument(1, "test1");
		Argument tag = new Argument(1, "test2");
		String[] urls = {"url1", "url2"};
		Argument[] tags = {tag};
		Pet pet = new Pet(id, category, "petName", urls, tags , "statustest");
		
		RequestSpecification addRequest = RestAssured.given();
		addRequest.header("Content-Type", "application/json");
		addRequest.body(pet.toJson());
		Response addResponse = addRequest.post("http://petstore.swagger.io/v2/pet");
		Assert.assertEquals(200, addResponse.getStatusCode());
		
		RequestSpecification deleteRequest = RestAssured.given();
		Response deleteResponse = deleteRequest.delete("http://petstore.swagger.io/v2/pet/" + pet.getId());
		Assert.assertEquals(200, deleteResponse.getStatusCode());
	}
	
}
