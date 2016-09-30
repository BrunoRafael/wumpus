package scenario;

import java.util.LinkedList;
import java.util.List;

import components.Component;
import components.Gold;
import components.Hunter;
import components.Point;
import main.State;

public class Scenario {
	private List<List<Point>> board;
	private int width, height;
	
	private Point hunterPosition;
	private Point goldPosition;
	
	private History history;
		
	public Scenario(int width, int height){
		this.setWidth(width);
		this.setHeight(height);
		history = new History();
		List<List<Point>> matrix = new LinkedList<List<Point>>();
		for(int i = 0; i < width; i++){
			List<Point> line = new LinkedList<Point>();
			for(int j = 0; j < height; j++){
				Point item = new Point(i, j);
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
	
	public boolean isAvailable(ComponentName added, Point coord){
		
		if(added == ComponentName.HOLE || added == ComponentName.WUMPUS){
			if(coord.getX() == hunterPosition.getX() + 1 && coord.getY() == hunterPosition.getY() ||
				coord.getY() == hunterPosition.getY() + 1 && coord.getX() == hunterPosition.getX() ||
				coord.getX() == hunterPosition.getX() + 1 && coord.getY() == hunterPosition.getY() + 1){
				return false;
			}
		}

		Point point = this.board.get(coord.getX()).get(coord.getY());
		if(point.isEmpty()){
			return true;
		}
		
		if(ComponentName.isComponentNotRepeated(added)){
			return false;
		}
		
		if(point.get(added) != null){
			return false;
		}

		for(ComponentName cName : ComponentName.getComponentsNotRepeated()){
			if(point.get(cName) != null){
				return false;
			}
		}
		
		return true;
		
	}
	
	public State moveComponent(ComponentName componentName, Point actualCoord, Point newCoord) 
			throws Exception{
		if(!isAvailable(componentName, actualCoord) || !isAvailable(componentName, newCoord)){
			throw new Exception("Position unavailable");
		}
						
		Component c = this.board.get(
				actualCoord.getX()).get(actualCoord.getY()).remove(componentName);
		
		Point nexPos = this.board.get(newCoord.getX()).get(newCoord.getY());
		nexPos.put(componentName, c);
		nexPos.setExplored(true);
		
		if(componentName == ComponentName.HUNTER){
			history.savePosition(hunterPosition);
			this.hunterPosition = newCoord;
		}
		
		return nexPos.getState();
	}

	public Point getGoldPosition(){
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
		
	public Point getHunterPosition(){
		return this.hunterPosition;
	}
	
	public List<Point> getAvailablePositionsToNext() {
		return getAvailablePositionsToNext(this.hunterPosition);
	}
	
	public List<Point> getAvailablePositionsToNext(Point p) {
		
		List<Point> points = new LinkedList<Point>();
		if(p.getX() + 1 < width ){
			points.add(board.get(p.getX() + 1).get(p.getY()));
		}
		
		if(p.getY() + 1 < height){
			points.add(board.get(p.getX()).get(p.getY() + 1));
		}
		
		if(p.getX() - 1 >= 0){
			points.add(board.get(p.getX() - 1).get(p.getY()));
		}
		
		if(p.getY() - 1 >= 0){
			points.add(board.get(p.getX()).get(p.getY() - 1));			
		}
		
		return points;
	}
	
	@Override
	public String toString(){
		String s = "";
		for(List<Point> line : this.board){
			for(Point p : line){
				if(p.isEmpty()){
					s += " * ";
				} else {
					for(ComponentName component : p.getElements().keySet()){
						if(component == null){
							s += " * ";
						} else {
							if(p.getElements().size() == 1){
								s += component.toString();
							} else {
								s += component.getSymbol();
							}
						}
					}
				}
			}
			s += "\n";
		}
		return s;
	}
}