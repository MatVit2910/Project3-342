import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class TestClient {

    public static void main(String[] args) {
        int port = 8888;
        String ip = "127.0.0.1";

        try (Socket socket = new Socket(ip, port); // Use try-with-resources for auto-close
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Attempting to connect...");
            System.out.println("Connected!");

            socket.setTcpNoDelay(true);

            // --- 1. Perform Initial Message Exchange ---
            String messageToSend = "Hello Server, can you hear me?";
            out.writeObject(messageToSend);
            System.out.println("Sent: " + messageToSend);

            // 2. Wait for server's immediate reply
            Object response = in.readObject();
            System.out.println("Server replied: " + response.toString());

            // --- 3. Delay the Close Operation ---
            long delaySeconds = 10;
            System.out.printf("Holding connection for %d seconds...\n", delaySeconds);

            // This is the correct way to pause the thread
            TimeUnit.SECONDS.sleep(delaySeconds);

            System.out.println("Time elapsed. Disconnecting...");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Best practice to re-interrupt
            System.err.println("Client thread interrupted during sleep.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}