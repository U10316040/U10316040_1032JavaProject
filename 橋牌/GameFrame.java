import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


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
		checkBegin();
	//	setBackground();
		
	}
	
	public static void main(String[] args) {
		frame = new GameFrame();
		frame.setVisible(true);
	}
	
	public static GameFrame getFrame(){
		return frame;
	}
	
	public static PlayGame getplayGame(){
		return playGame;
	}
	
	private void checkBegin(){
		Timer timer = new Timer(1000,new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(ReadyPlay.isBegin()){
					remove(readyPlay);
				}		
			}
		});
		timer.start();
	}
	
	private void setBackground(){
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("card/blue.png"));
		image =  image.getScaledInstance(800, 730, Image.SCALE_DEFAULT);
		JLabel background = new JLabel(new ImageIcon(image));
		background.setBounds(0, 0, 800, 730);
		add(background);
	}
	
}
