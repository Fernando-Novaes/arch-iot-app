package br.ufrj.cos.components.treeview;


import br.ufrj.cos.components.notification.NotificationDialog;
import br.ufrj.cos.domain.*;
import br.ufrj.cos.service.IoTDomainService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
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

    @PostConstruct
    public void init() {
        TreeGrid<Object> treeGrid = new TreeGrid<>();
        List<IoTDomain> rootNodes = ioTDomainService.findAllDomains();

        treeGrid.addHierarchyColumn(this::getNodeName).setHeader("Name");
        treeGrid.setItems(ioTDomainService.getChildren(rootNodes));

        treeGrid.setHierarchyColumn(new ComponentRenderer<>(item -> {
            Span nameSpan = new Span(getNodeName(item));
            Image icon = new Image();
            setIcon(icon, item);

            icon.setClassName("tree-icon");
            Div container = new Div(icon, nameSpan);
            container.setClassName("tree-item-container");
            return container;
        }).toString());

        treeGrid.addItemClickListener(event -> {
            Object item = event.getItem();
            if (item instanceof Technology technology) {
                List<String> path = ioTDomainService.getPath(technology);
                NotificationDialog
                        .showNotificationDialogOnBotton(technology.getDescription()+":" ,
                                 String.join(" -> ", path));
            }
        });

        add(treeGrid);
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

    private void setIcon(Image icon, Object item) {
        if (item instanceof IoTDomain) {
            icon.setSrc("images/root-icon.png");
        } else if (item instanceof ArchitectureSolution) {
            icon.setSrc("images/collapsible-icon.png");
        } else if (item instanceof QualityRequirement) {
            icon.setSrc("images/collapsible-icon.png");
        } else if (item instanceof Technology) {
            icon.setSrc("images/leaf-icon.png");
        }
    }
}
