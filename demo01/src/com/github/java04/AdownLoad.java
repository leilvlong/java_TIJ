package com.github.java04;

import java.io.*;
import java.net.URL;

public class AdownLoad {
    public static void main(String[] args) throws Exception{
        URL url = new URL("https://b-ssl.duitang.com/uploads/item/201406/13/20140613101954_CnxQB.jpeg");
        InputStream inputStream = url.openStream();
        BufferedInputStream bfil = new BufferedInputStream(inputStream);
        BufferedOutputStream bfol = new BufferedOutputStream(new FileOutputStream("demo01\\funs\\20140613101954_CnxQB.jpeg"));
        int len;
        for(byte[] bytes = new byte[1024*8];(len=bfil.read(bytes)) != -1;){
            bfol.write(bytes,0,len);
        }
        bfol.close();
        bfil.close();
        inputStream.close();
    }
}
