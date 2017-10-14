package com.melonchan;

import org.apache.commons.lang3.StringEscapeUtils;

import java.net.*;
import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {



        //URL oracle = new URL("https://twitter.com/ohfunato/status/917745918354497536");

        //URL oracle = new URL("https://twitter.com/sirokumadaddy/status/917866826889019392");

        //URL oracle = new URL("https://twitter.com/blast2630/status/917889136052256768");

        //URL oracle = new URL("https://twitter.com/roco_0523/status/917920252813500416");

        //URL oracle = new URL("https://twitter.com/MFKM_WFWF/status/917920055106551808");

        //URL oracle = new URL("https://twitter.com/hinekota/status/917645907826417664/photo/2");

        //URL oracle = new URL("https://twitter.com/Jubatus_Lupus/status/917441469777985536");

        //URL oracle = new URL("https://twitter.com/blast2630/status/917363084842352640");

        //URL oracle = new URL("https://twitter.com/kemokakeGMKZ/status/917019026475200512");

        //URL oracle = new URL("https://twitter.com/NIf13jv/status/785860912812953600");

        //URL oracle = new URL("https://twitter.com/CKoksantia/status/918543226931314688");

        //URL oracle = new URL(""); //Empty URL

        URL oracle;

        boolean sw = true;

        if (sw){
            System.out.print("URL : ");
            Scanner scanner = new Scanner(System.in);
            String inp = scanner.next();
            oracle = new URL(inp);
        }
        else {
            oracle = new URL("https://twitter.com/NIf13jv/status/785860912812953600");
        }


        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));
        //BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        String inputLine;

       

        Tweet tweet = new Tweet();
        CheckString checkString = new CheckString();

        //System.out.println(in.readLine());

        while ((inputLine = in.readLine()) != null){

            checkString.checkString(inputLine);
            //String p = StringEscapeUtils.unescapeHtml4(inputLine);
        }

        System.out.println();

        tweet = checkString.getTweet();

        System.out.println("\n ----- Username -----");
        System.out.println(tweet.getUser_name());

        System.out.println("\n ----- Type -----");
        System.out.println(tweet.getType());

        System.out.println("\n ----- Tweet URL -----");
        System.out.println(tweet.getUrl());

        System.out.println("\n ----- Description -----");
        System.out.println(tweet.getDescription());

        ArrayList<String> s = tweet.getImage_list();
        ArrayList<String> e = tweet.getName_tag();
        ArrayList<String> h = tweet.getHashtag();
        ArrayList<String> v = tweet.getVideo_list();

        System.out.println("\n ----- Image -----");
        for (String i : s){
            System.out.println(i);
        }

        System.out.println("\n ----- Video -----");
        for (String m : v){
            System.out.println(m);
        }
        System.out.println("Resolution : " + tweet.getVideo_weight() + " x " + tweet.getVideo_height()
                + "\nAspect : "+tweet.getVideo_aspect());

        System.out.println("\n ----- Name List -----");
        for (String j : e){
            System.out.println(j);
        }

        System.out.println("\n ----- Hashtag -----");
        for (String k : h){
            System.out.println(k);
        }



        /*
        while ((inputLine = in.readLine()) != null){
            try{
                //writer.write(inputLine+"\n");
            }
            catch(IOException e){
                e.printStackTrace();
                return;
            }
        }*/

        in.close();
        //writer.close();

    }
}
