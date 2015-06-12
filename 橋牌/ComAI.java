import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class ComAI {
	public static int[][] ColorNum = new int[4][4];
	public static int[] ColorPoint = new int[4];
	//ArrayList<Integer[]> SuitCards = new ArrayList<Integer[]>();
	public static int kingColor;
	public static int PlayCard[] = new int[4];
	private static int[] playcard;
	
	public static int ComDeKing(int com, int comhandcard[], int currentKing){
		int point = 0;
		for(int i=0; i<13; i++){
			ColorNum[com][comhandcard[i]/13]++;
			switch(comhandcard[i] % 13){
			case 0:
				ColorPoint[comhandcard[i]/13]+= 4;break;
			case 10:
				ColorPoint[comhandcard[i]/13]+= 1;break;
			case 11:
				ColorPoint[comhandcard[i]/13]+= 2;break;
			case 12:
				ColorPoint[comhandcard[i]/13]+= 3;break;
			}
		}
		
		if(MaxColor(ColorNum[com]) <=4){
			if(MaxColor(ColorPoint) <=4){
				DecideKing.isPass[com] = true;
			//	System.out.println("kingColor: "+ kingColor);
				return currentKing;//pass
			}
			else if(MaxColor(ColorPoint) >4 && MaxColor(ColorPoint)<=10){
				if(currentKing < 8){
					if(kingColor == currentKing % 4){
						DecideKing.isPass[com] = true;
					}
					currentKing += (kingColor != currentKing % 4?(kingColor < currentKing % 4? 4-(currentKing % 4-kingColor) : kingColor - currentKing % 4):0);
					
					System.out.println("kingColor: "+ kingColor);
					return currentKing;
				}
				else{
					DecideKing.isPass[com] = true;
				//	System.out.println("kingColor: "+ kingColor);
					return currentKing;//pass
				}
				
					
			}
		}
		else if(MaxColor(ColorNum[com]) > 4 && MaxColor(ColorNum[com])<=7){
			if(MaxColor(ColorPoint) <=2){
				DecideKing.isPass[com] = true;
			//	System.out.println("kingColor: "+ kingColor);
				return currentKing;//pass
			}
			else if(MaxColor(ColorPoint) >2 && MaxColor(ColorPoint) <=6 ){
				if(currentKing < 8){
					if(kingColor == currentKing % 4){
						DecideKing.isPass[com] = true;
					}
					currentKing += (kingColor != currentKing % 4?(kingColor < currentKing % 4? 4-(currentKing % 4-kingColor) : kingColor - currentKing % 4):0);
					
					System.out.println("kingColor: "+ kingColor);
					return currentKing;
				}
				else{
					DecideKing.isPass[com] = true;
					return currentKing;//pass
				}
			}
			else{
				if(kingColor == currentKing % 4){
					DecideKing.isPass[com] = true;
				}
				if(currentKing < 8)
					currentKing += (kingColor != currentKing % 4?(kingColor < currentKing % 4? 4-(currentKing % 4-kingColor) : kingColor - currentKing % 4):0);
				else
					DecideKing.isPass[com] = true;
				System.out.println("kingColor: "+ kingColor);
				return currentKing;
			}
		}
		else {
			if(kingColor == currentKing % 4){
				DecideKing.isPass[com] = true;
			}
			
			if(currentKing < 8)
				currentKing += (kingColor != currentKing % 4?(kingColor < currentKing % 4? 4-(currentKing % 4-kingColor) : kingColor - currentKing % 4):0);
			else
				DecideKing.isPass[com] = true;
			return currentKing;
		}
		
		
			
		/*for(int i=0; i<4; i++){
			point += ColorPoint[i];
			
			
		}*/
		
/*		for(int i=0 ;i<13; i++){
			NumOfSuits[comhandcard[i]/4] ++;
			
			if(comhandcard[i]%13 > 10){
				point += (comhandcard[i]%13-3);
			}
			if(comhandcard[i]%13 == 10)
				point += 4;
		}
		
		for(int i =0; i<4; i++){
			if(NumOfSuits[i]>4)
				break;
			
			if(point >=15 && point <=18){
				currentKing = 16;
				return currentKing;
			}
		}*/
		
		
		
		return currentKing;
	}
	
	//public static void ComPlayCard(int order, int comhandcard[]){
		
		
	//}
	
	public static void Comthink(int order, int suit){
		int card;
		int com = PlayGame.Order[order];
	/*	int playcard[] = new int[ColorNum[com][suit]];
		for(card=0; card<13; card++){
			if(PlayGame.getComHandcard(PlayGame.Order[order])[card]/13 == suit){
				for(int i=card; i < card + ColorNum[PlayGame.Order[order]][suit]; i++){
					playcard[i] = PlayGame.getComHandcard(com)[i];
				}	
				break;
			}
				
		}
		
		switch(suit){
		case 0:
			PlayMethod(com,SuitableCard(PlayGame.getComHandcard(com),order), kingColor );
		case 1:
		case 2:
		case 3:
		}*/
		if(PlayGame.currentcard == -1){
			PlayMethod(com,SuitableCard(PlayGame.getComHandcard(com),order), kingColor );
			return;
		}
		
		if(!PlayGame.isLack(PlayGame.getComHandcard(com), PlayGame.currentcard)){
			if(ColorNum[com][suit] != 0)
				playcard = new int[ColorNum[com][suit]];
			for(card = 0; card < 13; card++){
				//int playcard = (PlayGame.getComHandcard(PlayGame.Order[order]))[card];
				if( PlayGame.getComHandcard(com)[card]/13 == suit){
					for(int i=card; i < card + ColorNum[PlayGame.Order[order]][suit]; i++){
						playcard[i] = PlayGame.getComHandcard(com)[i];
					}		
					break;
				}	
			}
			
			if(order == 0){
				if(playcard[ColorNum[com][suit]-1] == 0){
					PlayMethod(com,playcard[ColorNum[com][suit]-1], card + ColorNum[com][suit]-1);
				}
				else{
					PlayMethod(com,MaxColor(playcard), card + kingColor -1);
				}		
			}
			else if(order == 2 || order == 3){
				if(playcard[ColorNum[com][suit]-1] == 0){
					PlayMethod(com,playcard[ColorNum[com][suit]-1], card + ColorNum[com][suit]-1);
				}
				else{
					PlayMethod(com,SuitableCard(playcard,order), card + kingColor -1);
				}
			}
			else {
				PlayMethod(com,SuitableCard(playcard,order), card + kingColor -1);
			}
		}
		else{
			
			PlayMethod(com,SuitableCard(PlayGame.getComHandcard(com),order), kingColor);
		}
	}
	
	private static void PlayMethod(int com,int card,int position){ 
		PlayCard[com] = card;
		PlayGame.checkWinDun(com, card);
		PlayGame.currentcard = card;
		PlayGame.whoRound = (PlayGame.whoRound + 1) % 4;
		
		Image image = Toolkit.getDefaultToolkit().getImage(ComAI.class.getResource("card/"+ card +".png"));
		image = image.getScaledInstance(85, 130, Image.SCALE_DEFAULT);
		JLabel Card = new JLabel(new ImageIcon(image));
		Card.setBounds(350,200,85,130);	
		GameFrame.getplayGame().add(Card);
		GameFrame.getplayGame().repaint();
		PlayGame.getComHandcard(com)[position] = -4;
	}
	
	private static int MaxColor(int... numbers){
		int MaxValue = -2;
		int color = 0;
		for (color = 0 ; color < numbers.length ; color++) {
			  if (numbers[color] > MaxValue) {
				  MaxValue = numbers[color];
				  kingColor = color;
			  }
		}
		
		return MaxValue;
	}
	
	private static int SuitableCard(int[] numbers, int order){
		int Value = -2;
		int card = 0;
		for (card = 0 ; card < numbers.length ; card++) {
			  if (numbers[card] > PlayGame.currentMaxCard(order)) {
				  Value = numbers[card];
				  kingColor = card;
				  return Value;
			  }
		}
		
		return Value;
	}
}
