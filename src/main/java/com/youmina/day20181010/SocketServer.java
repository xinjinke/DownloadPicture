package com.youmina.day20181010;


import java.io.*;
import java.net.ServerSocket;

/**
 * @author zhangxinhe
 * @date 2018/10/10 16:35
 */
public class SocketServer {


    public static void main(String [] args) throws IOException {
        //端口0-65535之间，0-1023系统占用
        ServerSocket sercverSocket = new ServerSocket(10086);

        java.net.Socket socket = sercverSocket.accept();

        InputStream is = socket.getInputStream();

        InputStreamReader isr = new InputStreamReader(is);

        BufferedReader br = new BufferedReader(isr);

        String info = null;
        while((info = br.readLine())!= null){
            System.out.println("I am server,client say:"+info);
        }

        socket.shutdownInput();

        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("wellcome!");
        pw.flush();

        pw.close();
        os.close();

        br.close();
        isr.close();
        is.close();
        socket.close();
        sercverSocket.close();

    }
}
