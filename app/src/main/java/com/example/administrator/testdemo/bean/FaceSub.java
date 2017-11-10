package com.example.administrator.testdemo.bean;

/**
 * Created by Administrator on 2017/8/16.
 */

public class FaceSub {

    /**
     * attributes : {"gender":{"value":"Male"},"age":{"value":44}}
     * face_rectangle : {"width":212,"top":569,"left":122,"height":212}
     * face_token : d07408fd2df9acdc3f3735b868cfcc76
     */

    private AttributesBean attributes;
    private FaceRectangleBean face_rectangle;
    private String face_token;

    public AttributesBean getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesBean attributes) {
        this.attributes = attributes;
    }

    public FaceRectangleBean getFace_rectangle() {
        return face_rectangle;
    }

    public void setFace_rectangle(FaceRectangleBean face_rectangle) {
        this.face_rectangle = face_rectangle;
    }

    public String getFace_token() {
        return face_token;
    }

    public void setFace_token(String face_token) {
        this.face_token = face_token;
    }

    public static class AttributesBean {
        /**
         * gender : {"value":"Male"}
         * age : {"value":44}
         */

        private GenderBean gender;
        private AgeBean age;

        public GenderBean getGender() {
            return gender;
        }

        public void setGender(GenderBean gender) {
            this.gender = gender;
        }

        public AgeBean getAge() {
            return age;
        }

        public void setAge(AgeBean age) {
            this.age = age;
        }

        public static class GenderBean {
            /**
             * value : Male
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class AgeBean {
            /**
             * value : 44
             */

            private int value;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }
    }

    public static class FaceRectangleBean {
        /**
         * width : 212
         * top : 569
         * left : 122
         * height : 212
         */

        private int width;
        private int top;
        private int left;
        private int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
