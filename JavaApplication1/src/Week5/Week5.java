package week5;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Week5 {

    public static void main(String[] args) {
        In in = new In("D:\\Arnout\\documenten\\GitHub\\lag\\JavaApplication1\\src\\Week5\\tinyG.txt");
        Graph G = new Graph(in);

        DepthFirstPaths dfs = new DepthFirstPaths(G, 0);
//        Dijkstra pts = new Dijkstra(G, 0, 0);

        SingleGraph graph = new SingleGraph("monalisa");

        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.display();

        for (int i = 0; i < G.V(); i++) {
            graph.addNode("" + i);
        }

        for (int j = 0; j < G.V(); j++) {
            for (int b : G.adj(j)) {
                System.out.println("V: " + j + "B: " + b);
                try{Thread.sleep(200);}catch (Exception e){}
                graph.addEdge(j + "_" + b, j, b);
            }
        }

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }

        explore(G, dfs, 0, graph);
    }

    public static void explore(Graph G, DepthFirstPaths dfs, int s, SingleGraph graph) {
        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                for (int x : dfs.pathTo(v)) {
                    Node next = graph.getNode("" + x);
                    next.setAttribute("ui.class", "dfs");
                    sleep();
                }
            }
        }
    }

    protected static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    protected static String styleSheet
            = "node {fill-color: black;}"
            + "node.dfs {fill-color: red;}"
            + "node.mst {fill-color: green;}";
}