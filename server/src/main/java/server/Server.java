package server;

import io.ServerInterface;
import io.bio.BIOServer;
import io.nio.java.NIOServer;

/**
 * Created by SUN on 17/12/25.
 */
public class Server {

    private static ServerInterface serverInterface = null;

    public static void main(String[] args) {
        IOType type = IOType.NIO;
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
            case NIMA: {
                break;
            }
            case NETTY: {
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
