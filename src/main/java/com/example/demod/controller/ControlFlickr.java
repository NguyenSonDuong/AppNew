package com.example.demod.controller;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.Parameter;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.people.PeopleInterface;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.github.scribejava.core.model.OAuth1RequestToken;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static com.flickr4java.flickr.photos.PhotosInterface.METHOD_SEARCH;
@RestController
@RequestMapping
public class ControlFlickr {

    Flickr f = new Flickr("dcf344473b57cb724ff2ce419abed51f", "c3e4ad07039b4b8f", new REST());
    OAuth1RequestToken Oauth;
    @RequestMapping(value = "/requestToken",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getTokenFlickr(){
        AuthInterface testInterface = f.getAuthInterface();

        try {
            Oauth = testInterface.getRequestToken();
            return testInterface.getAuthorizationUrl(Oauth, Permission.READ);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    @RequestMapping(value = "/getTokenFlickr",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getToken(@RequestParam("ver") String ver){
        AuthInterface au = f.getAuthInterface();
        HashMap<String,String> tokenKey = new HashMap<>();
        try {
            String accessten = URLDecoder.decode(au.getAccessToken(Oauth,ver).getRawResponse(), StandardCharsets.UTF_8.name());
            String[] infor = accessten.split("[&]");
            for (String item:infor) {
                String[] it = item.split("=");
                if(it.length>1)
                    tokenKey.put(it[0],it[1]);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return tokenKey;
    }
    @RequestMapping(value = "/getImage",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getImageFlickr(@RequestParam("userid")String userid,@RequestParam("authtoken")String authtoken,@RequestParam("Secret") String Secret){
        try {
            PeopleInterface user = f.getPeopleInterface();
            PhotoList<Photo> list = user.getPhotosOf(userid,null,null,50,1);
            System.out.println(list.size());
            for (Photo item :
                    list) {
                System.out.println(item.getLargeUrl());
            }
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
//
        List parameters = new ArrayList();
        parameters.add(new Parameter("method", METHOD_SEARCH));
        parameters.add(new Parameter("api_key", f.getApiKey()));
        String token = RequestContext.getRequestContext().getAuth().getToken();
        if (token != null)
            parameters.add(
                    new Parameter(
                            "auth_token",
                            RequestContext.getRequestContext().getAuth().getToken()
                    )
            );
        return "";
    }

}
