package com.example.demod.model.token;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("oauth_token")
	private String oauthToken;

	@SerializedName("fullname")
	private String fullname;

	@SerializedName("oauth_token_secret")
	private String oauthTokenSecret;

	@SerializedName("user_nsid")
	private String userNsid;

	@SerializedName("username")
	private String username;

	public void setOauthToken(String oauthToken){
		this.oauthToken = oauthToken;
	}

	public String getOauthToken(){
		return oauthToken;
	}

	public void setFullname(String fullname){
		this.fullname = fullname;
	}

	public String getFullname(){
		return fullname;
	}

	public void setOauthTokenSecret(String oauthTokenSecret){
		this.oauthTokenSecret = oauthTokenSecret;
	}

	public String getOauthTokenSecret(){
		return oauthTokenSecret;
	}

	public void setUserNsid(String userNsid){
		this.userNsid = userNsid;
	}

	public String getUserNsid(){
		return userNsid;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"oauth_token = '" + oauthToken + '\'' + 
			",fullname = '" + fullname + '\'' + 
			",oauth_token_secret = '" + oauthTokenSecret + '\'' + 
			",user_nsid = '" + userNsid + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}