package Enel;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Api {
	
	private String city;
	
	public static void main(String[] args) {
	Api api = new Api();
	try {
		System.out.print(api.fetchApi("Atlanta"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String fetchApi(String city) throws IOException, InterruptedException{
		HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://yahoo-weather5.p.rapidapi.com/weather?location="+city+"&format=json&u=f"))
		.header("x-rapidapi-key", "b4e646b5acmshefad74f73aab4edp133812jsnc1d7ba4e5b9e")
		.header("x-rapidapi-host", "yahoo-weather5.p.rapidapi.com")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
		
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    JsonParser jp = new JsonParser();
	    JsonElement je = jp.parse(response.body().toString());
	    String prettyJsonString = gson.toJson(je);
		
		return prettyJsonString;
	}
	
	

}
