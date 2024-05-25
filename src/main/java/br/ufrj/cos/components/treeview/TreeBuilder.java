package br.ufrj.cos.components.treeview;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public class TreeBuilder {
    public static Graph<String, DefaultEdge> createTree(TreeNode rootNode) {
        Graph<String, DefaultEdge> tree = new SimpleDirectedGraph<>(DefaultEdge.class);
        addNodesAndEdges(tree, rootNode, null);
        return tree;
    }

    private static void addNodesAndEdges(Graph<String, DefaultEdge> tree, TreeNode node, String parent) {
        tree.addVertex(node.getName());
        if (parent != null) {
            tree.addEdge(parent, node.getName());
        }
        for (TreeNode child : node.getChildren()) {
            addNodesAndEdges(tree, child, node.getName());
        }
    }
}
