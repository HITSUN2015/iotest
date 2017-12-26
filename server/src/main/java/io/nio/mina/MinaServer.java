package io.nio.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import io.ServerInterface;
import org.apache.mina.common.*;
import org.apache.mina.transport.socket.nio.*;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

/**
 * HelloServer演示程序
 * @author liudong (<a href="http://www.dlog.cn/javayou"><code>http://www.dlog.cn/javayou</code></a>)
 */
public class MinaServer implements ServerInterface{

//    private static final int PORT = 8080;

    @Override
    public void startService(int portNumber) throws Exception {
        IoAcceptor acceptor = new SocketAcceptor();
        IoAcceptorConfig config = new SocketAcceptorConfig();
        DefaultIoFilterChainBuilder chain = config.getFilterChain();
        //使用字符串编码
        chain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
        //启动HelloServer
        acceptor.bind(new InetSocketAddress(portNumber), new HelloHandler(), config);
        System.out.println("HelloServer started on port " + portNumber);
    }
}


