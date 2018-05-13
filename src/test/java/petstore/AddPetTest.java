package petstore;

import java.util.Random;
import io.restassured.RestAssured;
import io.restassured.response.*;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import org.junit.Test;
import com.google.gson.Gson;

public class AddPetTest{

	@Test
	public void addPetTest(){
		Random randomId = new Random();
		long id = randomId.nextLong();
		Argument category = new Argument(1, "test1");
		Argument tag = new Argument(1, "test2");
		String[] urls = {"url1", "url2"};
		Argument[] tags = {tag};
		Pet pet = new Pet(id, category, "petName", urls, tags , "statustest");
		
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(pet.toJson());
		Response response = request.post("http://petstore.swagger.io/v2/pet");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
		
		Gson gson = new Gson();
		Pet newPet = gson.fromJson(response.getBody().asString(), Pet.class);
		Assert.assertEquals(pet.getId(), newPet.getId());
		Assert.assertEquals(pet.getCategory().getId(), newPet.getCategory().getId());
		Assert.assertEquals(pet.getCategory().getName(), newPet.getCategory().getName());
		Assert.assertEquals(pet.getName(), newPet.getName());
		Assert.assertEquals(pet.getPhotoUrls().length, newPet.getPhotoUrls().length);
		Assert.assertEquals(pet.getPhotoUrls()[0], newPet.getPhotoUrls()[0]);
		Assert.assertEquals(pet.getTags().length, newPet.getTags().length);
		Assert.assertEquals(pet.getTags()[0].getId(), newPet.getTags()[0].getId());
		Assert.assertEquals(pet.getTags()[0].getName(), newPet.getTags()[0].getName());
		Assert.assertEquals(pet.getStatus(), newPet.getStatus());
	}
}
