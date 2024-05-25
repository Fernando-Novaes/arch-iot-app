package br.ufrj.cos.components.treeview;


import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@CssImport("./styles/styles.css")  // Import the CSS file
public class TreeViewComponent extends VerticalLayout {

    public TreeViewComponent() throws IOException {
        TreeGrid<String> treeGrid = new TreeGrid<>();

        // Parse the JSON file and create the tree structure
        TreeNode rootNode;
        rootNode = TreeNode.fromJson("C:\\Users\\Fernando\\IdeaProjects\\arch-iot-app\\frontend\\tree.json");

        Graph<String, DefaultEdge> tree = TreeBuilder.createTree(rootNode);

        // Add columns to the TreeGrid
        treeGrid.addHierarchyColumn(String::toString).setHeader("Arch-IoT Decision Tool")
                .setClassNameGenerator(this::getNodeClassName);

        // Set the items for the TreeGrid
        treeGrid.setItems(getTreeRoots(tree), parent -> getChildren(parent, tree));

        // Add the TreeGrid to the layout
        add(treeGrid);
    }

    private Set<String> getTreeRoots(Graph<String, DefaultEdge> tree) {
        Set<String> allVertices = new HashSet<>(tree.vertexSet());
        Set<String> childVertices = tree.edgeSet().stream()
                .map(tree::getEdgeTarget)
                .collect(Collectors.toSet());
        allVertices.removeAll(childVertices);
        return allVertices;
    }

    private Collection<String> getChildren(String parent, Graph<String, DefaultEdge> tree) {
        return tree.outgoingEdgesOf(parent).stream()
                .map(tree::getEdgeTarget)
                .collect(Collectors.toSet());
    }

    private String getNodeClassName(String node) {
        if (node.equals("Root")) {
            return "tree-node";
        } else if (node.startsWith("Child")) {
            return "tree-node-child";
        } else if (node.startsWith("GrandChild")) {
            return "tree-node-grandchild";
        }
        return "";
    }

}
