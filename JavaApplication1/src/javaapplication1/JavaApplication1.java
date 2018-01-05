/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import edu.princeton.cs.introcs.Draw;
import edu.princeton.cs.introcs.StdOut;
import java.util.Random;

/**
 *
 * @author Berhane
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Graph tree = new Graph(15);
        In in = new In(args[0]);
        Graph G = new Graph(in);
        System.out.println(G);
        int s = 7;
        DepthFirstPaths search = new DepthFirstPaths(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : search.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }

        drawGraph(G);

    }

    static void drawGraph(Graph G) {
        Random r = new Random();
        Draw graphic = new Draw();
        //canvas size
        graphic.setCanvasSize(1400, 1400);
        double test = 0.02;
        double startX = 0.5;
        double startY = 0.5;
        double x = 0.5;
        double y = 0.5;
        int numb = 0;
        int vert = 0;
        int shift = 1;
        int mul = 1;
        //for each vertex

        for (int i = 0; i < G.V(); i++) {

            graphic.circle(x, y, 0.01);
            graphic.text(x, y, Integer.toString(numb));
            //vert = 1 + r.nextInt() % G.V();

            if (direction(shift) == 0) {
                y = startY;
                x = startX + (test * mul);
            }
            if (direction(shift) == 1) {
                x = startX;
                y = startY + (test * mul);
            }
            if (direction(shift) == 2) {
                y = startY;
                x = startX - (test * mul);
            }
            if (direction(shift) == 3) {
                x = startX;
                y = startY - (test * mul);
            }
            //diagonaal
            if (direction(shift) == 4) {
                x = startX - (test * mul);
                y = startY - (test * mul);
            }
            if (direction(shift) == 5) {
                x = startX + (test * mul);
                y = startY - (test * mul);
            }
            if (direction(shift) == 6) {
                x = startX + (test * mul);
                y = startY + (test * mul);
            }
            if (direction(shift) == 7) {
                x = startX - (test * mul);
                y = startY + (test * mul);
                shift++;
            }
            shift++;

            if (shift >= 9) {
                shift = 1;
                mul++;
            }

            numb++;

        }
    }

    static int direction(int shift) {
        System.out.println("shift: " + shift);
        switch (shift) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
            case 8:
                return 7;

            default:
                return 10;

        }

    }
}
