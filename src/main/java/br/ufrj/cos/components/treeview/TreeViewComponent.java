package br.ufrj.cos.components.treeview;


import br.ufrj.cos.components.notification.NotificationDialog;
import br.ufrj.cos.domain.*;
import br.ufrj.cos.service.IoTDomainService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static javax.swing.text.StyleConstants.setIcon;

@Component
@CssImport(value = "./styles/app-styles.css", themeFor = "vaadin-grid")
public class TreeViewComponent extends VerticalLayout {

    private final IoTDomainService ioTDomainService;

    @Autowired
    public TreeViewComponent(IoTDomainService ioTDomainService) {

        this.ioTDomainService = ioTDomainService;
    }

    public void load() {
        // Define columns (e.g., displaying IoT Domain names)
        TreeGrid<TreeNode<?>> treeGrid = new TreeGrid<>();
        treeGrid.addHierarchyColumn(node -> {
            Object data = node.getData();
            if (data instanceof IoTDomain) {
                return ((IoTDomain) data).getName();
            } else if (data instanceof ArchitectureSolution) {
                return ((ArchitectureSolution) data).getName();
            } else if (data instanceof QualityRequirement) {
                return ((QualityRequirement) data).getName();
            } else if (data instanceof Technology) {
                return ((Technology) data).getDescription();
            }
            return "";
        }).setHeader("IoT Domain");

        // Expand all rows by default
        TreeNode<Object> root = ioTDomainService.getTree();
        treeGrid.setItems(root, this::getChildren);

        // Add a click listener to the TreeGrid nodes
        treeGrid.addItemClickListener(event -> {
            TreeNode<IoTDomain> node = event.getItem();
            List<TreeNode<IoTDomain>> path = getPathToRoot(node);

            // Construct the full path string
            StringBuilder pathString = new StringBuilder();
            for (int i = path.size() - 1; i >= 0; i--) {
                pathString.append(path.get(i).getData().getName());
                if (i > 0) {
                    pathString.append(" > ");
                }
            }

            // Print or display the full path
            NotificationDialog.showNotificationDialogOnBotton(node.getData().getName(), pathString.toString());
        });

        // Add styling variant to the TreeGrid for better visibility
        treeGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        // Add the TreeGrid to the main layout
        add(treeGrid);
    }

    private List<TreeNode<IoTDomain>> getPathToRoot(TreeNode<IoTDomain> node) {
        List<TreeNode<IoTDomain>> path = new ArrayList<>();
        TreeNode<IoTDomain> current = node;

        while (current != null) {
            path.add(current);
            current = getParentNode(current);
        }

        Collections.reverse(path);
        return path;
    }

    private List<TreeNode<?>> getChildren(TreeNode<?> node) {
        return node.getChildren();
    }

    private TreeNode<IoTDomain> getParentNode(TreeNode<IoTDomain> node) {
        return node.getParent();
    }
}
