import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Tile {
	private double x;
	private double y;
	private boolean black;
	private double dy = 0;
	private Color color;
	private int direction;
	public Tile(int x, int y, boolean black, int direction) {
		this.x = x;
		this.y = y;
		this.black = black;
		this.color = black ? Color.BLACK: Color.WHITE;
		this.direction = direction;
	}
	public void draw(Graphics2D g2d) {
		g2d.setColor(black ? color: Color.WHITE);
		g2d.fillRect((int) x, (int) (y - 1), 100, 100);
		if (black) {
			g2d.setColor(Color.WHITE);
			if (direction == 0 || direction == 2) g2d.fillRect((int) (x + (100 - 10) / 2), (int) (y - 1 + 25), 10, 50);
			else g2d.fillRect((int) (x + (50 / 2)), (int) (y - 1 + (100 - 10) / 2), 50, 10);
			if (direction == 0) g2d.fillPolygon(new int[]{(int) (x + (100) / 2 - 10), (int) (x + (100) / 2), (int) (x + (100) / 2 + 10)}, new int[]{(int) (y - 1 + 25), (int) (y - 1), (int) (y - 1 + 25)}, 3);
			else if (direction == 1) g2d.fillPolygon(new int[]{(int) (x + 100 - 25), (int) (x + 100), (int) (x + 100 - 25)}, new int[]{(int) ((y - 1) + (100) / 2 - 10), (int) ((y - 1) + (100) / 2), (int) ((y - 1) + (100) / 2 + 10)}, 3);
			else if (direction == 2) g2d.fillPolygon(new int[]{(int) (x + (100) / 2 - 10), (int) (x + (100) / 2), (int) (x + (100) / 2 + 10)}, new int[]{(int) (y - 1 + 75), (int) (y - 1 + 100), (int) (y - 1 + 75)}, 3);
			else g2d.fillPolygon(new int[]{(int) (x + 25), (int) (x), (int) (x + 25)}, new int[]{(int) ((y - 1) + (100) / 2 - 10), (int) ((y - 1) + (100) / 2), (int) ((y - 1) + (100) / 2 + 10)}, 3);
		}
	}
	public int getX() {
		return (int) x;
	}
	public int getY() {
		return (int) y;
	}
	public boolean getBlack() {
		return black;
	}
	public void setdY(double dy) {
		this.dy = dy;
	}
	public void setColor (Color c) {
		this.color = c;
	}
	public void update() {
		y += dy;
	}
}
