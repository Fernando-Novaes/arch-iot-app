package br.ufrj.cos.components.chart;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class ChartComponent {

    public static File createPieChart() throws IOException {
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        File imageFile = File.createTempFile("pie_chart", ".png");
        ChartUtils.saveChartAsPNG(imageFile, chart, 800, 600);

        return imageFile;
    }

    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Category A", 50);
        dataset.setValue("Category B", 20);
        dataset.setValue("Category C", 30);
        return dataset;
    }

    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart Example",
                dataset,
                true,
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Category A", new Color(255, 102, 102));
        plot.setSectionPaint("Category B", new Color(102, 255, 102));
        plot.setSectionPaint("Category C", new Color(102, 102, 255));
        plot.setBackgroundPaint(Color.white);

        return chart;
    }

}
