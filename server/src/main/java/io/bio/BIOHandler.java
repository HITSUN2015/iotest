package io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * Created by SUN on 17/12/25.
 */
public class BIOHandler implements Runnable {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public BIOHandler() {
    }

    public BIOHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int result = inputStream.read(buffer);
            String str = new String(buffer, "utf-8");
//            System.err.println("from client info:" + str + "thread:" + Thread.currentThread().getId());
            System.out.println("thread:" + Thread.currentThread().getId());
            String server = new String("from server：" + str);
            Charset cs = Charset.forName("utf-8");
            TimeUnit.MILLISECONDS.sleep(100);
            byte[] bytes = server.getBytes(cs);
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}