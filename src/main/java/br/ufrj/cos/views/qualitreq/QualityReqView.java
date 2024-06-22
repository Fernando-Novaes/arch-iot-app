package br.ufrj.cos.views.qualitreq;

import br.ufrj.cos.views.BaseView;
import br.ufrj.cos.views.MainLayout;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Arch IoT - Quality Requirement")
@Route(value = "qualityreq", layout = MainLayout.class)
public class QualityReqView extends BaseView {

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
        layoutRow.add(this.createHeader("Quality requirement"));
        getContent().add(this.createQualityRequirementListLayout());
    }

    private AccordionPanel createQualityRequirementListLayout() {
       //Content
        VerticalLayout vl = new VerticalLayout();
        vl.setWidth("100%");
        vl.setHeight("100%");
        vl.setAlignSelf(FlexComponent.Alignment.CENTER);
        H3 h3 = new H3();
        h3.add("The degree to which the set of functions covers all specified tasks and objectives.");
        vl.add(h3);

        Accordion accordion = new Accordion();
        AccordionPanel qrlInfoPanel = new AccordionPanel();
        qrlInfoPanel.add(
            accordion.add("Personal information", vl));

        qrlInfoPanel.addThemeVariants(DetailsVariant.FILLED);
        qrlInfoPanel.setOpened(true);
        qrlInfoPanel.setWidthFull();
        qrlInfoPanel.setHeightFull();

        Accordion accordion2 = new Accordion();
        qrlInfoPanel.add(
                accordion2.add("Personal information", vl));

        Accordion accordion3 = new Accordion();
        qrlInfoPanel.add(
            accordion3.add("Personal information", vl));


        return qrlInfoPanel;
    }
}
