import java.util.Scanner;

public class SelectiveRepeat {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int totalFrames, windowSize;

        System.out.print("Enter Total Number of Frames: ");
        totalFrames = sc.nextInt();

        System.out.print("Enter Window Size: ");
        windowSize = sc.nextInt();

        boolean[] received = new boolean[totalFrames];

        int base = 0;

        while (base < totalFrames) {

            System.out.println("\nSending Window:");

            int end = Math.min(base + windowSize, totalFrames);

            for (int i = base; i < end; i++) {
                if (!received[i]) {
                    System.out.println("Frame " + i + " Sent");
                }
            }

            System.out.print("\nEnter Lost Frame (-1 for No Loss): ");
            int lost = sc.nextInt();

            if (lost == -1) {

                for (int i = base; i < end; i++) {
                    received[i] = true;
                }

            } else {

                for (int i = base; i < end; i++) {

                    if (i != lost) {
                        received[i] = true;
                        System.out.println("ACK Received for Frame " + i);
                    }
                }

                System.out.println("Frame " + lost + " Lost.");
                System.out.println("Retransmitting Only Frame " + lost);

                received[lost] = true;
                System.out.println("ACK Received for Frame " + lost);
            }

            while (base < totalFrames && received[base]) {
                base++;
            }
        }

        System.out.println("\nAll Frames Successfully Transmitted.");

        sc.close();
    }
}