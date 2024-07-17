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
import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.config.builder.LegendBuilder;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.annotation.UIScope;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

@UIScope
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PageTitle("Arch IoT - Home")
//@JavaScript(value = "https://unpkg.com/vis-network/standalone/umd/vis-network.min.js")
public class HomeView extends BaseView {

    private final ChartComponent chart;
    private final IoTDomainService domainService;
    private final QualityRequirementService qualityReqService;
    private final ArchitectureSolutionService architectureSolutionService;

    @Autowired
    public HomeView(ChartComponent chart,
                    IoTDomainService domainService,
                    QualityRequirementService qualityReqService,
                    ArchitectureSolutionService architectureSolutionService) {
        this.chart = chart;
        this.domainService = domainService;
        this.qualityReqService = qualityReqService;
        this.architectureSolutionService = architectureSolutionService;


        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
    }

    @PostConstruct
    private void init() {
        this.createChartsLayout();
        this.createDiagramLayout();
    }

    /***
     *
     * @return VerticalLayout
     */
    private VerticalLayout createVerticalContainer() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.getStyle().setBorder("1px solid grey");

        return verticalLayout;
    }

    /***
     * Create the container to show boxes
     * @return HorizontalLayout
     */
    private HorizontalLayout createContainer() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayout.setWidthFull();

        return horizontalLayout;
    }

    private void createDiagramLayout() {
        HorizontalLayout container = createContainer();
        VerticalLayout box01 = createVerticalContainer();

        Div diagram = new Div();
        diagram.setId("mynetwork");
        box01.add(diagram);
        container.add(box01);
        getContent().add(container);
        //Add the custom JavaScript to initialize the network (https://visjs.github.io/vis-network/examples/)
        UI.getCurrent().getPage().executeJs(
                "var nodes = new vis.DataSet([" +
                        "  { id: 0, label: '<b>IoT Domain</b>', group: 0, color: 'lightblue', font: { multi: true, size: 50, face: 'georgia'} }," +
                        "  { id: 1, label: 'Architectural Solution', group: 0, color: 'yellow', font: { multi: true, size: 50, face: 'georgia'}  }," +
                        "  { id: 2, label: 'Quality Requirement', group: 0, color: 'green', font: { multi: true, size: 50, face: 'georgia'}  }," +
                        "  { id: 3, label: 'Technology/Feature', group: 0, color: 'orange', font: { multi: true, size: 50, face: 'georgia'}  }" +

                        "]);" +
                        "var edges = new vis.DataSet([" +
                        "  { from: 0, to: 1, arrows: 'to', shadow: { color: 'black' }}," +
                        "  { from: 1, to: 2, arrows: 'to', shadow: { color: 'black' } }," +
                        "  { from: 2, to: 3, arrows: 'to', shadow: { color: 'black' } }" +

                        "]);" +
                        "var container = document.getElementById('mynetwork');" +
                        "var data = { nodes: nodes, edges: edges };" +
                        "var options = {" +
                        "  nodes: {" +
                        "    shape: 'box'," +
                        "    size: 42," +
                        "    font: { size: 32 }," +
                        "    borderWidth: 2," +
                        "    shadow: true" +
                        "  }," +
                        "  edges: {" +
                        "    width: 2," +
                        "    shadow: true" +
                        "  }," +
                        "  physics: {" +
                        "    enabled: false," +
                        "  }," +
                        "};" +
                        "var network = new vis.Network(container, data, options);"
        );
    }

    /***
     * Create the layout to show all graphs
     * @throws IOException
     */
    private void createChartsLayout() {
        HorizontalLayout container = this.createContainer();
        VerticalLayout box01 = this.createVerticalContainer();
        VerticalLayout box02 = this.createVerticalContainer();
        VerticalLayout box03 = this.createVerticalContainer();
        VerticalLayout box04 = this.createVerticalContainer();

        box01.add(this.createArchitectureSolutionChart("Architectural Solutions"));
        box02.add(this.createIoTDomainChart("IoT Domains"));
        box03.add(this.createQualityRequirementChart("Quality Requirements"));
        box04.add(this.createArchitectureSolutionChart("Architecture Solutions"));

        container.add(box01, box02, box03, box04);

        getContent().add(container);
    }

    private void chartInitialConfig() {
        this.chart.cleanDataset();
        this.chart.withLegend(LegendBuilder.get().withShow(Boolean.FALSE).build()).build();
    }

    /***
     * Create the IoT Domains chart
     * @param chartTitle
     * @return Image
     * @throws IOException
     */
    private ApexCharts createIoTDomainChart(String chartTitle) {
        List<IoTDomainRecord> recordData = this.domainService.getIoTDomainCountGroupedByName();

        this.chartInitialConfig();
        recordData.forEach(d -> {
            this.chart.addData(d.description(), d.qtd(), d.total());
        });

        return chart.createPieChart(String.format("%s [%s]", chartTitle, recordData.size()));
    }

    /***
     * Create the Quality Requirement chart
     * @param chartTitle
     * @throws IOException
     */
    private ApexCharts createQualityRequirementChart(String chartTitle) {
        List<QualityRequirementRecord> recordData = this.qualityReqService.getQualityRequirementCountGroupedByName();

        this.chartInitialConfig();
        recordData.forEach(d -> {
            this.chart.addData(d.description(), d.qtd(), d.total());
        });

        return chart.createPieChart(String.format("%s [%s]", chartTitle, recordData.size()));
    }

    /***
     * Create the Quality Requirement chart
     * @param chartTitle
     * @throws IOException
     */
    private ApexCharts createArchitectureSolutionChart(String chartTitle) {
        List<ArchitectureSolutionRecord> recordData = this.architectureSolutionService.geArchitectureSolutionCountGroupedByName();

        this.chartInitialConfig();
        recordData.forEach(d -> {
            this.chart.addData(d.description(), d.qtd(), d.total());
        });

        return chart.createPieChart(String.format("%s [%s]", chartTitle, recordData.size()));
    }

}
