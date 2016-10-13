package components;

import scenario.ComponentName;

public abstract class Component {
	private Point coordinates;
	private ComponentName name;
	private double probabilityOfDanger;
		
	public Component(Point coord, ComponentName name){
		this.coordinates = coord;
		this.setName(name);
	}
	
	public Component(){}
	
	public void setX(int x){
		this.coordinates.setX(x);
	}
	
	public int getX(){
		return this.coordinates.getX();
	}
	
	public void setY(int y){
		this.coordinates.setY(y);
	}
	
	public int getY(){
		return this.coordinates.getY();
	}
	
	public Point getCoordinates(){
		return this.coordinates;
	}
	
	public void setCoordinates(Point coord){
		this.coordinates = coord;
	}
	
	public abstract char getSymbol();

	public ComponentName getName() {
		return name;
	}

	public void setName(ComponentName name) {
		this.name = name;
	}

}