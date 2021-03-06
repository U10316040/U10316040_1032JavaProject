
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class PlayGame extends JPanel {

	private int[] deck = new int[52];
	private static int[][] handcard = new int[4][13];
	private String[] suitKing = {"Club ", "Diamond ","Hearts ", "Spades "};
	private JLabel[][] playerhandcard = new JLabel[4][13];
	//private JLabel background;
	private ImageIcon[] handcardImage = new ImageIcon[13];
	private boolean[] isPlay = new boolean[13];
	private Image image;
	public static Timer timer[][] = new Timer[4][13];
	private Timer PopKingWindow;
	public static Timer ComplayCard;
	public static int currentcard = -1;
	private boolean isStart;
	private boolean isAppear[] = new boolean[13];
	private boolean isFullRound;
	public static int whoRound;
	public static int Order[] = new int[4];
	public static int king;
	public static int whoWinDun, order = 0;
	
	PlayGame(){
		setLayout(null);
		setBounds(0, 0, 800, 700);
		//setOpaque(false);
		setVisible(true);
		dealcard();
		for(int player=0; player<4; player++){
			PlayerHandcard(player);
		}
	//	setBackground();
		PopKingWindow = new Timer(1000, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(isStart && isAppear[12]){
					DecideKing choose = new DecideKing(GameFrame.getFrame());
					PopKingWindow.stop();
				}
			}
		});
	
		
		
		//chooseking();
		//decideKing();
		
		ComplayCard = new Timer(1500, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(whoRound != 0){
					ComAI.Comthink(order, currentcard/13);
					order = (order + 1) % 4;
				}
				
				
			}
		});
			
		
	}
	
	
/*	private void decideKing() {
		int player = (int)( Math.random() * 3);
		if(player == 0){
			JButton[] King = new JButton[4];
			for(int i=0; i<5; i++){
				King[i] = new JButton(i == 4? "Pass" :1 + " " + suitKing[i]);
				King[i].setBounds(350,200+40*i,100,30);
				kingListener(King[i], i == 4? 16: i);
				background.add(King[i]);
			}
		}
	}*/


	private void PlayerHandcard(int p){
		image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("card/QQ.png"));
		image =  image.getScaledInstance(85, 130, Image.SCALE_DEFAULT);
		
		for(int i=0; i<13; i++){
			
			handcardImage[i] = new ImageIcon(image);
			playerhandcard[p][i] = new JLabel(handcardImage[i]);
			playerhandcard[p][i].setBounds(350,250+i,85,130);	
			dealCardAnimation(p,i);
		//	playerhandcard[p][i].setBorder(null);
			add(playerhandcard[p][i]);
		//	playerhandcard[p][i].setOpaque(true);
			PlayCard(i);
		}
		
		
	}
	
	
	private void dealCardAnimation(int player,int handCard){
		timer[player][handCard] = new Timer(1200+60*handCard, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				if(isStart){
					if(player == 0){
						image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("card/"+ (handcard[0][handCard]+1) +".png"));
						image =  image.getScaledInstance(85, 130, Image.SCALE_DEFAULT);
						playerhandcard[player][handCard].setIcon(new ImageIcon(image));
						isAppear[handCard] = true;
					}
				}
				else{
					switch(player){
					case 0:
						playerhandcard[player][handCard].setLocation(150+30*handCard,520);break;
					case 1:
						playerhandcard[player][handCard].setLocation(30,120+20*handCard);break;
					case 2:
						playerhandcard[player][handCard].setLocation(150+30*handCard,5);break;
					case 3:
						playerhandcard[player][handCard].setLocation(640,120+20*handCard);break;
					}
				}
					
				//
				if(player == 3 && handCard == 12){
					isStart = true;
					timer[player][handCard].stop();
					PopKingWindow.start();
				}
			}
		});
	}
	
	private void PlayCard(int card){
		
		playerhandcard[0][card].addMouseListener(new MouseListener(){
			public void mouseEntered(MouseEvent e){
				if(!isPlay[card] && isStart && isAppear[card]){
					timer[0][card].stop();
					playerhandcard[0][card].setLocation(150+30*card, 490);
				}
			}
			
			public void mouseExited(MouseEvent e){
				if(!isPlay[card] && isStart)
					playerhandcard[0][card].setLocation(150+30*card, 520);
			};
				
			public void mouseClicked(MouseEvent e){
				if(isStart && isAppear[12] && whoRound == 0){
					playerhandcard[0][card].setLocation(350, 300);
					isPlay[card] = true;
					
					/*if(handcard[0][card] > currentcard || handcard[0][card] % 13 == 0){
						if(currentcard / 13 != king || currentcard / 13 == king && handcard[0][card] / 13 == king){
							whoWinDun = 0;
						}
					}*/
					whoWinDun = checkWinDun(0, handcard[0][card]);
					ComAI.PlayCard[0] = handcard[0][card];
					currentcard = handcard[0][card];
					handcard[0][card] = -4;
					ComplayCard.start();
					order = (order + 1) % 4;
					whoRound = (whoRound + 1) % 4;
				}
				
				
			};
			
			public void mousePressed(MouseEvent e){};
			public void mouseReleased(MouseEvent e){};
			
		});
			
	
	}
	
	
	
	private void dealcard(){
		shuffle();

		for(int j=0; j<52; j++){
			handcard[j / 13][j % 13] = deck[j];
		}
		
		for(int i=0; i<4; i++)
			sort(handcard[i]);
		
	}
	
	private void shuffle(){
		
		for(int i=0 ;i<52; i++)
			deck[i] = i;
		
		for(int i=0; i<deck.length; i++){
			int index = (int)(Math.random() * deck.length);
			int temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}
	}
	
	private void sort(int[] number){
		for(int i=0;i<number.length-1;i++){
			for(int j=i+1;j<number.length;j++){
				if(number[i]>number[j] && (number[j] % 13!=0 || number[i] % 13==0|| number[j]/13 != number[i]/13)){
					if(number[j] % 13!=0 || number[i] % 13!=0){
						number[i] = number[i] ^ number[j];     // Use XOR change number
						number[j] = number[i] ^ number[j];     //
						number[i] = number[i] ^ number[j];    
					}										
				}
			}	
			
		}
	}
	/*private void setBackground(){
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("card/blue.png"));
		image =  image.getScaledInstance(800, 730, Image.SCALE_DEFAULT);
		background = new JLabel(new ImageIcon(image));
		background.setBounds(0, 0, 800, 730);
		add(background);
	}*/
	
/*	public void chooseking(){
	//	currentKing = 1;
		if(currentKing <= 12){
			JButton[] decideKing = new JButton[4];
			for(int i=currentKing; i<currentKing+4; i++){
				decideKing[i-currentKing] = new JButton(i > currentKing? i/4+1+""+suitKing[i % 4]:"Pass");
				kingListener(decideKing[i-currentKing],i > currentKing? i:16);
				decideKing[i-currentKing].setBounds(350,200+40*i,100,30);
				background.add(decideKing[i-currentKing]);
				
			}
			
		}
		else if(currentKing > 12 && currentKing < 16){
			JButton[] decideKing = new JButton[16-currentKing];
			for(int i = currentKing; i<15; i++){
				decideKing[i-currentKing] = new JButton(i>currentKing? i/4+1+""+suitKing[i % 4] : "Pass");
				kingListener(decideKing[i-currentKing], i>currentKing? i:16);
				decideKing[i-currentKing].setBounds(300,200,200+120*i,100);
				background.add(decideKing[i-currentKing]);
				
			}
		}
			
		
	}
	
	private void kingListener(JButton choice, int newKing){
		choice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(newKing < 16){
					currentKing = newKing;
					System.out.print(currentKing);
				}
				else{
					
				}
					
			}
		});
	}*/
	public static boolean isLack(int[] handcard, int currentCard){
		int suit = currentCard / 4;
		for(int card = 0 ; card < 13; card++){
			if(handcard[card] == suit)
				return true;
		}
		return false;
	}
	
	public static int checkWinDun(int person ,int playcard){
		int p;
		if(playcard > currentcard || playcard % 13 == 0){
			if(currentcard / 13 != king || currentcard / 13 == king && playcard / 13 == king){
				p = person;
				if(p == Order[3])
					setOrder(whoWinDun);
				
				return p;
			}
		}
		return 0;
	}
	
	public static int[] getComHandcard(int com){
		return handcard[com];
	}
	
	public static void WhoFirstPlay(int man){
		whoRound = man;
	}
	
	public static void setOrder(int whoWin){
		for(int i=0; i<4; i++){
			Order[i] = whoWin;
			whoWin = (whoWin + 1) % 4;
		}
	}
	
	public static int currentMaxCard(int order){
		int Max = -1;
		for(int i=0; i < order; i++){
			if(ComAI.PlayCard[Order[order]] % 13 > Max){
				Max = ComAI.PlayCard[Order[order]];
			}
		}
		return Max;
	}
	
	/*@Override
    protected void paintComponent(Graphics g) {
		
	}*/

}
