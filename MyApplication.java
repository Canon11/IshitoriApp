import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyApplication extends JFrame implements ActionListener, MouseListener {
	MyCanvas canvas;
	PlayerSystem playerSystem;
	JButton nextButton, restartButton;
	JPanel panel;
	JLabel label;
	
	public MyApplication() {
		super("Ishitori Game!");
		label = new JLabel();
		setLayout(new BorderLayout());
		panel = new JPanel();
		nextButton = new JButton("次のプレイヤーへ");
		restartButton = new JButton("リスタート");
		start();

		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		restartButton.addActionListener(this);
		panel.add(label);
		panel.add(restartButton);
		
		nextButton.addActionListener(this);
		restartButton.addActionListener(this);
		addWindowListener(new myWindowListener());
		add(panel, BorderLayout.NORTH);
		add(nextButton, BorderLayout.SOUTH);
	}
	
	public void start() {
		playerSystem = new PlayerSystem();
		canvas = new MyCanvas(playerSystem);
		label.setText(playerSystem.currentPlayer.getName() + "のターン");
		nextButton.setEnabled(true);
		canvas.addMouseListener(this);
		add(canvas, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		MyApplication app = new MyApplication();
		app.pack();
		app.setVisible(true);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(320,230);
	}

	public class myWindowListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (canvas.count == 1) {
			if (canvas.finishClicked(e.getX(), e.getY())) {
				label.setText(playerSystem.currentPlayer.getName() + "の勝利！");
				canvas.removeMouseListener(this);
				nextButton.setEnabled(false);
			}
		} else {
			canvas.onClicked(e.getX(), e.getY());
			label.setText(playerSystem.currentPlayer.getName() + "のターン");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nextButton) {
			playerSystem.changeTurn();
			canvas.canCatchSet(0);
			canvas.showCanColor();
			label.setText(playerSystem.currentPlayer.getName() + "のターン");
		} else {
			start();
		}
	}
}
