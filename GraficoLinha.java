package Avaliacao1;

import org.jfree.chart.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import java.awt.Color;

public class GraficoLinha {

	Ativos ativo;
	
	public GraficoLinha(Ativos ativo) {
		this.ativo = ativo;
		criarGrafico();
	}
	
	private void criarGrafico() {

		var mmaCurta  =	new XYSeries("MMA Curta");
		var mmaInterm =	new XYSeries("MMA Intermediaria");
		var mmaLonga  =	new XYSeries("MMA Longa");
		var mmeCurta  =	new XYSeries("MME Curta");
		var mmeInterm =	new XYSeries("MME Intermediaria");
		var mmeLonga  =	new XYSeries("MME Longa");
		var close     =	new XYSeries("Close");
		
		for(int i = 6; i < ativo.getN(); i++) {
			
			close.add		(i, ativo.getClose().get(i));
			mmaCurta.add	(i, ativo.getMediaSimples().get(i).getCurta());
			mmaInterm.add	(i, ativo.getMediaSimples().get(i).getIntermediaria());
			mmaLonga.add	(i, ativo.getMediaSimples().get(i).getLonga());
			mmeCurta.add	(i, ativo.getMediaExponencial().get(i).getCurta());
			mmeInterm.add	(i, ativo.getMediaExponencial().get(i).getIntermediaria());
			mmeLonga.add	(i, ativo.getMediaExponencial().get(i).getLonga());
		}

		var dataset = new XYSeriesCollection();
		dataset.addSeries(close);
		dataset.addSeries(mmaCurta);
	//	dataset.addSeries(mmaInterm);
	//	dataset.addSeries(mmaLonga);
		dataset.addSeries(mmeCurta);
		dataset.addSeries(mmeInterm);
		dataset.addSeries(mmeLonga);

		JFreeChart chart = ChartFactory.createXYLineChart(
				"Evolução das médias móveis do ativo "+ ativo.getId(),
                "Data",
                "Valor",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
				);

		XYPlot plot = chart.getXYPlot();

		var renderer = new XYLineAndShapeRenderer();
        
        renderer.setBaseItemLabelsVisible(true);
    	renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.CYAN);
        
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinesVisible(false);
		plot.setDomainGridlinesVisible(false);
		chart.getXYPlot().getRangeAxis().setRange(ativo.getClose().get(ativo.getN()-1)*0.95, ativo.getClose().get(ativo.getN()-1)*1.05);
		
		chart.getLegend().setFrame(BlockBorder.NONE);

		ChartFrame frame1 = new ChartFrame("Gráfico de linhas", chart);
		frame1.setVisible(true);
		frame1.setSize(1300, 800);

	}
}
