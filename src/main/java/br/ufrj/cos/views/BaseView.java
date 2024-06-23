package br.ufrj.cos.views;

import br.ufrj.cos.components.treeview.TreeViewComponent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@CssImport(value = "./styles/app-styles.css")
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

    /***
     * Get the value from a property name in a properties file
     * @param fileName
     * @param propertyName
     * @return String
     */
    public String getValueFromPropertiesFile(String fileName, String propertyName) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + fileName);
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
        return properties.getProperty(propertyName);
    }
}
