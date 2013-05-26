package application;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableUndirectedGraph;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 26.05.13
 * Time: 13:15
 * To change this template use File | Settings | File Templates.
 */
public class Starter {

    public static void main(String[] args){


        ListenableGraph graph = createK6();
        GraphViewer frame = new GraphViewer(graph);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);


    }

    private static ListenableGraph createK6(){
        // create a JGraphT graph
        ListenableGraph graph = new ListenableUndirectedGraph(DefaultEdge.class);

        // add some sample data (graph manipulated via JGraphT)
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");


        //Verbinde alle Ecken
        Set verexes = new HashSet(graph.vertexSet());
        for (Object startVertex : graph.vertexSet()){
            verexes.remove(startVertex);
            for (Object endVertex : verexes){
                graph.addEdge(startVertex, endVertex);
            }
        }

        return graph;
    }
}
