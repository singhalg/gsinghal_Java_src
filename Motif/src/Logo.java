import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Logo {

	private static final int W = 800, H = 600;

	private double[][] pwm;

	private double[] info;

	public Logo(double[][] pwm, double[] info) {
		super();
		this.pwm = pwm;
		this.info = info;

	}

	private Color[] colors = { Color.GREEN, Color.BLUE, Color.YELLOW, Color.RED };

	private String bases = "ACGT";

	public void draw(Graphics g, int width, int height) {
		int M = 50;
		int H = height - 2 * M, W = width - 2 * M;
		g.drawLine(M, M, M, M + H);
		g.drawLine(M, M + H, M + W, M + H);
		g.drawString("0 bits", 10, M + H);
		g.drawString("1 bit", 10, M + H / 2);
		g.drawString("2 bits", 10, M);
		int cw = W / info.length;
		for (int i = 0; i < info.length; i++) {
			int[] ranked = getRanked(pwm[i]);

			double total = 0;
			for (int j = 0; j < ranked.length; j++) {
				double pct = pwm[i][ranked[j]];
				g.setColor(colors[ranked[j]]);
				int bottom = (int) (M + H - H * (total * info[i]) / 2);
				int size = (int) (H * (pct * info[i]) / 2);

				g.fillRect(cw * i + M, bottom - size, cw, size);
				g.setColor(Color.BLACK);
				g.drawRect(cw * i + M, bottom - size, cw, size);
				if (size > 10) {
					g.drawString(bases.substring(ranked[j], ranked[j] + 1), M
							+ cw * i + cw / 2, bottom - size / 2 + 5);
				}
				total += pct;
			}
		}
	}

	private int[] getRanked(double[] ds) {
		int[] x = new int[ds.length];
		for (int i = 0; i < ds.length; i++) {
			x[i] = i;
		}

		for (int i = 0; i < ds.length; i++) {
			int mini = i;
			for (int j = i + 1; j < ds.length; j++) {
				if (ds[x[j]] < ds[x[mini]]) {
					mini = j;
				}
			}
			int temp = x[mini];
			x[mini] = x[i];
			x[i] = temp;

		}
		return x;
	}

	public void displayLogo() {

		JFrame frame = new JFrame("Logo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new LogoPanel(this, W, H));
		frame.pack();
		frame.setVisible(true);
	}

	public void toFile(String filename) throws IOException {
		BufferedImage bi = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
		Graphics ig = bi.createGraphics();
		draw(ig, W, H);
		ImageIO.write(bi, "JPEG", new File(filename));
	}

	private static class LogoPanel extends JPanel {
		private Logo logo;

		public LogoPanel(Logo logo, int width, int height) {
			setPreferredSize(new Dimension(width, height));
			this.logo = logo;
		}

		public void paintComponent(Graphics g) {
			logo.draw(g, getWidth(), getHeight());
		}
	}

}
