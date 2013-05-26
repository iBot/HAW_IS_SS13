package application;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableUndirectedGraph;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 26.05.13
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public class GraphViewer extends JFrame {

    private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
    //
    private JGraphModelAdapter m_jgAdapter;

    public GraphViewer(ListenableGraph graph) {
        // create a JGraphT graph
//        ListenableGraph graph = new ListenableUndirectedGraph(DefaultEdge.class);

        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter(graph);

        JGraph jgraph = new JGraph(m_jgAdapter);
        jgraph.setDragEnabled(false);
        jgraph.setDisconnectable(false);

        jgraph.setBackground(Color.darkGray);
        getContentPane().add(jgraph);
        resize(DEFAULT_SIZE);


        // position vertices nicely within JGraph component
        positionVertexAt("A", 200, 240);
        positionVertexAt("B", 400, 120);
        positionVertexAt("C", 600, 240);
        positionVertexAt("D", 200, 360);
        positionVertexAt("E", 400, 480);
        positionVertexAt("F", 600, 360);

        // that's all there is to it!...
    }

    private void positionVertexAt(Object vertex, int x, int y) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell(vertex);
        Map attr = cell.getAttributes();
        Rectangle b = GraphConstants.getBounds(attr).getBounds();

        GraphConstants.setBounds(attr, new Rectangle(x, y, b.width, b.height));

        Map<DefaultGraphCell, Map> cellAttr = new HashMap<DefaultGraphCell, Map>();
        cellAttr.put(cell, attr);
//        m_jgAdapter.edit( cellAttr, null, null, null, null );
        m_jgAdapter.edit(cellAttr, null, null, null);
    }
}
