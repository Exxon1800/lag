package Week5;


import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class lagprac2 {

    //3. deel 2
    public static void printGraaf(File file) throws FileNotFoundException {
        ArrayList<Integer> waardes = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextInt()) {
            waardes.add(scanner.nextInt());
        }

        waardes.forEach((p) -> {
            System.out.println(p);
        });
    }

    public static void main(String[] args) throws FileNotFoundException {
        //1. inlezen van de graaf
        File file = new File("graaf100.txt");
        Graph G = new Graph(new In(file));
        //2. print de graaf
        System.out.println(G.toString());
        //3. eigen methode voor afdrukken, zie de rest bovenaan
        printGraaf(file);
        //4. DFS
        DepthFirstPaths dfs = new DepthFirstPaths(G, 0);
        for (int i = 0; i <= 100; i++) {
            System.out.println(dfs.pathTo(i));
        }
        //5. EWG
         EdgeWeightedGraph EGW = new EdgeWeightedGraph(new In("newEWG.txt"));
         for (int i = 0; i <= G.V(); i++) {
            EGW.addEdge((EdgeP) G.adj(i));
        }
        //6. PrimMST
        PrimMST MST = new PrimMST(new In("newEWG.txt"));
        System.out.println(MST.weight());
    }
}
