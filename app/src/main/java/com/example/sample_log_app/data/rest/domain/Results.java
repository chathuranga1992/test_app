package com.example.sample_log_app.data.rest.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Results{

	@SerializedName("merchantLove")
	private List<String> merchantLove;

	@SerializedName("merchantCategories")
	private List<String> merchantCategories;

	public void setMerchantLove(List<String> merchantLove){
		this.merchantLove = merchantLove;
	}

	public List<String> getMerchantLove(){
		return merchantLove;
	}

	public void setMerchantCategories(List<String> merchantCategories){
		this.merchantCategories = merchantCategories;
	}

	public List<String> getMerchantCategories(){
		return merchantCategories;
	}

	@Override
 	public String toString(){
		return 
			"Results{" + 
			"merchantLove = '" + merchantLove + '\'' + 
			",merchantCategories = '" + merchantCategories + '\'' + 
			"}";
		}
}