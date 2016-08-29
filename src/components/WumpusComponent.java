package components;

import scenario.ComponentName;

public abstract class WumpusComponent {
	private int x;
	private int y;
	private ComponentName name;
	
	public WumpusComponent(int[] coord, ComponentName name){
		this.x = coord[0];
		this.y = coord[1];
		this.name = name;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getX(){
		return this.x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getY(){
		return this.y;
	}
}
