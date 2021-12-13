package testApi.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.*;

public class HelloApi {
	private static final Gson gson = new Gson();
	  private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }
	  public static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      JSONArray json = new JSONArray(jsonText);
		      return json;
		    } finally {
		      is.close();
		    }
		  }
	
	public static void main(String[] args) throws IOException, JSONException {
		String url = "http://localhost:3000/employees";
		JSONArray arr = readJsonFromUrl(url);
		
		String array = arr.toString();
		Type employeeListType = new TypeToken<ArrayList<employee>>(){}.getType();
		List<employee> list = gson.fromJson(array, employeeListType) ;
		
		
		
		for (employee employee : list) {
			System.out.println("employee: ("+ employee.getId()+")");
			System.out.println("first name: " + employee.getFirst_name());
			System.out.println("last name: " + employee.getLast_name());
			System.out.println("email: " + employee.getEmail());
			System.out.println("==========================");
			
			
		}
		
	}
}
