package Week5;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

//@author ENES1
public class Week5 {

    public static void main(String[] args) {
        Graph G = new Graph(new In("C:\\Users\\Arnout\\Desktop\\week 5\\week5\\src\\week5\\mediumEWG.txt"));
        EdgeWeightedGraph EG = new EdgeWeightedGraph(new In("C:\\Users\\Arnout\\Desktop\\week 5\\week5\\src\\week5\\mediumEWG.txt"));

        DepthFirstPaths dfs = new DepthFirstPaths(G, 0);
        KruskalMST mst = new KruskalMST(EG);

        SingleGraph graph = new SingleGraph("monalisa");//make graph drawing
        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.display();

        //add nodes
        for (int i = 0; i < G.V(); i++) {
            graph.addNode("" + i);
            graph.getNode("" + i).addAttribute("ui.label", i);
        }

        //add edges
        for (int j = 0; j < G.V(); j++) {
            for (int b : G.adj(j)) {
                graph.addEdge(j + "-" + b, j, b);
            }
        }

        Thread thread1 = new Thread() {
            public void run() {
                explore_dfs(G, dfs, graph);
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                explore_mst(EG, mst, graph);
            }
        };

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void explore_dfs(Graph G, DepthFirstPaths dfs, SingleGraph graph) {
        boolean marked[] = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                for (int x : dfs.pathTo(v)) {
                    if (marked[x]) {
                        continue;
                    }
                    marked[x] = true;
                    Node next = graph.getNode("" + x);
                    next.addAttribute("ui.class", "dfs");
                    sleep();
                }
            }
        }
        System.out.println("DFS DONE!");
    }

    public static void explore_mst(EdgeWeightedGraph EG, KruskalMST mst, SingleGraph graph) {
        for (EdgeP e : mst.edges()) {
            String edgeweight[] = e.toString().split(" ");
            Edge next = graph.getEdge(edgeweight[0]);
            next.addAttribute("ui.class", "mst");
            sleep();
        }
        System.out.println("MST DONE!");
    }

    protected static void sleep() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
    }

    protected static String styleSheet
            = "node {fill-color: black;}"
            + "node.dfs {fill-color: red;}"
            + "edge { fill-color: black;}"
            + "edge.mst { fill-color: green; shape: angle; arrow-shape: none; size: 5px; }";
}
