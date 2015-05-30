
public class ComAI {
	private static int[] NumOfSuits = new int[4];
	
	public static int ComDeKing(int comhandcard[], int currentKing){
		int point = 0;
		for(int i=0 ;i<13; i++){
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
		}
		
		
		
		
		
		
		
		return currentKing;
	}
}
