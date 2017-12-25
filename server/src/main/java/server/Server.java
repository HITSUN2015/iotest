package server;

import io.ServerInterface;
import io.bio.BIOServer;

/**
 * Created by SUN on 17/12/25.
 */
public class Server {

    private static ServerInterface serverInterface = null;

    public static void main(String[] args) {
        IOType type = IOType.BIO;
        switch (type) {
            case BIO: {
                serverInterface = new BIOServer();
                break;
            }
            case NIO: {
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
