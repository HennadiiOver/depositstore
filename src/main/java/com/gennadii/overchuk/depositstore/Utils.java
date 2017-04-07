package com.gennadii.overchuk.depositstore;


import java.io.*;

/**
 * Created by Tehnik on 05.04.2017.
 */
public class Utils {
    public static String convertStreamToString(InputStream is) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void convertStringToStream(OutputStream os, String xml) {
        PrintWriter pw = new PrintWriter(os, true);
        pw.println(xml);
    }
}
