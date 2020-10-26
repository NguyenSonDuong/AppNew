package com.example.demod.controller;

import com.example.demod.model.pixiv.DataItem;
import com.example.demod.model.pixiv.ReposivePixiv;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/v1/pixiv")
public class ControlPixiv {
    // lấy danh sách thông tin các ảnh theo tìm kiếm
    @RequestMapping(value = "/update",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ReposivePixiv getDataItem(@RequestParam("page") String page, @RequestParam("search") String search){
        ReposivePixiv reposivePixiv = ReposivePixiv.of();
        ArrayList<DataItem> li = new ArrayList<>();
        HttpURLConnection con = null;
        try {
            System.out.println(search);
            URL url = new URL("https://www.pixiv.net/ajax/search/artworks/"+ActionCustom.encodeValue(search)+"?p="+page+"&lang=en");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String json = content.toString();
            System.out.println(json);
            JSONObject data = new JSONObject(json);
            JSONArray body = data.getJSONObject("body").getJSONObject("illustManga").getJSONArray("data");
            for(int i=0;i<body.length();i++){
                JSONObject object2 = body.getJSONObject(i);
                DataItem dataItem = DataItem.of();
                dataItem.setId(object2.getString("id"));
                dataItem.setTitle(object2.getString("title"));
                dataItem.setUserId(object2.getString("userId"));
                dataItem.setUserName(object2.getString("userName"));
                dataItem.setWidth(object2.getInt("width"));
                dataItem.setHeight(object2.getInt("height"));
                li.add(dataItem);
            }
            reposivePixiv.setData(li);
            return reposivePixiv;
        } catch (IOException e) {
            return reposivePixiv;
        }

    }

    //
    @RequestMapping(value = "/getUrlImage",method = RequestMethod.GET,produces = MediaType.TEXT_PLAIN_VALUE)
    public String getUrlImage(@RequestParam("id") String id){
        HttpURLConnection con = null;
        try {
            URL url = new URL("https://www.pixiv.net/touch/ajax/illust/details?illust_id="+id);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            con.setRequestProperty("upgrade-insecure-requests", "1");
            con.setRequestProperty("sec-fetch-site", "cross-site");
            con.setRequestProperty("Referer", "https://www.pixiv.net/");
            con.setRequestProperty("Cookie","__cfduid=d0b3f745fc46a7b3ab59f1039db2a7ae21600009563; first_visit_datetime_pc=2020-09-14+00%3A06%3A04; yuid_b=kliBNg; p_ab_id=7; p_ab_id_2=7; p_ab_d_id=527486032; __utmc=235335808; __utmz=235335808.1600009564.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _fbp=fb.1.1600009565149.286296701; _ga=GA1.2.2037516319.1600009564; PHPSESSID=36179561_Q8VLJ3qahtlYPn7hdmOC0Ym2c4GeMmf6; device_token=80b1597154fd9240c3b8c166902422d9; c_type=21; privacy_policy_agreement=2; a_type=0; b_type=1; __utmv=235335808.|3=plan=normal=1^5=gender=male=1^6=user_id=36179561=1^11=lang=en=1; categorized_tags=CADCYLsad0~Hry6GxyqEm; __utma=235335808.2037516319.1600009564.1600673106.1600852454.5; tags_sended=1; _gid=GA1.2.1584391673.1600853028; _fbc=fb.1.1600853206030.IwAR1UDLYSOt7JPTN_U_eXM4f0uQ6bHehdJqSGc9n-G9DoBptjUdfgdNWfMz8; tag_view_ranking=Hry6GxyqEm~KN7uxuR89w~Lt-oEicbBr~RTJMXD26Ak~lESQzXtc3u~0c3mrf8bTA~eJu3J1XfFH~jH0uD88V6F~Bd2L9ZBE8q~YGM-VZAmZd~25GfYO-H1t~G_f4j5NH8i~CrFcrMFJzz~KOnmT1ndWG~p7mg6rtSk0~WlKkwEuUi0~cbmDKjZf9z~fbUyQrXMR3~_EOd7bsGyl~ef1QMXOaBg~aKhT3n4RHZ~rqnJSF7cpq~Y5Tw-5iHPe~Mf52HwdQ_f~eeilemH9GR~WjRN9ve4kb~rOnsP2Q5UN~0bJRmdEART~F6bwMFlHEF~K00L04OJ5K~6kZq7YWj68");
            con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
            con.connect();
            if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                String json = content.toString();
                JSONObject data = new JSONObject(json);
                String pic = data.getJSONObject("body").getJSONObject("illust_details").getString("url_big");
                return pic;
            }else{
                return "";
            }

        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Lấy ảnh qua ID
     * @param id
     * @return byte array
     */
    @RequestMapping(value = "/getImage",method = RequestMethod.GET,produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@RequestParam("id") String id){
        HttpURLConnection con = null;
        try {
            String pic = getUrlImage(id);
            URL url2 = new URL(pic);
            con = (HttpURLConnection) url2.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Cookie","__cfduid=d0b3f745fc46a7b3ab59f1039db2a7ae21600009563; first_visit_datetime_pc=2020-09-14+00%3A06%3A04; yuid_b=kliBNg; p_ab_id=7; p_ab_id_2=7; p_ab_d_id=527486032; __utmc=235335808; __utmz=235335808.1600009564.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _fbp=fb.1.1600009565149.286296701; _ga=GA1.2.2037516319.1600009564; PHPSESSID=36179561_Q8VLJ3qahtlYPn7hdmOC0Ym2c4GeMmf6; device_token=80b1597154fd9240c3b8c166902422d9; c_type=21; privacy_policy_agreement=2; a_type=0; b_type=1; __utmv=235335808.|3=plan=normal=1^5=gender=male=1^6=user_id=36179561=1^11=lang=en=1; categorized_tags=CADCYLsad0~Hry6GxyqEm; __utma=235335808.2037516319.1600009564.1600673106.1600852454.5; tags_sended=1; _gid=GA1.2.1584391673.1600853028; _fbc=fb.1.1600853206030.IwAR1UDLYSOt7JPTN_U_eXM4f0uQ6bHehdJqSGc9n-G9DoBptjUdfgdNWfMz8; tag_view_ranking=Hry6GxyqEm~KN7uxuR89w~Lt-oEicbBr~RTJMXD26Ak~lESQzXtc3u~0c3mrf8bTA~eJu3J1XfFH~jH0uD88V6F~Bd2L9ZBE8q~YGM-VZAmZd~25GfYO-H1t~G_f4j5NH8i~CrFcrMFJzz~KOnmT1ndWG~p7mg6rtSk0~WlKkwEuUi0~cbmDKjZf9z~fbUyQrXMR3~_EOd7bsGyl~ef1QMXOaBg~aKhT3n4RHZ~rqnJSF7cpq~Y5Tw-5iHPe~Mf52HwdQ_f~eeilemH9GR~WjRN9ve4kb~rOnsP2Q5UN~0bJRmdEART~F6bwMFlHEF~K00L04OJ5K~6kZq7YWj68");
            con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            con.setRequestProperty("upgrade-insecure-requests", "1");
            con.setRequestProperty("sec-fetch-site", "cross-site");
            con.setRequestProperty("Referer", "https://www.pixiv.net/");
            con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
            con.connect();
            if(con.getResponseCode() == 200){
                InputStream inp = con.getInputStream();
                System.out.println("okkk");
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] bt = new byte[1024];
                while ((nRead = inp.read(bt, 0, bt.length)) != -1) {
                    buffer.write(bt, 0, nRead);
                }
                buffer.flush();
                byte[] byteArray = buffer.toByteArray();
                return byteArray;

            }else {
                System.out.println("nooooooo");
                return null;
            }

        } catch (IOException e) {
            return null;
        }
    }
}
