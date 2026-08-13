import java.util.Scanner;

public class HammingCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- STEP 1: Input & Binary Conversion ---
        System.out.print("Enter a single character: ");
        char ch = sc.next().charAt(0);
        int asciiVal = (int) ch;

        int[] data = new int[8];
        for (int i = 7; i >= 0; i--) {
            data[7 - i] = (asciiVal >> i) & 1;
        }

        // --- STEP 2: Construct 12-bit Hamming Array ---
        int[] hamming = new int[13]; // Indices 1..12

        hamming[3]  = data[0]; // D1
        hamming[5]  = data[1]; // D2
        hamming[6]  = data[2]; // D3
        hamming[7]  = data[3]; // D4
        hamming[9]  = data[4]; // D5
        hamming[10] = data[5]; // D6
        hamming[11] = data[6]; // D7
        hamming[12] = data[7]; // D8

        // Calculate Parity Bits
        hamming[1] = hamming[3] ^ hamming[5] ^ hamming[7] ^ hamming[9] ^ hamming[11];
        hamming[2] = hamming[3] ^ hamming[6] ^ hamming[7] ^ hamming[10] ^ hamming[11];
        hamming[4] = hamming[5] ^ hamming[6] ^ hamming[7] ^ hamming[12];
        hamming[8] = hamming[9] ^ hamming[10] ^ hamming[11] ^ hamming[12];

        System.out.print("Encoded 12-bit Hamming Code: ");
        printArray(hamming);

        // --- STEP 3: Simulate Transmission Error ---
        System.out.print("\nEnter bit position to corrupt (1-12, or 0 for no error): ");
        int errorPos = sc.nextInt();

        if (errorPos >= 1 && errorPos <= 12) {
            hamming[errorPos] ^= 1; // Flip the bit using XOR
            System.out.print("Corrupted Code:              ");
            printArray(hamming);
        }

        // --- STEP 4: Detect and Correct Error ---
        int c1 = hamming[1] ^ hamming[3] ^ hamming[5] ^ hamming[7] ^ hamming[9] ^ hamming[11];
        int c2 = hamming[2] ^ hamming[3] ^ hamming[6] ^ hamming[7] ^ hamming[10] ^ hamming[11];
        int c4 = hamming[4] ^ hamming[5] ^ hamming[6] ^ hamming[7] ^ hamming[12];
        int c8 = hamming[8] ^ hamming[9] ^ hamming[10] ^ hamming[11] ^ hamming[12];

        int detectedErrorPos = (c8 * 8) + (c4 * 4) + (c2 * 2) + (c1 * 1);

        if (detectedErrorPos == 0) {
            System.out.println("\nResult: No error detected during transmission.");
        } else {
            System.out.println("\nResult: Error detected at position " + detectedErrorPos + "!");
            // Correct the bit
            hamming[detectedErrorPos] ^= 1;
            System.out.print("Corrected Code:              ");
            printArray(hamming);
        }

        // Reconstruct the original character from data bits
        int reconstructedAscii = 0;
        int[] extractedData = {
            hamming[3], hamming[5], hamming[6], hamming[7],
            hamming[9], hamming[10], hamming[11], hamming[12]
        };

        for (int i = 0; i < 8; i++) {
            reconstructedAscii = (reconstructedAscii << 1) | extractedData[i];
        }

        System.out.println("Decoded Character:          '" + (char) reconstructedAscii + "'");

        sc.close();
    }

    // Helper method to print 12-bit array
    private static void printArray(int[] arr) {
        for (int i = 1; i <= 12; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}