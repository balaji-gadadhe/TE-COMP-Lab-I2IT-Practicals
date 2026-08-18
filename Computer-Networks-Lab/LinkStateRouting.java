import java.util.Scanner;

public class LinkStateRouting {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of routers: ");
        int n = sc.nextInt();

        int[][] cost = new int[n][n];

        System.out.println("Enter cost matrix:");
        System.out.println("(Enter 0 if there is no direct link)");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();

                // Convert 0 (except diagonal) to infinity
                if (cost[i][j] == 0 && i != j)
                    cost[i][j] = 999;
            }
        }

        System.out.print("Enter source router (0 to " + (n - 1) + "): ");
        int source = sc.nextInt();

        int[] distance = new int[n];
        boolean[] visited = new boolean[n];

        // Initially, distance is the direct cost from source
        for (int i = 0; i < n; i++) {
            distance[i] = cost[source][i];
            visited[i] = false;
        }

        distance[source] = 0;

        // Dijkstra's Algorithm
        for (int count = 0; count < n - 1; count++) {

            int min = 999;
            int u = -1;

            // Find the unvisited router with minimum distance
            for (int i = 0; i < n; i++) {
                if (!visited[i] && distance[i] < min) {
                    min = distance[i];
                    u = i;
                }
            }

            if (u == -1)
                break;

            visited[u] = true;

            // Update distances
            for (int v = 0; v < n; v++) {
                if (!visited[v] &&
                    cost[u][v] != 999 &&
                    distance[u] + cost[u][v] < distance[v]) {

                    distance[v] = distance[u] + cost[u][v];
                }
            }
        }

        // Display routing table
        System.out.println("\nRouting Table");
        System.out.println("-------------------------");
        System.out.println("Router\tCost");

        for (int i = 0; i < n; i++) {
            System.out.println(i + "\t" + distance[i]);
        }

        sc.close();
    }
}