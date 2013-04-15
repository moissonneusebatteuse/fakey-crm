// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) space 
// Source File Name:   Utils.java

package uk.co.tangentlabs.util;

import java.io.*;

public class Utils
{

    public Utils()
    {
    }

    public static String getStringFromInputStream(InputStream in)
        throws IOException
    {
        StringBuffer out = new StringBuffer();
        byte b[] = new byte[4096];
        int n;
        while ((n = in.read(b)) != -1) 
            out.append(new String(b, 0, n));
        return out.toString();
    }

    public static String xmlEncode(String str)
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            if (ch > '\200')
                buf.append((new StringBuilder("&#")).append(ch).append(";").toString());
            else
                buf.append(ch);
        }

        return buf.toString();
    }

    public static String readFileAsString(File filePath)
        throws IOException
    {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char buf[] = new char[1024];
        for (int numRead = 0; (numRead = reader.read(buf)) != -1;)
        {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }

        reader.close();
        return fileData.toString();
    }
}
