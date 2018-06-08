package com.youmina.handler;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.StreamHandler;

/**
 * Created by xinhezhang on 2018/3/29.
 */
public class WindowHandler extends StreamHandler{
    public WindowHandler(){
        final JTextArea output = new JTextArea();
        setOutputStream(
                new OutputStream() {
                    public void write(int b){};

                    public void write(byte[] b,int off,int len) throws IOException {
                        output.append(new String(b,off,len));
                    }
                }
        );
    }

}
