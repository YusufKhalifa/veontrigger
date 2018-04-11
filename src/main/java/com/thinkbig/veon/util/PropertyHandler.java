package com.thinkbig.veon.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import static java.lang.System.out;

public class PropertyHandler {

    private static PropertyHandler instanceDefault = null;
    private static PropertyHandler instanceConf = null;
    private String confPath = null;

    private Properties propsCommon = null;

    private PropertyHandler(String confPath)
    {
        out.println("STRING_PATH="+confPath);
        this.propsCommon = new Properties();
        InputStream in = null;
        try
        {
            if(confPath == null)
            {
               // in = this.getClass().getClassLoader().getResourceAsStream(IConstants.CONFIG_FILE);
                in = new FileInputStream(IConstants.CONFIG_FILE);
                this.propsCommon.load(in);
            }
            else
            {
                in = this.getClass().getClassLoader().getResourceAsStream(confPath);
                //in = new FileInputStream(confPath);
                this.confPath =confPath;
                this.propsCommon.load(in);
            }

        }
        catch (IOException e)
        {
            out.println("ERROR="+e.getMessage());
        }
        finally
        {
            if(in!=null)
            {
                try {
                    in.close();
                } catch (IOException e) {
                    in=null;
                }
            }
        }
    }

    //----------------------------Default conf file-------------------------------------------

    public static synchronized PropertyHandler getInstance()
    {
        if (instanceDefault == null)
            instanceDefault = new PropertyHandler(null);
        return instanceDefault;
    }

    //----------------------------custom config file-------------------------------------------

    public static synchronized PropertyHandler getInstance(String confPath)
    {
        if (instanceConf == null || instanceConf.confPath!=confPath)
            instanceConf = new PropertyHandler(confPath);
        return instanceConf;
    }

    public String getValue(String propKey)
    {
        if(propKey!=null && !"".equals(propKey.trim()))
        {
            propKey = propKey.trim();
            return this.propsCommon.getProperty(propKey);
        }
        else return "";
    }

    public List<String> getValue(String propKey, String Delim){
        out.println("Prop Key" + propKey);
        return Arrays.asList(this.propsCommon.getProperty(propKey).split(Delim));
    }


}
