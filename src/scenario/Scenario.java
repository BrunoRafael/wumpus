package scenario;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import components.Component;
import components.Danger;
import components.Gold;
import components.Hunter;

public class Scenario {
	private List<List<Map<ComponentName, Component>>> board;
	private int width, height;
	
	private List<Integer> hunterPosition;
	private List<Integer> goldPosition;
		
	public Scenario(int width, int height){
		this.setWidth(width);
		this.setHeight(height);
		List<List<Map<ComponentName, Component>>> matrix = new LinkedList<List<Map<ComponentName, Component>>>();
		for(int i = 0; i < width; i++){
			List<Map<ComponentName, Component>> line = new LinkedList<Map<ComponentName, Component>>();
			for(int j = 0; j < height; j++){
				Map<ComponentName, Component> item = new HashMap<ComponentName, Component>();
				line.add(item);
			}
			matrix.add(line);
		}
		
		this.board = matrix;
	}
	
	public Scenario(){
		this(16, 16);
	}
	
	public void addComponent(Component component) throws Exception{
		if(isAvailable(component.getName(), component.getCoordinates())){
			if(component instanceof Gold){
				this.goldPosition = component.getCoordinates();
			} else if(component instanceof Hunter) {
				this.hunterPosition = component.getCoordinates();
			}
			
			this.board.get(component.getX()).get(component.getY()).put(component.getName(), component);			
		} else {
			
		}
	}
	
	public boolean isAvailable(ComponentName added, List<Integer> coord){

		Map<ComponentName, Component> map = this.board.get(coord.get(0)).get(coord.get(1));
		if(map.isEmpty()){
			return true;
		}
		
		if(ComponentName.isComponentNotRepeated(added)){
			return false;
		}

		for(ComponentName cName : ComponentName.getComponentsNotRepeated()){
			if(map.get(cName) != null){
				return false;
			}
		}
		
		return true;
		
	}
	
	public void moveComponent(Component component, int[] newCoord){
		Map<ComponentName, Component> oldPosition = this.board.get(component.getX()).get(component.getY());
		oldPosition.remove(component.getName());
		
		Map<ComponentName, Component> newPosition = this.board.get(newCoord[0]).get(newCoord[1]);
		newPosition.put(component.getName(), component);
		
		this.hunterPosition = component.getCoordinates();
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
	
	public List<List<Map<ComponentName, Component>>> getBoard(){
		return this.board;
	}
	
	public List<Integer> getHunterPosition(){
		return this.hunterPosition;
	}
}