

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;


public class DecideKing extends JDialog {
	private JLabel king;
	private JTextArea message;
	private String[] suitKing = {"梅花  ", "方塊  ","愛心 ", "黑桃  "};
	public static boolean isPass[] = new boolean[4];
	public static int currentKing = -1;
	private boolean isChoose = false;
	private Timer[] ComDeKing = new Timer[3];
	private static Timer wait;
	private static int whoRound = 0; 
	private int whoWin;
	public static int DunNum[] = new int[2];
	private JPanel panel = new JPanel();
	JButton[] King = new JButton[4];
	
	public DecideKing(JFrame frame){
		super(frame, "叫牌", true);
		setBounds(350,100,500,400);
		//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	/*	for(int i=0; i<13; i++){
			System.out.println(PlayGame.getComHandcard(3)[i]);
		}*/
		panel.setBounds(0, 0, 500, 400);
		panel.setLayout(null);
		add(panel);
		king = new JLabel("123");
		king.setBounds(340,10,200,100);
		panel.add(king);
	
		message = new JTextArea();
		message.setBounds(10,10,300,200);
		panel.add(message);

		for(int com = 1;com <=3; com++){
			comDeking(com);
		}
		
		wait = new Timer(700,new ActionListener(){
			public void actionPerformed(ActionEvent e){

				if(isThreePass(isPass)){
					for(int i=0; i<4; i++)
						PlayGame.Order[i] = (whoRound + i) % 4;
					
					whoWin = whoRound;
					PlayGame.WhoFirstPlay((whoRound + 1) % 4);
					PlayGame.king = currentKing % 4;
					int level = currentKing/4+1;
					JOptionPane.showMessageDialog(null, "王: " + level + " " + suitKing[currentKing % 4] + 
												"\n勝利條件:玩家  電腦2  " + (DunNum[0] = whoWin == 0 || whoWin == 3? (7 + level - 1) : (7 - level + 1)) + "敦" +
												"\n                 電腦1  電腦3 " + (DunNum[1] = whoWin == 1 || whoWin == 4? (7 + level - 1) : (7 - level + 1)) + 	"敦"	);
					dispose();
					PlayGame.ComplayCard.start();
					wait.stop();
				}
					
				if(isChoose){
					whoRound = (whoRound + 1) % 4;
					king.setText("目前的王:" + (currentKing/4+1) + suitKing[currentKing % 4]);
					isChoose = false;
					if(whoRound != 0){
						if(!isPass[whoRound]){
							wait.stop();
							ComDeKing[whoRound-1].start();
						}
						else{
							isChoose = true;
						}
					}
					else{
						if(!isPass[whoRound]){
							removeButton();
							panel.repaint();
							decideKing();
						}
						else{
							isChoose = true;
						}
					}
						
				}
			}
		});
		wait.start();
		
		decideKing();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	private void decideKing(){
		wait.stop();	
		for(int i=0; i<4 && !isPass[0] ; i++){
			King[i] = new JButton(currentKing > 0 && i == 3? "Pass" : (currentKing+i+1) /4 + 1 + " " + suitKing[(currentKing + i + 1)%4]);
			King[i].setBounds(350,200+40*i,100,30);
			King[i].setFont(new Font("Microsoft YaHei",Font.BOLD,17));
			kingListener(King[i], currentKing > 0 && i == 3? currentKing : currentKing + i + 1);
			panel.add(King[i]);
		}
			
		
	}
	
	private void kingListener(JButton choice, int newKing){
		choice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				removeButton();
				if(currentKing != newKing){
					currentKing = newKing;
					isChoose = true;
					//whoRound = (whoRound + 1) % 4;
					king.setText("目前的王:" + (currentKing/4+1) + suitKing[currentKing % 4]);
			//		System.out.print(currentKing);
					wait.start();
					//decideKing();
				}
				else{
					isPass[0] = true;
					JLabel pass = new JLabel("Pass");
					pass.setFont(new Font("Microsoft YaHei",Font.BOLD,25));
					pass.setBounds(340,200,200,100);
					panel.add(pass);
					isChoose = true;
					removeButton();
					repaint();
					wait.start();
				}
					
			}
				
		});
	}
	
	private boolean isThreePass(boolean[] isDe){
		int count = 0;
		for(int i =0;i<4; i++){
			if(isDe[i])
				count++;
		}
		return (count == 3);
	}
	private void comDeking(int com){
		ComDeKing[com-1] = new Timer(1500,new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentKing = ComAI.ComDeKing(com, PlayGame.getComHandcard(com), currentKing);
				//whoRound = (whoRound + 1) % 4;
			//	System.out.print(currentKing);
			//	System.out.println("\ncom " + com + " ");
				isChoose = true;	
			//	System.out.println("Pass:" + isPass[com]);
			//	System.out.println("com " + com+ " King:"+ComAI.kingColor);
				message.setFont(new Font("Microsoft YaHei",Font.BOLD,30));
				message.setText("\n\n" + (isPass[com] ? "電腦" + com +" Pass" : "電腦" + com + "  " + (currentKing/4+1) + suitKing[currentKing % 4]));
				ComDeKing[com-1].stop();
				wait.start();
			}
		});
	}
	
	private void removeButton(){
		for(int i=0; i<King.length; i++){
			panel.remove(King[i]);
		}
	}
	
	private class KingPanel{
		
	}
	
	
}
