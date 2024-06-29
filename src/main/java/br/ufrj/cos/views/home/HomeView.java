package br.ufrj.cos.views.home;

import br.ufrj.cos.components.chart.ChartComponent;
import br.ufrj.cos.service.ArchitectureSolutionService;
import br.ufrj.cos.service.IoTDomainService;
import br.ufrj.cos.service.QualityRequirementService;
import br.ufrj.cos.views.BaseView;
import br.ufrj.cos.views.MainLayout;
import br.ufrj.cos.views.data.ArchitectureSolutionRecord;
import br.ufrj.cos.views.data.IoTDomainRecord;
import br.ufrj.cos.views.data.QualityRequirementRecord;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.util.List;

@UIScope
@PageTitle("Arch IoT - Home")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends BaseView {

    private final ChartComponent chart;
    private final IoTDomainService domainService;
    private final QualityRequirementService qualityReqService;
    private final ArchitectureSolutionService architectureSolutionService;

    HorizontalLayout layoutRow;

    @Autowired
    public HomeView(ChartComponent chart,
                    IoTDomainService domainService,
                    QualityRequirementService qualityReqService,
                    ArchitectureSolutionService architectureSolutionService) throws IOException {
        this.chart = chart;
        this.domainService = domainService;
        this.qualityReqService = qualityReqService;
        this.architectureSolutionService = architectureSolutionService;

        this.layoutRow = new HorizontalLayout();
        //header
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        getContent().add(this.layoutRow);
    }

    @PostConstruct
    private void init() throws IOException {
        this.createTopLayoutDataCount();
        this.createIoTDomainChart("IoT Domains");
        this.createQualityRequirementChart("Quality Requirements");
        this.createArchitectureSolutionChart("Architecture Solutions");
    }

    private void createTopLayoutDataCount() {
        HorizontalLayout hl01 = new HorizontalLayout();
        hl01.setWidth("25%");
        hl01.setAlignItems(FlexComponent.Alignment.CENTER);
        VerticalLayout vlDesc1 = new VerticalLayout();
        H1 h01 = new H1();
        h01.add("Architecture Solution");
        vlDesc1.add(h01);
        VerticalLayout vlCount1 = new VerticalLayout();
        H5 h05 = new H5();
        h01.add("00");
        vlCount1.add(h05);

        HorizontalLayout hl02 = new HorizontalLayout();
        hl02.setWidth("25%");
        hl02.setAlignItems(FlexComponent.Alignment.CENTER);

        HorizontalLayout hl03 = new HorizontalLayout();
        hl03.setWidth("25%");
        hl03.setAlignItems(FlexComponent.Alignment.CENTER);

        HorizontalLayout hl04 = new HorizontalLayout();
        hl04.setWidth("25%");
        hl04.setAlignItems(FlexComponent.Alignment.CENTER);

        this.layoutRow.add(hl01, hl02, hl03, hl04);
    }

    /***
     * Create the IoT Domains chart
     * @param chartTitle
     * @return Image
     * @throws IOException
     */
    private void createIoTDomainChart(String chartTitle) throws IOException {
        List<IoTDomainRecord> recordData = this.domainService.getIoTDomainCountGroupedByName();

        this.chart.cleanDataset();
        recordData.forEach(d -> {
            this.chart.addData(d.description(), d.qtd(), d.total());
        });

        getContent().add(chart.createPieChart(chartTitle));
    }

    /***
     * Create the Quality Requirement chart
     * @param chartTitle
     * @throws IOException
     */
    private void createQualityRequirementChart(String chartTitle) throws IOException {
        List<QualityRequirementRecord> recordData = this.qualityReqService.getQualityRequirementCountGroupedByName();

        this.chart.cleanDataset();
        recordData.forEach(d -> {
            this.chart.addData(d.description(), d.qtd(), d.total());
        });

        getContent().add(chart.createPieChart(chartTitle));
    }

    /***
     * Create the Quality Requirement chart
     * @param chartTitle
     * @throws IOException
     */
    private void createArchitectureSolutionChart(String chartTitle) throws IOException {
        List<ArchitectureSolutionRecord> recordData = this.architectureSolutionService.geArchitectureSolutionCountGroupedByName();

        this.chart.cleanDataset();
        recordData.forEach(d -> {
            this.chart.addData(d.description(), d.qtd(), d.total());
        });

        getContent().add(chart.createPieChart(chartTitle));
    }

}
