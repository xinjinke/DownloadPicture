package com.youmina.day20181010;

import java.io.*;
import java.net.Socket;

/**
 * @author zhangxinhe
 * @date 2018/10/10 16:44
 */
public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("192.168.1.71",10086);

        OutputStream os = socket.getOutputStream();

        PrintWriter pw = new PrintWriter(os);

        pw.write("username:admin,password:123456");
        pw.flush();

        socket.shutdownOutput();

        InputStream is = socket.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String info = null;

        while((info = br.readLine())!= null){
            System.out.println("I am client,server say:"+info);
        }

        br.close();
        is.close();

        pw.close();
        os.close();
        socket.close();
    }
}
