package components;

import java.util.HashMap;
import java.util.Map;

import main.State;
import scenario.ComponentName;

public class Point {

	private int x;
	private int y;
	
	private boolean explored;
	
	private Map<ComponentName, Component> elements;
	
	//private boolean AllTheNeighborsWereExplored;
	
	public Point(){
		elements = new HashMap<ComponentName, Component>();
	}
	public Point(int x, int y){
		this();
		this.setX(x);
		this.setY(y);
	}
	
	public Point (int x, int y, ComponentName cName, Component c){
		this(x, y);
		this.elements.put(cName, c);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isEmpty(){
		return elements.isEmpty();
	}
	
	public Component get(ComponentName cName){
		return elements.get(cName);
	}
	
	public void put(ComponentName cName, Component c){
		elements.put(cName, c);
	}
	
	public Component remove(ComponentName cName){
		return elements.remove(cName);
	}
	public Map<ComponentName, Component> getElements() {
		// TODO Auto-generated method stub
		return elements;
	}

	/*public boolean isAllTheNeighborsWereExplored() {
		return AllTheNeighborsWereExplored;
	}
	public void setAllNeighborsExplored(boolean allTheNeighborsWereExplored) {
		AllTheNeighborsWereExplored = allTheNeighborsWereExplored;
	}*/
	
	public State getState(){
		if(elements.containsKey(ComponentName.HOLE)){
			return State.GAME_OVER_HOLE;
			
		} else if(elements.containsKey(ComponentName.WUMPUS)){
			return State.GAME_OVER_WUMPUS;
		} else if(elements.containsKey(ComponentName.GOLD)) {
			return State.VICTORY;
		} else {
			return State.CONTINUE;
		}
	}
	public boolean isExplored() {
		return explored;
	}
	public void setExplored(boolean explored) {
		this.explored = explored;
	}
	
	@Override
	public int hashCode(){
		return getX() + getY() + super.hashCode(); 
	}
}