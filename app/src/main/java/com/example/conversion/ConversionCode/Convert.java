package com.example.conversion.ConversionCode;

import com.example.conversion.model.Hex;

public class Convert {

    public Integer decimal(String hexValue){
        if(hexValue != null){
            hexValue = hexValue.replace("0x","");
            return Integer.parseInt(hexValue,16);
        }else{
            return null;
        }
    }

    public String binary(String hexValue){

        if(hexValue != null){
            hexValue = hexValue.replace("0x","");
            int hexInt = Integer.parseInt(hexValue, 16);
            return Integer.toBinaryString(hexInt);
        }
        else {
            return null;
        }
    }

    public String ascii(String hexValue){
        if(hexValue != null) {
            hexValue = hexValue.replace("0x","");
            StringBuilder asciiValue = new StringBuilder();
            for (int i = 0; i < hexValue.length(); i += 2) {
                String str = hexValue.substring(i, i + 2);
                asciiValue.append((char) Integer.parseInt(str, 16));
            }
            return asciiValue.toString();
        }else {
            return null;
        }
    }
}
