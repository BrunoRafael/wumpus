package components;

import java.util.LinkedList;
import java.util.List;

public abstract class Component {
	private List<Integer> coordinates;
		
	public Component(List<Integer> coord){
		this.coordinates = coord;
	}
	
	public Component(int x, int y){
		this.coordinates = new LinkedList<Integer>();
		this.coordinates.add(x);
		this.coordinates.add(y);
	}
	
	public Component(){}
	
	public void setX(int x){
		this.coordinates.set(0, x);
	}
	
	public int getX(){
		return this.coordinates.get(0);
	}
	
	public void setY(int y){
		this.coordinates.set(1, y);
	}
	
	public int getY(){
		return this.coordinates.get(1);
	}
	
	public List<Integer> getCoordinates(){
		return this.coordinates;
	}
	
	public abstract char getSymbol();
	
}