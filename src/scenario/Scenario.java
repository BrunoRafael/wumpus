package scenario;

import java.util.List;

import components.Component;
import components.Gold;

public class Scenario {
	private Component[][] board;
	private int width, height;
	
	public List<Integer> goldPosition;
	
	public Scenario(int width, int height){
		this.setWidth(width);
		this.setHeight(height);
		this.board = new Component[width][height];
	}
	
	public Scenario(){
		this(16, 16);
	}
	
	public void addComponent(Component component){
		if(component instanceof Gold){
			this.goldPosition = component.getCoordinates();
		}
		this.board[component.getX()][component.getY()] = component;
	}
	
	public boolean isOccupied(List<Integer> coord){
		return this.board[coord.get(0)][coord.get(1)] != null;
	}
	
	public void moveComponent(Component component, int[] newCoord){
		this.board[component.getX()][component.getY()] = null;
		component.setX(newCoord[0]);
		component.setY(newCoord[1]);
		this.board[newCoord[0]][newCoord[1]] = component;
	}
	
	public List<Integer> getGoldPosition(){
		return goldPosition;
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
	
	public Component[][] getBoard(){
		return this.board;
	}
}