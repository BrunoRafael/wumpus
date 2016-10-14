package scenario;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import components.Component;
import components.Gold;
import components.Hunter;
import components.Movimentation;
import components.Point;
import main.State;

public class Scenario {
	private List<List<Point>> board;
	private int width, height;
	
	private Point hunterPosition;
	private Point goldPosition;
	
	private HistoryBase history;
		
	public Scenario(int width, int height){
		try {
			this.setWidth(width);
			this.setHeight(height);
			history = new HistoryBase();
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				this.saveAvailablePointsToNextPosition(this.hunterPosition);
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
		if(componentName != ComponentName.HUNTER){
			if(!isAvailable(componentName, newCoord)){
				throw new Exception("Position unavailable");
			}
		}
		
		Component c = this.board.get(
				actualCoord.getX()).get(actualCoord.getY()).remove(componentName);
		
		Point nexPos = this.board.get(newCoord.getX()).get(newCoord.getY());
		nexPos.put(componentName, c);
		nexPos.setExplored(true);
		
		if(componentName == ComponentName.HUNTER){
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
	
	public List<Point> getAvailableNeighbors(Point point, List<Point> neighborsActual, boolean insertExplored) {
		List<Point> points = getAvailableNeighbors(point);
		List<Point> newPointList = new ArrayList<Point>();
		for(Point p : points){
			insertPoint(p, insertExplored, newPointList);
		}
		if(neighborsActual != null){
			newPointList.removeAll(neighborsActual);
		}
		return newPointList;
	}
		
	public List<Point> getAvailableNeighbors(Point p) {
		
		List<Point> points = new LinkedList<Point>();
		Point point;
		if(p.getX() + 1 < width ){
			point = board.get(p.getX() + 1).get(p.getY());
			points.add(point);
			
		}
		
		if(p.getY() + 1 < height){
			point = board.get(p.getX()).get(p.getY() + 1);
			points.add(point);
		}
		
		if(p.getX() - 1 >= 0){
			point = board.get(p.getX() - 1).get(p.getY());
			points.add(point);
		}
		
		if(p.getY() - 1 >= 0){
			point = board.get(p.getX()).get(p.getY() - 1);
			points.add(point);
		}
		
		return points;
	}
	
	private void insertPoint(Point p, boolean insertExplored, List<Point> points) {
		if(insertExplored){
			if(insertExplored){
				if(p.isExplored() && !p.getThreats().isEmpty()){
					points.add(p);
				}
			} else {
				points.add(p);
			}
		} else {
			if(!p.isExplored()){
				points.add(p);
			}
		}
		
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

	public void saveAvailablePointsToNextPosition(Point pos) {
		this.history.removeExploredPointInNextPositions(pos);
		List<Point> neighbors = getAvailableNeighbors(pos, null, false);
		history.saveAvailablePointsToNextPosition(neighbors);
	}

	public List<Point> getAvailablePointsToNextPosition() {
		return this.history.getAvailablePointsToNextPosition();
	}
	
	public void saveMovimentation(Movimentation mov) throws FileNotFoundException, IOException{
		history.saveMoviment(mov);
	}

}