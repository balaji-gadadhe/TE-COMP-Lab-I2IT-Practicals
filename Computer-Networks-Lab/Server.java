import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 4200; // Port number to listen on
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Create input stream to receive data
            InputStream inputStream = socket.getInputStream();

            // Read the filename first
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String fileName = dataInputStream.readUTF();

            // Prepare to write the received file
            FileOutputStream fileOutputStream = new FileOutputStream("received_" + fileName);

            byte[] buffer = new byte[4096];
            int bytesRead;

            // Read data from client and write to file
            while ((bytesRead = dataInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File received: " + fileName);

            // Close streams and socket
            fileOutputStream.close();
            socket.close();
            System.out.println("Connection closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
