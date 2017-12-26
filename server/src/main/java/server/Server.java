package server;

import io.ServerInterface;
import io.bio.BIOServer;
import io.nio.java.NIOServer;
import io.nio.mina.MinaServer;
import io.nio.netty4.Netty4Server;

/**
 * Created by SUN on 17/12/25.
 */
public class Server {

    private static ServerInterface serverInterface = null;

    public static void main(String[] args) {
        IOType type = IOType.MINA;
        switch (type) {
            case BIO: {
                serverInterface = new BIOServer();
                break;
            }
            case NIO: {
                serverInterface = new NIOServer();
                break;
            }
            case AIO: {
                break;
            }
            case MINA: {
                serverInterface = new MinaServer();
                break;
            }
            case NETTY4: {
                serverInterface = new Netty4Server();
                break;
            }default:{

            }
        }
        try {
            serverInterface.startService(8089);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
