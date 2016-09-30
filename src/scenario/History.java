package scenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import components.Point;

public class History {

	private Stack<Point> positions;
	
	public History(){
		positions = new Stack<Point>();
	}
	
	/*public List<Point> getAllPointsWithUnexploitedNeighbors(){
		List<Point> result = new ArrayList<Point>();
		for(Point p : positions){
			if(!p.isAllTheNeighborsWereExplored()){
				result.add(p);
			}
		}
		return result;
	}*/

	public void savePosition(Point points) {
		positions.push(points);
	};	
}
