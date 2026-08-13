import java.util.Scanner;

public class GoBackN {

    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int totalFrames, windowSize;

    System.out.print("Enter Total Number of Frames: ");
    totalFrames = sc.nextInt();

    System.out.print("Enter Window Size: ");
    windowSize = sc.nextInt();

    int i = 0;

    while (i < totalFrames) {

        System.out.println("\nSending Frames:");

        int end = Math.min(i + windowSize, totalFrames);

        for (int j = i; j < end; j++) {
            System.out.println("Frame " + j + " Sent");
        }

        System.out.print("\nEnter Frame Number Lost (-1 for No Loss): ");
        int lost = sc.nextInt();

        if (lost == -1) {
            System.out.println("Acknowledgement Received for All Frames.");
            i = end;
        } else {
            System.out.println("Frame " + lost + " Lost.");
            System.out.println("Retransmitting from Frame " + lost);

            i = lost;

        }
    }

    System.out.println("\nAll Frames Successfully Transmitted.");
    sc.close();
    }
}