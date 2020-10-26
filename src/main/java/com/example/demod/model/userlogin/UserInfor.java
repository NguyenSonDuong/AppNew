package com.example.demod.model.userlogin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class UserInfor {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String birthday;
    private String address;

    public static UserInfor SetALL(String data){
        String[] ite = data.split("[|]");
        UserInfor userInfor = new UserInfor();
        userInfor.id =ite[0];
        userInfor.username =ite[1];
        userInfor.password =ite[2];
        userInfor.fullname =ite[3];
        userInfor.birthday =ite[4];
        userInfor.address =ite[5];
        return userInfor;
    }
}
