package br.ufrj.cos.components.chart;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;

import com.vaadin.flow.component.html.Image;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.util.SortOrder;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.stereotype.Component;

@Component
public class ChartComponent {

    /***
     * Create the Pie chart
     * @return Vaadin Image
     * @throws IOException
     */
    public Image createPieChart(HashMap<String, Integer> dataSet) throws IOException {
        PieDataset dataset = createDataset(dataSet);
        JFreeChart chart = createChart(dataset);

        File imageFile = File.createTempFile("pie_chart", ".png");
        ChartUtils.saveChartAsPNG(imageFile, chart, 800, 600);

        return this.convertToImage(imageFile);
    }

    /***
     * get the chart return and convert to Vaadin image
     * @param imageFile
     * @return
     * @throws IOException
     */
    private Image convertToImage(File imageFile) throws IOException {
        byte[] imageBytes = Files.readAllBytes(
                Paths.get(imageFile.toURI()));

        // Convert to Base64
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        String imageUrl = "data:image/png;base64," + base64Image;

        // Create Vaadin Image component
        Image image = new Image(imageUrl, "Example image");
        image.setWidth("600px");
        image.setHeight("400px");

        return image;
    }

    private PieDataset createDataset(HashMap<String, Integer> dataSet) {
        DefaultPieDataset chartDataset = new DefaultPieDataset();

        dataSet.keySet().forEach(k -> {
            chartDataset.setValue(k, dataSet.get(k));
        });

        chartDataset.sortByKeys(SortOrder.ASCENDING);
        return chartDataset;
    }

    private JFreeChart createChart(PieDataset dataset) {
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
