import java.awt.Color;
import java.awt.Graphics2D;

public class ProgressBar {
	private double x;
	private double y;
	private Color color;
	private int width;
	private double progress = 0;
	private static final int HEIGHT = 10;
	public ProgressBar(int x, int y, Color color, int width) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.width = width;
	}
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillRect((int) x, (int) (y - 1), (int) (width * progress), HEIGHT);
	}
	public void update(double progress) {
		this.progress = progress;
	}
}
