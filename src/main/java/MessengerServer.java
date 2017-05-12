import socket.MessageListener;
import socket.MulticastSendingThread;
import socket.ReceivingThread;
import socket.SocketMessengerConstants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tamami on 11/05/17.
 */
public class MessengerServer implements MessageListener {

    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(SocketMessengerConstants.SERVER_PORT, 100);
            System.out.println("Server menunggu di port " +
                SocketMessengerConstants.SERVER_PORT + " ...");

            while(true) {
                Socket clientSocket = serverSocket.accept();
                new ReceivingThread(this, clientSocket).start();
                System.out.println("Connection received from: " + clientSocket.getInetAddress());
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void messageReceived(String from, String message) {
        String completeMessage = from + SocketMessengerConstants.MESSAGE_SEPARATOR + message;
        new MulticastSendingThread(completeMessage.getBytes()).start();
    }

    public static void main(String[] args) {
        new MessengerServer().startServer();
    }

}
