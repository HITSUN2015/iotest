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

    //测试时,超出线程池范围,就会报错,后续会陆续的 线程池关闭线程,程序停止
    //todo console的线程阻塞原因
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

    //由于 上面 console 总是最后一秒 全打出,所以想测试 是不是线程 都是最后才执行,然后 就用了file输出,发现并不是,file输出 都是按照启动顺序
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
