import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameFrame extends JFrame {
	
	private static ReadyPlay readyPlay = new ReadyPlay();
	public static PlayGame playGame = new PlayGame();
	private static GameFrame frame;
	
	GameFrame(){
		setBounds(140,0,800,730);
		setTitle("Bridge game");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(readyPlay);
		//setBackground();
	}
	
	public static void main(String[] args) {
		frame = new GameFrame();
		frame.setVisible(true);
	}
	
	public static GameFrame getFrame(){
		return frame;
	}
	
	

}
