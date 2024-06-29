package br.ufrj.cos.views.qualitreq;

import br.ufrj.cos.views.BaseView;
import br.ufrj.cos.views.MainLayout;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Arch IoT - Quality Requirement")
@Route(value = "qualityreq", layout = MainLayout.class)
public class QualityReqView extends BaseView {

    private final String PROPERTY_FILE_QR = "quality-requirement.properties";

    public QualityReqView() {
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
        layoutRow.add(this.createHeader("Quality Requirement (ISO 2510:2023)"));

        this.createPageContent();
    }

    /***
     * Add a container with the main QR name and definition, and all sub QR
     * @param title
     * @param content
     * @return Details
     */
    private Details addMainQualityRequirementContainer(String title, String content, List<AccordionPanel> accordionPanels) throws IOException {
        Details details = new Details();
        details.setSummary(new Span(this.getValueFromPropertiesFile(PROPERTY_FILE_QR, content)));
        details.setSummaryText(title);
        details.setOpened(true);
        details.addThemeVariants(DetailsVariant.FILLED);
        details.setWidthFull();
        details.addClassName("custom-summary");
        details.getStyle().set("border-radius", "10px");
        details.getStyle().set("background-color", "white");
        details.getStyle().set("border-width", "1px");
        details.getStyle().set("border-color", "white");
        accordionPanels.forEach(details::add);

        return details;
    }

    /***
     * Add a container with QR name and definition
     * @param title
     * @param content
     * @return AccordionPanel
     */
    private AccordionPanel addQualityRequirementContainer(String title, String content) throws IOException {
       //Content
        VerticalLayout vl = new VerticalLayout();
        vl.setWidth("100%");
        vl.setHeight("100%");
        vl.setAlignSelf(FlexComponent.Alignment.CENTER);
        H4 h4 = new H4();
        h4.add(this.getValueFromPropertiesFile(PROPERTY_FILE_QR, content));
        vl.add(h4);

        AccordionPanel qrlInfoPanel = new AccordionPanel();
        qrlInfoPanel.addThemeVariants(DetailsVariant.REVERSE);
        qrlInfoPanel.setOpened(true);
        qrlInfoPanel.setWidthFull();
        qrlInfoPanel.setHeightFull();

        Accordion accordion = new Accordion();
        accordion.setSizeFull();
        qrlInfoPanel = accordion.add(title, vl);
        qrlInfoPanel.setOpened(true);

        return qrlInfoPanel;
    }

    private void createPageContent() {
        try {
            //Functional Suitability
            List<AccordionPanel> functionalSuitability = new ArrayList<>() ;
            functionalSuitability.add(this.addQualityRequirementContainer("Functional Completeness", "Functional_completeness"));
            functionalSuitability.add(this.addQualityRequirementContainer("Functional Correctness", "Functional_correctness"));
            functionalSuitability.add(this.addQualityRequirementContainer("Functional Appropriateness", "Functional_appropriateness"));
            getContent().add(this.addMainQualityRequirementContainer("Functional Suitability", "Functional_Suitability", functionalSuitability));

            //Performance_Efficiency
            List<AccordionPanel> performanceEfficiency = new ArrayList<>() ;
            performanceEfficiency.add(this.addQualityRequirementContainer("Time Behavior", "Time_behavior"));
            performanceEfficiency.add(this.addQualityRequirementContainer("Resource Utilization", "Resource_utilization"));
            performanceEfficiency.add(this.addQualityRequirementContainer("Capacity", "Capacity"));
            getContent().add(this.addMainQualityRequirementContainer("Performance Efficiency", "Performance_Efficiency", performanceEfficiency));

            //Compatibility
            List<AccordionPanel> compatibility = new ArrayList<>() ;
            compatibility.add(this.addQualityRequirementContainer("Interoperability", "Interoperability"));
            compatibility.add(this.addQualityRequirementContainer("Co-existence", "Co_existence"));
            getContent().add(this.addMainQualityRequirementContainer("Compatibility", "Compatibility", compatibility));

            //Interaction_Capability
            List<AccordionPanel> interactionCapability = new ArrayList<>() ;
            interactionCapability.add(this.addQualityRequirementContainer("Appropriateness Recognizability", "Appropriateness_recognizability"));
            interactionCapability.add(this.addQualityRequirementContainer("Learnability", "Learnability"));
            interactionCapability.add(this.addQualityRequirementContainer("Operability", "Operability"));
            interactionCapability.add(this.addQualityRequirementContainer("User Error Protection", "User_error_protection"));
            interactionCapability.add(this.addQualityRequirementContainer("User Engagement", "User_engagement"));
            interactionCapability.add(this.addQualityRequirementContainer("Inclusivity", "Inclusivity"));
            interactionCapability.add(this.addQualityRequirementContainer("User Assistance", "User_assistance"));
            interactionCapability.add(this.addQualityRequirementContainer("Self Descriptiveness", "Self_descriptiveness"));
            getContent().add(this.addMainQualityRequirementContainer("Interaction Capability", "Interaction_Capability", interactionCapability));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}