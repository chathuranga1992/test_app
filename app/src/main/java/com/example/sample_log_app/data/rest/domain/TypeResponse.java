package com.example.sample_log_app.data.rest.domain;

import com.google.gson.annotations.SerializedName;

public class TypeResponse{

	@SerializedName("results")
	private Results results;

	public void setResults(Results results){
		this.results = results;
	}

	public Results getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"TypeResponse{" + 
			"results = '" + results + '\'' + 
			"}";
		}
}