import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "10.11.5.220"; // Server IP
        int port = 4200; // Server port
        String filePath = "/home/student/CN-Lab/msg.txt"; // Path to the file to send

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to server.");

            // Create output stream to send data
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            // Send filename first
            File file = new File(filePath);
            dataOutputStream.writeUTF(file.getName());

            // Read file and send data
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, bytesRead);
            }

            // Close streams
            dataOutputStream.flush();
            fileInputStream.close();
            System.out.println("File sent successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
