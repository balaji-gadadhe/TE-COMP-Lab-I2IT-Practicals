import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port + "...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMsg, serverMsg;
            while ((clientMsg = in.readLine()) != null) {
                System.out.println("Client: " + clientMsg);
                if (clientMsg.equalsIgnoreCase("exit")) break;

                System.out.print("Server: ");
                serverMsg = consoleInput.readLine();
                out.println(serverMsg);
                if (serverMsg.equalsIgnoreCase("exit")) break;
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
