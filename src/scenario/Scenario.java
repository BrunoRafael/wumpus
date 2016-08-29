package scenario;

import components.WumpusComponent;

public class Scenario {
	private WumpusComponent[][] board;
	private int width, height;
	
	public Scenario(int width, int height){
		this.setWidth(width);
		this.setHeight(height);
		this.board = new WumpusComponent[width][height];
	}
	
	public Scenario(){
		this(16, 16);
	}
	
	public void addComponent(WumpusComponent component){
		this.board[component.getX()][component.getY()] = component;
	}
	
	public boolean isOccupied(int[] coord){
		return this.board[coord[0]][coord[1]] != null;
	}
	
	public void moveComponent(WumpusComponent component, int[] newCoord){
		this.board[component.getX()][component.getY()] = null;
		component.setX(newCoord[0]);
		component.setY(newCoord[1]);
		this.board[newCoord[0]][newCoord[1]] = component;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}