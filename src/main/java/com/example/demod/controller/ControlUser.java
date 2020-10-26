package com.example.demod.controller;


import com.example.demod.model.ReponsiveData;
import com.example.demod.model.userlogin.UserInfor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
public class ControlUser {
    
    @RequestMapping(value = "/register",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Object Register(@RequestBody UserInfor userInfor){
        ReponsiveData reponsiveData = ReponsiveData.of();
        try{
            reponsiveData.setError(0);
            reponsiveData.setStatus("Thành công");
            reponsiveData.setResulf(userInfor);
            SaveFile.FileWrite(SaveFile.PATH_USER,userInfor.toString());
            return reponsiveData;
        }catch (Exception ex){
            reponsiveData.setError(1);
            reponsiveData.setStatus(ex.getMessage());
            reponsiveData.setResulf(null);
            return reponsiveData;
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Object Login(@RequestParam("username") String username, @RequestParam("password") String password){
        ReponsiveData reponsiveData = ReponsiveData.of();
        try{
            ArrayList<String> list = SaveFile.FileRead(SaveFile.PATH_USER);
            for (String item:list) {
                UserInfor userInfor = UserInfor.SetALL(item);
                if(userInfor.getUsername().equals(username) && userInfor.getPassword().equals(password)){
                    reponsiveData.setError(0);
                    reponsiveData.setStatus("Thành công");
                    reponsiveData.setResulf(userInfor);
                    return reponsiveData;
                }
            }

        }catch (Exception ex){
            reponsiveData.setError(1);
            reponsiveData.setStatus(ex.getMessage());
            reponsiveData.setResulf(null);
            return reponsiveData;
        }
        reponsiveData.setError(1);
        reponsiveData.setStatus("Lỗi");
        reponsiveData.setResulf(null);
        return reponsiveData;
    }
}
