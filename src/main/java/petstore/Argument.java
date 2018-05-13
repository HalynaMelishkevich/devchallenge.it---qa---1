package petstore;

import com.google.gson.annotations.SerializedName;

public class Argument {
	
	@SerializedName("id")
	private long id;
	
	@SerializedName("name")
	private String name;
	
	public Argument(long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public long getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
}
