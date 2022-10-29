package com.example.demo.sample.utils;

import javafx.beans.binding.Bindings;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 2hu0
 */
public class DateUtils {
    private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String strDate(Date date) {
        return sf.format(date);
    }

}
