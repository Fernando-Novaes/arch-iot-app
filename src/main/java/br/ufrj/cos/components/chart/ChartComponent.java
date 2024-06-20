package br.ufrj.cos.components.chart;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Random;

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
    public Image createPieChart(HashMap<String, Integer> dataSet, String chartTitle) throws IOException {
        PieDataset chartDataset = createDataset(dataSet);
        JFreeChart chart = createChart(chartDataset, chartTitle);

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

    private JFreeChart createChart(PieDataset dataset,String chartTitle) {
        JFreeChart chart = ChartFactory.createPieChart(
                chartTitle,
                dataset,
                true,
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        dataset.getKeys().forEach(k->{

            plot.setSectionPaint(k.toString(), this.getRandomColor());

        });

        plot.setBackgroundPaint(Color.white);

        return chart;
    }

    /***
     * Random color generator
     * @return Color
     */
    private Color getRandomColor() {
        final Random RANDOM = new Random();
        int red = RANDOM.nextInt(256);
        int green = RANDOM.nextInt(256);
        int blue = RANDOM.nextInt(256);
        return new Color(red, green, blue);
    }


}
