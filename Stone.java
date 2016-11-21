import java.awt.*;

public class Stone {
	int x, y;
	Color color;
	boolean isExist;
	boolean canCatch;
	Rectangle region;
	
	public Stone(int x, int y) {
		this.x = x;
		this.y = y;
		this.color = Color.black;
		isExist = true;
		canCatch = true;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.drawOval(x, y, 20, 20);
	}
	
	public void Destroy() {
		isExist = false;
		this.color = Color.white;
	}
	
	public void setRegion() {
		region = new Rectangle(x,y,20,20);
	}
	
	public boolean contains(int x, int y) {
		return region.contains(x, y);
	}
}
