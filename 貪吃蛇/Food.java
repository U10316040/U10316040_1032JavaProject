import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Food {
	int row, col;
	int w = Park.GRID_SIZE;
	int h = Park.GRID_SIZE;
	private static Random r = new Random();

	public Food(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Food(){
		this(r.nextInt(Park.ROWGRIDS-3) + 3, r.nextInt(Park.COLGRIDS-3) + 3);
	}
	//重新出現食物
	public void reAppear(){
		this.row = r.nextInt(Park.ROWGRIDS-3) + 3;
		this.col = r.nextInt(Park.COLGRIDS-3) + 3;
	}
	
	public Rectangle getRect(){
		return new Rectangle(Park.GRID_SIZE * col, Park.GRID_SIZE * row , w, h);
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(Park.GRID_SIZE * col, Park.GRID_SIZE * row , w, h);
		g.setColor(c);
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

}
