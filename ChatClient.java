import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        String host = "10.11.5.220";
        int port = 5000;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to the chat server!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMsg, serverMsg;
            while (true) {
                System.out.print("You: ");
                clientMsg = consoleInput.readLine();
                out.println(clientMsg);
                if (clientMsg.equalsIgnoreCase("exit")) break;

                serverMsg = in.readLine();
                if (serverMsg == null || serverMsg.equalsIgnoreCase("exit")) break;
                System.out.println("Server: " + serverMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
