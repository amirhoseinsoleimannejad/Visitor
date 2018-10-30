package com.example.amhso.visitor.otherclass;

/**
 * Created by amhso on 21/07/2018.
 */

public class dragestor {

    public String id;
    public String text;
    public String mobile;
    public String address;
    public String lat;
    public String lng;
    public String mobile2;


    public dragestor(String id, String text, String mobile, String address, String lat, String lng, String mobile2){
        this.id=id;
        this.text=text;
        this.mobile=mobile;
        this.address=address;
        this.lat=lat;
        this.lng=lng;
        this.mobile2=mobile2;
    }

    public String getId(){
        return this.id;
    }

    public String getText(){
        return this.text;
    }


    public String getMobile(){
        return this.mobile;
    }

    public String getAddress(){
        return this.address;
    }


    public String getLat(){
        return this.lat;
    }



    public String getLng(){
        return this.lng;
    }



    public String getMobile2(){
        return this.mobile2;
    }




}
