package br.ufrj.cos.components.treeview;


import br.ufrj.cos.components.notification.NotificationDialog;
import br.ufrj.cos.domain.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@CssImport(value = "./styles/app-styles.css", themeFor = "vaadin-grid")
public class TreeViewComponent extends VerticalLayout {

    private Graph<Object, DefaultEdge> tree;

    public TreeViewComponent() throws IOException {
        TreeGrid<Object> treeGrid = new TreeGrid<>();

        // Parse the JSON file and create the tree structure
        List<IoTDomain> rootNodes;
        try {
            rootNodes = JsonReader.readJson("C:\\Users\\Fernando\\IdeaProjects\\arch-iot-app\\frontend\\tree.json");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        tree = TreeBuilder.createTree(rootNodes);

        // Add columns to the TreeGrid
        treeGrid.addHierarchyColumn(this::getNodeName).setHeader("IOT DOMAIN -> ARCHITECTURE SOLUTION -> QUALITY REQUIREMENT -> TECHNOLOGY")
                .setClassNameGenerator(this::getNodeClassName);

        // Set the items for the TreeGrid
        treeGrid.setItems(getTreeRoots(tree), parent -> getChildren(parent, tree));

        // Add a click listener to the TreeGrid
        treeGrid.addItemClickListener(event -> {
            Object item = event.getItem();
            if (item instanceof Technology technology) {
                List<String> path = getPath(technology);
                NotificationDialog
                        .showNotificationDialogOnBotton(technology.getDescription()+":" ,
                                 String.join(" -> ", path));
            }
        });

        // Add the TreeGrid to the layout
        add(treeGrid);
    }

    private List<String> getPath(Object leaf) {
        List<String> path = new ArrayList<>();
        Object current = leaf;
        while (current != null) {
            path.add(0, getNodeName(current));
            current = getParent(current);
        }
        return path;
    }

    private Object getParent(Object child) {
        for (DefaultEdge edge : tree.incomingEdgesOf(child)) {
            return tree.getEdgeSource(edge);
        }
        return null;
    }

    private String getNodeName(Object node) {
        if (node instanceof IoTDomain) {
            return ((IoTDomain) node).getName();
        } else if (node instanceof ArchitectureSolution) {
            return ((ArchitectureSolution) node).getName();
        } else if (node instanceof QualityRequirement) {
            return ((QualityRequirement) node).getName();
        } else if (node instanceof Technology) {
            return ((Technology) node).getDescription();
        }
        return "";
    }

    private Set<Object> getTreeRoots(Graph<Object, DefaultEdge> tree) {
        Set<Object> allVertices = new HashSet<>(tree.vertexSet());
        Set<Object> childVertices = tree.edgeSet().stream()
                .map(tree::getEdgeTarget)
                .collect(Collectors.toSet());
        allVertices.removeAll(childVertices);
        return allVertices;
    }

    private Collection<Object> getChildren(Object parent, Graph<Object, DefaultEdge> tree) {
        return tree.outgoingEdgesOf(parent).stream()
                .map(tree::getEdgeTarget)
                .collect(Collectors.toSet());
    }

    private String getNodeClassName(Object node) {
        if (node instanceof IoTDomain) {
            return "iot-domain";
        } else if (node instanceof ArchitectureSolution) {
            return "architecture-solution";
        } else if (node instanceof QualityRequirement) {
            return "quality-requirement";
        } else if (node instanceof Technology) {
            return "technology";
        }
        return "";
    }

}
