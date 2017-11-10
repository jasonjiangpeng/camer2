package com.example.administrator.testdemo.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */

public class FaceAge {

    /**
     * image_id : HmkK97l96CMSk/QAnwbAIQ==
     * request_id : 1502782865,eefc0734-ea6b-4ece-bd17-d5b282398f17
     * time_used : 650
     * faces : [{"attributes":{"gender":{"value":"Male"},"age":{"value":42}},"face_rectangle":{"width":106,"top":284,"left":60,"height":106},"face_token":"c39c19fae2c3fd8551eaf2630cfe9dd0"},{"attributes":{"gender":{"value":"Female"},"age":{"value":48}},"face_rectangle":{"width":98,"top":277,"left":726,"height":98},"face_token":"bde76f311d908a918c022cefecfed954"},{"attributes":{"gender":{"value":"Male"},"age":{"value":36}},"face_rectangle":{"width":87,"top":325,"left":435,"height":87},"face_token":"2918fa5998c4230269c945c15db462d7"},{"attributes":{"gender":{"value":"Female"},"age":{"value":45}},"face_rectangle":{"width":82,"top":221,"left":265,"height":82},"face_token":"240b3ecf9c3768ddc9ddd39108a07fb7"},{"attributes":{"gender":{"value":"Male"},"age":{"value":40}},"face_rectangle":{"width":78,"top":105,"left":617,"height":78},"face_token":"b59cfa3c7936a2512f7f3663abd2a79d"}]
     */

    private String image_id;
    private String request_id;
    private int time_used;
    private List<FacesBean> faces;

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public List<FacesBean> getFaces() {
        return faces;
    }

    public void setFaces(List<FacesBean> faces) {
        this.faces = faces;
    }

    public static class FacesBean {
        /**
         * attributes : {"gender":{"value":"Male"},"age":{"value":42}}
         * face_rectangle : {"width":106,"top":284,"left":60,"height":106}
         * face_token : c39c19fae2c3fd8551eaf2630cfe9dd0
         */

        private String attributes;


        public String getAttributes() {
            return attributes;
        }

        public void setAttributes(String attributes) {
            this.attributes = attributes;
        }


    }
}
