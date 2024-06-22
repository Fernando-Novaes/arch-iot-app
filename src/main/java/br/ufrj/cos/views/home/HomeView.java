package br.ufrj.cos.views.home;

import br.ufrj.cos.components.chart.ChartComponent;
import br.ufrj.cos.views.BaseView;
import br.ufrj.cos.views.MainLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@PageTitle("Arch IoT - Home")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends BaseView {

    private final ChartComponent chart;

    @Autowired
    public HomeView(ChartComponent chart) throws IOException {
        this.chart = chart;
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow = new HorizontalLayout();
        //header
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");

        chart.addData("Architecture", 35);
        chart.addData("Quality Requirement", 25);
        chart.addData("Technology", 40);


        getContent().add(this.createChart("Chart Title"));
    }

    private Image createChart(String chartTitle) throws IOException {
        return chart.createPieChart("Teste");
    }
}
