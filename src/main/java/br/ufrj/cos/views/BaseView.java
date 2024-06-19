package br.ufrj.cos.views;

import br.ufrj.cos.components.treeview.TreeViewComponent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class BaseView extends Composite<VerticalLayout> {

    /***
     * Create the Header of the Page
     * @param text
     * @return H5
     */
    public H5 createHeader(String text) {
        H5 header = new H5();
        header.setText(text);
        header.setWidth("max-content");

        return header;
    }

    /***
     * Create the layout to the Tree View
     * @param title
     * @param treeView
     * @return Details
     */
    public Details createTreeViewLayout(String title, TreeViewComponent treeView) {
        Details treeViewDetails = new Details(title, treeView);
        treeViewDetails.setOpened(true);
        treeViewDetails.setWidth("100%");
        treeViewDetails.setHeight("100%");
        treeViewDetails.addThemeVariants(DetailsVariant.FILLED);

        return treeViewDetails;
    }

    /***
     * Create container details
     * @param title
     * @param textContent
     * @return
     */
    public Details createDetailsPanel(String title, String textContent) {
        HorizontalLayout content = new HorizontalLayout(
                new Span(textContent)
        );

        Details details = new Details(title, content);
        details.setOpened(true);
        details.addThemeVariants(DetailsVariant.FILLED);

        return details;
    }

}
