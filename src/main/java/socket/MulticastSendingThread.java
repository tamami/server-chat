package socket;

/**
 * Created by tamami on 11/05/17.
 */
public class MulticastSendingThread extends Thread {

    private byte[] messageBytes;

    public MulticastSendingThread(byte[] bytes) {
        super("MulticastSendingThread");
        messageBytes = bytes;
    }

}
