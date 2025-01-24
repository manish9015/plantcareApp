package com.example.emanagerapp.utils;

import android.icu.text.SimpleDateFormat;

import java.util.Date;

public class Helper  {
    public static String formatDate(Date date){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMMM,yyyy");
        return dateFormat.format(date);
    }


}
