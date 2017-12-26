package io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Random;
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
        runLogWithFile();
    }

    private void runLogWithConsole(){
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int result = inputStream.read(buffer);
            String str = new String(buffer, "utf-8");
//            System.err.println("from client info:" + str + "thread:" + Thread.currentThread().getId());
            Integer sleepSecond =new Random().nextInt(20);
            System.out.println("thread:" + Thread.currentThread().getId() + " start:" + sleepSecond + "seconds");
            String server = new String("from server：" + str);
            Charset cs = Charset.forName("utf-8");
            TimeUnit.SECONDS.sleep(sleepSecond);
            byte[] bytes = server.getBytes(cs);
            outputStream.write(bytes);
            outputStream.flush();
            System.out.println("thread:" + Thread.currentThread().getId() + " sleep:" + sleepSecond + "seconds");
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

    //由于 上面 console
    private void runLogWithFile() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int result = inputStream.read(buffer);
            String str = new String(buffer, "utf-8");
//            System.err.println("from client info:" + str + "thread:" + Thread.currentThread().getId());
            Integer sleepSecond =new Random().nextInt(20);
            long threadId = Thread.currentThread().getId();
            long time = System.currentTimeMillis();
            String fileName = time + "-" + threadId + "-" + sleepSecond;

            FileIO.createLocalPropertyFile(fileName);
            FileIO.appendMethodB(fileName,String.valueOf(sleepSecond));

            String server = new String("from server：" + str);
            Charset cs = Charset.forName("utf-8");
            TimeUnit.SECONDS.sleep(sleepSecond);
            byte[] bytes = server.getBytes(cs);
            outputStream.write(bytes);
            outputStream.flush();

            FileIO.appendMethodB(fileName,String.valueOf(" sleep:" + sleepSecond + "seconds"));
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
