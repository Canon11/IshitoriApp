import java.awt.*;
import javax.swing.*;

public class MyCanvas extends JPanel {
	PlayerSystem playerSystem;
	Stone[] stones = new Stone[15];
	int count = 15;
	
	public MyCanvas(PlayerSystem playerSystem) {
		this.playerSystem = playerSystem;
		setBackground(Color.white);
		for (int i = 0; i < 3; i++) {
			stones[i] = new Stone(100 + 30*i, 30);
		}
		for (int i = 0; i < 5; i++) {
			stones[i+3] = new Stone(70 + 30*i, 60);
		}
		for (int i = 0; i < 7; i++) {
			stones[i+8] = new Stone(40 + 30*i, 90);
		}
		setRegion();
		canCatchSet(0);
		showCanColor();
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < stones.length; i++) {
			stones[i].draw(g);
		}
	}
	
	public void setRegion() {
		for (Stone d : stones) {
			d.setRegion();
		}
	}

	public void onClicked(int x, int y) {
		for (int i = 0; i < stones.length; i++) {
			if (stones[i].contains(x,y) && stones[i].canCatch) {
				playerSystem.catchStone();
				stones[i].Destroy();
				canCatchSet(i);
				showCanColor();
				count--;
			}
		}			
	}
	
	public boolean finishClicked(int x, int y) {
		for (int i = 0; i < stones.length; i++) {
			if (stones[i].contains(x,y) && stones[i].canCatch) {
				stones[i].Destroy();
				playerSystem.changeTurn();
				repaint();
				return true;
			}
		}
		return false;
	}
	
	public void canCatchSet(int index) {
		if (playerSystem.currentPlayer.getStock() == 3) {
			for (Stone d : stones)
				d.canCatch = true;
		} else if (playerSystem.currentPlayer.getStock() == 2) {
			for (Stone d : stones)
				d.canCatch = false;
			if (index == 0 || index == 3 || index == 8)
				stones[index + 1].canCatch = true;
			else if (index == 2 || index == 7 || index == 14)
				stones[index - 1].canCatch = true;
			else {
				stones[index - 1].canCatch = true;
				stones[index + 1].canCatch = true;
			}
		} else {
			if (index == 0 || index == 3 || index == 8)
				stones[index + 1].canCatch = true;
			else if (index == 2 || index == 7 || index == 14)
				stones[index - 1].canCatch = true;
			else {
				stones[index - 1].canCatch = true;
				stones[index + 1].canCatch = true;
			}
		}
	}
		
	public void showCanColor() {
		for (Stone d : stones) {
			if (d.canCatch && d.isExist)
				d.color = Color.RED;
			else if (d.isExist)
				d.color = Color.BLACK;
		}
		repaint();
	}
}