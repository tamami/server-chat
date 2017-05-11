package socket;

import java.io.IOException;
import java.net.*;

/**
 * Created by tamami on 11/05/17.
 */
public class MulticastSendingThread extends Thread {

    private byte[] messageBytes;

    public MulticastSendingThread(byte[] bytes) {
        super("MulticastSendingThread");
        messageBytes = bytes;
    }

    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(SocketMessengerConstants.MULTICAST_SENDING_PORT);
            InetAddress group = InetAddress.getByName(SocketMessengerConstants.MULTICAST_ADDRESS);
            DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, group,
                SocketMessengerConstants.MULTICAST_LISTENING_PORT);
            socket.send(packet);
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
