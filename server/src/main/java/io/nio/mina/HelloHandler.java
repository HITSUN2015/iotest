package io.nio.mina;

import org.apache.mina.common.*;

/**
 * HelloServer的处理逻辑
 * @author liudong
 */
public class HelloHandler extends IoHandlerAdapter implements IoHandler {
    /**
     * 当有异常发生时触发
     */
    @Override
    public void exceptionCaught(IoSession ssn, Throwable cause) {
        cause.printStackTrace();
        ssn.close();
    }

    /**
     * 有新连接时触发
     */
    @Override
    public void sessionOpened(IoSession ssn) throws Exception {
        System.out.println("session open for " + ssn.getRemoteAddress());
    }

    /**
     * 连接被关闭时触发
     */
    @Override
    public void sessionClosed(IoSession ssn) throws Exception {
        System.out.println("session closed from " + ssn.getRemoteAddress());
    }

    /**
     * 收到来自客户端的消息
     */
    public void messageReceived(IoSession ssn, Object msg) throws Exception {
        String ip = ssn.getRemoteAddress().toString();
        System.out.println("===> Message From " + ip +" : " + msg);
        ssn.write("Hello " + msg);
    }
}