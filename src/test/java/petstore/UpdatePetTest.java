package petstore;

import java.util.Random;
import io.restassured.RestAssured;
import io.restassured.response.*;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import org.junit.Test;
import com.google.gson.Gson;

public class UpdatePetTest{

	@Test
	public void updatePetName(){
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
		
		pet.setName("petName2");
		RequestSpecification updateRequest = RestAssured.given();
		updateRequest.header("Content-Type", "application/json");
		updateRequest.body(pet.toJson());
		Response updateResponse = updateRequest.put("http://petstore.swagger.io/v2/pet");
		Assert.assertEquals(200, updateResponse.getStatusCode());
		Gson gson = new Gson();
		Pet updatedPet = gson.fromJson(updateResponse.getBody().asString(), Pet.class);
		
		Assert.assertEquals(pet.getId(), updatedPet.getId());
		Assert.assertEquals(pet.getCategory().getId(), updatedPet.getCategory().getId());
		Assert.assertEquals(pet.getCategory().getName(), updatedPet.getCategory().getName());
		Assert.assertEquals(pet.getName(), updatedPet.getName());
		Assert.assertEquals(pet.getPhotoUrls().length, updatedPet.getPhotoUrls().length);
		Assert.assertEquals(pet.getPhotoUrls()[0], updatedPet.getPhotoUrls()[0]);
		Assert.assertEquals(pet.getTags().length, updatedPet.getTags().length);
		Assert.assertEquals(pet.getTags()[0].getId(), updatedPet.getTags()[0].getId());
		Assert.assertEquals(pet.getTags()[0].getName(), updatedPet.getTags()[0].getName());
		Assert.assertEquals(pet.getStatus(), updatedPet.getStatus());
	}
}
