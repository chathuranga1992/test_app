package com.example.sample_log_app.data.rest.domain;


import com.example.sample_log_app.GenericEntity;

public class Image extends GenericEntity {
	private String thumb;
	private String src;

	public void setThumb(String thumb){
		this.thumb = thumb;
	}

	public String getThumb(){
		return thumb;
	}

	public void setSrc(String src){
		this.src = src;
	}

	public String getSrc(){
		return src;
	}



	@Override
 	public String toString(){
		return 
			"Image{" + 
			"thumb = '" + thumb + '\'' + 
			",src = '" + src + '\'' +
			"}";
		}
}

