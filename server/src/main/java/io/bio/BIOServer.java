package io.bio;

import io.ServerInterface;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by SUN on 17/12/25.
 */
public class BIOServer implements ServerInterface {

    private static ServerSocket serverSocket;
    private static Socket socket;
    private static ExecutorService executeService = Executors.newCachedThreadPool();

    public BIOServer() {
    }

    @Override
    public void startService(int portNumber) throws IOException {
        System.out.println("BIOServer begin……");
        serverSocket = new ServerSocket(portNumber); // 等待客户端连接
        while (true) {
            socket = serverSocket.accept(); // 这就是bio同步阻塞
            BIOHandler handler = new BIOHandler(socket);// 创建一个任务
            executeService.execute(handler);// 任务交给线程池
        }

    }
}