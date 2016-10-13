package heuristics;

import java.util.List;

import components.Point;

public abstract class Heuristic {

	private int priority;

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	abstract public Point evaluatePositions(List<Point> points, Point actualPosition);
	
}
