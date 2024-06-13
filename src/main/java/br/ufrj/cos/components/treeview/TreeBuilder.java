package br.ufrj.cos.components.treeview;

import br.ufrj.cos.domain.ArchitectureSolution;
import br.ufrj.cos.domain.IoTDomain;
import br.ufrj.cos.domain.QualityRequirement;
import br.ufrj.cos.domain.Technology;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;

import java.util.List;

public class TreeBuilder {

    public static Graph<Object, DefaultEdge> createTree(List<IoTDomain> rootNodes) {
        Graph<Object, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        for (IoTDomain rootNode : rootNodes) {
            addNodesAndEdges(graph, rootNode, null);
        }
        return graph;
    }

    private static void addNodesAndEdges(Graph<Object, DefaultEdge> graph, Object node, Object parent) {
        graph.addVertex(node);
        if (parent != null) {
            graph.addEdge(parent, node);
        }

        if (node instanceof IoTDomain) {
            IoTDomain domain = (IoTDomain) node;
            for (ArchitectureSolution arch : domain.getArchs()) {
                addNodesAndEdges(graph, arch, node);
            }
        } else if (node instanceof ArchitectureSolution) {
            ArchitectureSolution solution = (ArchitectureSolution) node;
            for (QualityRequirement qr : solution.getQrs()) {
                addNodesAndEdges(graph, qr, node);
            }
        } else if (node instanceof QualityRequirement) {
            QualityRequirement qr = (QualityRequirement) node;
            for (Technology tech : qr.getTechs()) {
                addNodesAndEdges(graph, tech, node);
            }
        }
    }
}
