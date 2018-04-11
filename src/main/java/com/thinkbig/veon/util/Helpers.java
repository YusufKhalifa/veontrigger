package com.thinkbig.veon.util;

import java.util.Arrays;
import java.util.List;

public class Helpers {
       public List<String> splitCSVRecord(String str){

        //return new ArrayList<String>(Arrays.asList(str.split(" , ")));

        try {
            List<String> result = Arrays.asList(str.split(",", -1));
            // -1 to deal with trailing null values in CSV, exception.
            return result;
        }
        catch (IndexOutOfBoundsException obe){
            obe.printStackTrace();
            return null;
        }


    }
}
