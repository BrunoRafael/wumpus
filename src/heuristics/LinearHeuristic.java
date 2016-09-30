package heuristics;

import java.util.List;

import components.Point;

public class LinearHeuristic extends Heuristic {

	private List<Point> pointsToGold;
	
	@Override
	public Point evaluatePositions(List<Point> points) {
		Point selectedPosition = points.get(0);
		double minDistance = calculateMinDistanceToLine(selectedPosition);
		for(int i = 1; i < points.size(); i++){
			double distance = calculateMinDistanceToLine(points.get(i));
			if(distance < minDistance){
				minDistance = distance;
				selectedPosition = points.get(i);
			}
		}
		return selectedPosition;
	}

	private double calculateMinDistanceToLine(Point p) {
		Point comparator = pointsToGold.get(0);
		double distanceToLine = Math.pow(comparator.getX() - p.getX(), 2) + 
				Math.pow(comparator.getY() - p.getY(), 2);
		double minDistance = Math.sqrt(distanceToLine);
		for(int i = 1; i < pointsToGold.size() ; i++){
			
			comparator = pointsToGold.get(i);
			distanceToLine = Math.pow(comparator.getX() - p.getX(), 2) + 
					Math.pow(comparator.getY() - p.getY(), 2);
			double square = Math.sqrt(distanceToLine);
			
			if(square < minDistance){
				minDistance = square;
			}
			
		}
		return minDistance;
	}

	public List<Point> getPointsToGold() {
		return pointsToGold;
	}

	public void setPointsToGold(List<Point> pointsToGold) {
		this.pointsToGold = pointsToGold;
	}

}
