package components;

import java.util.LinkedList;
import java.util.List;

import scenario.ComponentName;

public abstract class Component {
	private List<Integer> coordinates;
	private ComponentName name;
		
	public Component(List<Integer> coord, ComponentName name){
		this.coordinates = coord;
		this.setName(name);
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

	public ComponentName getName() {
		return name;
	}

	public void setName(ComponentName name) {
		this.name = name;
	}
	
}