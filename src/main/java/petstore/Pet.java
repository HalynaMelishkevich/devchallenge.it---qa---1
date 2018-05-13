package petstore;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Pet {
	
	@SerializedName("id")
	private long id;
	
	@SerializedName("category")
	private Argument category;
	
	@SerializedName("tags")
	private Argument[] tags;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("status")
	private String status;
	
	@SerializedName("photoUrls")
	private String[] photoUrls;
	
	public Pet(long id, Argument category, String name, String[] photoUrls, Argument[] tags, String status){
		this.id = id;
		this.category = category;
		this.name = name;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.status = status;
	}
	
	public long getId(){
		return id;
	}
	
	public Argument getCategory(){
		return category;
	}
	
	public String getName(){
		return name;
	}
	
	public String[] getPhotoUrls(){
		return photoUrls;
	}
	
	public Argument[] getTags(){
		return tags;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setName(String newPetName){
		name = newPetName;
	}
	
	//Serialization
	public String toJson(){
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}
	

}
