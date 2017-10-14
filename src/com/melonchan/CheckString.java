package com.melonchan;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckString {

    private ArrayList<String> ogList = new ArrayList<>();
    private Tweet tweet = new Tweet();

    private String string;

    //private boolean videoAsCal = false;


    public Tweet getTweet() {
        return tweet;
    }

    public ArrayList<String> getArrayList() {
        return ogList;
    }

    public void checkString(String st){

        this.string = st;
        checkOG();

    }



    private void checkOG(){
        if (string.contains("og:") == true){
            //System.out.println(string);
            process();
        }
        if (tweet.getVideo_aspect() == null && tweet.getVideo_weight() != null && tweet.getVideo_height() != null){
            video_as();
        }
    }

    private void video_as(){
        int h = Integer.valueOf(tweet.getVideo_height());
        int w = Integer.valueOf(tweet.getVideo_weight());

        if (h > w){
            tweet.setVideo_aspect("portrait");
        }
        else if (w > h){
            tweet.setVideo_aspect("landscape");
        }
        else {
            tweet.setVideo_aspect("square");
        }
    }

    private void process(){
        if (string.contains("og:type") && !string.contains("og:video:type")){
            tweet.setType(contentCutFunc());
        }
        else if (string.contains("og:url") && !string.contains("og:video:url") && !string.contains("og:video:secure_url")){
            tweet.setUser_name("@"+userNameCutFunc());
            tweet.setUrl(contentCutFunc());
        }
        else if (string.contains("og:description")){
            tweet.setDescription(mainDesCutFunc());
            desCutFunc();
        }

        if (string.contains("og:image") && !string.contains("og:image:user_generated")){
            String x = image_orig(contentCutFunc());
            if (!x.contains("/profile_images/")){
                tweet.setImage_list(x);
            }

        }
        if (tweet.getType().contentEquals("video")){
            if(string.contains("og:video:url")){
                tweet.setVideo_list(contentCutFunc());
            }
            else if (string.contains("og:video:width")){
                tweet.setVideo_weight(contentCutFunc());
            }
            else if (string.contains("og:video:height")){
                tweet.setVideo_height(contentCutFunc());
            }


        }

    }



    private String contentCutFunc(){

        int index = string.indexOf("content=")+9;
        int end = string.length()-2;
        return string.substring(index,end);

    }

    private String cutTrash(String s){
        String a = "";

        for (int i = 0; i < s.length() ;i++){
           if (i+4 < s.length()){
               if (s.charAt(i) == '&' && s.charAt(i+1) == '#' && s.charAt(i+2) == '1' && s.charAt(i+3) == '0'  && s.charAt(i+4) == ';' ){
                   i+=4;
                   a = a + "\n";
               }else if (s.charAt(i) == '&' && s.charAt(i+1) == '#' && s.charAt(i+2) == '3' && s.charAt(i+3) == '9'  && s.charAt(i+4) == ';' ){
                   String a1 = s.substring(0,i);
                   i+=4;
                   a = a1 + "'";
               }
               else {
                   a = a + s.charAt(i);
               }
           }
        }

        return a;
    }

    private String mainDesCutFunc(){
        int index = string.indexOf("content=")+10;
        int end = string.length()-3;

        String s = string.substring(index,end);

        //System.out.println(cutTrash(s));

        return cutTrash(s);
    }

    private String image_orig (String url){
        //for (int i = 0;i < url.length();i++){

        //}
        String x = "https://";
        String a = url.substring(x.length());
        //System.out.println(a);

        if (a.contains(":")){
            int k = a.indexOf(":");
            a = a.substring(0,k);
        }

        url = x + a + ":orig";


        return url;
    }

    private void desCutFunc(){

        int index = -1,end = -1;
        boolean clear = true;
        boolean pass = false;

        for (int i = 0 ;i < string.length();i++){
            char character = string.charAt(i);
            String s = string;

            if (character == '@' && clear == true){
                clear = false;
                index = i + 1;
            }
            else if ((clear == false) && !((character >= 'a' && character <= 'z')
                    || (character >= 'A' && character <= 'Z') || (character == '_')
                    || (character >= '0' && character <= '9'))){
                clear = true;
                end = i;

                tweet.setName_tag("@"+string.substring(index,end));

            }
            if (!(s.charAt(i) == '&' && s.charAt(i+1) == '#' && s.charAt(i+2) == '1' && s.charAt(i+3) == '0'  && s.charAt(i+4) == ';' ) && pass == false){

                String sq = mainDesCutFunc();

                Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
                Matcher mat = MY_PATTERN.matcher(sq);
                while (mat.find()) {
                    String q = mat.group(1);
                    if (q.charAt(q.length()-1) == ')'){
                        tweet.setHashtag("#"+q.substring(0,q.length()-1));
                    }
                    else {
                        if (!q.contentEquals("39;")){
                            tweet.setHashtag("#"+q);
                        }
                    }
                    //System.out.println(q);


                }

                pass = true;
            }
        }
    }

    private String userNameCutFunc(){
        String x = "https://twitter.com/";
        int index = string.indexOf(x) + x.length();
        String y = "/status/";
        int end = string.indexOf(y);

        return string.substring(index,end);
    }

}
