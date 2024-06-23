package br.ufrj.cos.views.archiot;

import br.ufrj.cos.components.treeview.TreeViewComponent;
import br.ufrj.cos.views.BaseView;
import br.ufrj.cos.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@PageTitle("Arch IoT - Tool")
@Route(value = "arch-iot", layout = MainLayout.class)
public class ArchIotView extends BaseView {

    @Autowired
    private TreeViewComponent treeView;

    @PostConstruct
    private void init() {
        getContent().add(this.createTreeViewLayout("", treeView));
        treeView.load();

        getContent().add(
                this.createDetailsPanel("Title", "Text Content!!!"));
    }

    public ArchIotView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow = new HorizontalLayout();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        getContent().add(layoutColumn2);
        getContent().add(layoutRow);
        layoutRow.add(this.createHeader("Arch-IoT Tool"));
    }
}
