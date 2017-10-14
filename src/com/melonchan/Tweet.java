package com.melonchan;

import java.util.ArrayList;

public class Tweet {

    private String user_name,description,title,url,type,video_height,video_weight,video_aspect;
    private ArrayList<String> image_list,name_tag,hashtag,video_list;

    public Tweet() {
        image_list = new ArrayList<>();
        name_tag = new ArrayList<>();
        hashtag = new ArrayList<>();
        video_list = new ArrayList<>();

    }

    public String getVideo_aspect() {
        return video_aspect;
    }

    public void setVideo_aspect(String video_aspect) {
        this.video_aspect = video_aspect;
    }

    public ArrayList<String> getVideo_list() {
        return video_list;
    }

    public void setVideo_list(String video_list) {
        this.video_list.add(video_list);
    }

    public String getVideo_height() {
        return video_height;
    }

    public void setVideo_height(String video_height) {
        this.video_height = video_height;
    }

    public String getVideo_weight() {
        return video_weight;
    }

    public void setVideo_weight(String video_weight) {
        this.video_weight = video_weight;
    }

    public ArrayList<String> getImage_list() {
        return image_list;
    }

    public void setImage_list(String image_lists) {
        //System.out.println(image_lists);
        image_list.add(image_lists);
    }

    public ArrayList<String> getName_tag() {
        return name_tag;
    }

    public void setName_tag(String name_tag) {
        this.name_tag.add(name_tag);
    }

    public ArrayList<String> getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag.add(hashtag);
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
