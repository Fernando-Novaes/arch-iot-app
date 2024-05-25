package br.ufrj.cos.views.archiot;

import br.ufrj.cos.components.treeview.TreeViewComponent;
import br.ufrj.cos.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.io.IOException;

@PageTitle("Arch IoT - Tool")
@Route(value = "arch-iot", layout = MainLayout.class)
public class ArchIotView extends Composite<VerticalLayout> {

    public ArchIotView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow = new HorizontalLayout();
        H5 h5 = new H5();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        h5.setText("Heading");
        h5.setWidth("max-content");
        getContent().add(layoutColumn2);
        getContent().add(layoutRow);
        layoutRow.add(h5);

        TreeViewComponent treeView = null;
        try {
            treeView = new TreeViewComponent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getContent().add(treeView);
    }
}
