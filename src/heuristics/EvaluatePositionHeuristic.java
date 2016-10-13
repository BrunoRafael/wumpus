package heuristics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Comparator;

import components.Component;
import components.Point;
import scenario.ComponentName;
import scenario.ScenarioManager;

public class EvaluatePositionHeuristic extends Heuristic{
	
	private ScenarioManager sm;

	@Override
	public Point evaluatePositions(List<Point> points, Point actualPos) {
		this.sm = ScenarioManager.getInstance();
		//save points in history
		calculateFitness(points, actualPos);
		List<Point> resultPoints = checkConvergence(new LinkedList<Point>(points));
		if(resultPoints.isEmpty()){
			return null;
		} else if(resultPoints.size() > 1){
			Random r = new Random();
			return resultPoints.get(r.nextInt(resultPoints.size()));
		}
		
		return resultPoints.get(0);
		
	}
	
	private List<Point> checkConvergence(List<Point> points) {
		List<Point> result = new LinkedList<Point>();
		Collections.sort(points, new Comparator<Point>() {
	        public int compare(Point m1, Point m2){
	        	if (m1 == m2) return 0;
                if (m1 == null) return -1;
                if (m2 == null) return 1;
                if (m1.equals(m2)) return 0;
                
	        	List<Double> ft1 = new LinkedList<Double>(m1.getFitness());
	    		List<Double> ft2 = new LinkedList<Double>(m2.getFitness());
	    		Collections.sort(ft1, getValueComparator());
	    		Collections.sort(ft2, getValueComparator());
	    		
	    		int value = Double.compare(ft1.get(0), ft2.get(0));
                if (value != 0) return value;
                return m1.hashCode() - m2.hashCode();
	    		
	        }
	    });
		
		result.add(points.remove(0));
		
		for(Point p : points){
			if(p.equalsFit(result.get(0))){
				result.add(p);
			}
		}
		return result;
	}
	
	private Comparator<Double> getValueComparator(){
		return new Comparator<Double>() {
	        public int compare(Double v1, Double v2){
	        	if(v1 >= v2){
	        		return -1;
	        	} else {
	        		return 1;
	        	}
	        }
		};
	}

	private void calculateFitness(List<Point> points, Point actualPos){
		for(Point p : points){
			List<Double> incrementFit = evaluateNebulousConditions(p, actualPos);
			if(p.getFitness() != null && !p.getFitness().isEmpty()){
				for(int i = 0; i < incrementFit.size(); i++){
					incrementFit.set(i, p.getFitness().get(i) + incrementFit.get(i));
				}
			}
			p.setFitness(incrementFit);
		}
	}

	private List<Double> evaluateNebulousConditions(Point evaluatePos, Point actualPos) {
		List<Point> points = sm.getAllNeighbors(evaluatePos, true);
		List<Double> fitness = new ArrayList<Double>();
		fitness.add(0.0);
		fitness.add(0.0);
		fitness.add(0.0);
		for(Point p : points){
			//Analisa só os não explorados
			if(p.isExplored()){
				if(p.getThreats().size() > 0){
					for(Component c : p.getThreats()){
						if(c.getName().equals(ComponentName.BED_SMELL)){
							fitness.set(0, fitness.get(0) + 0.2); //é menor pois tem a possibilidade de atirar uma flecha
						} else if(c.getName().equals(ComponentName.BREEZE)) {
							fitness.set(1, fitness.get(1) + 0.25);
						}
					}
				}
			}
		}

		double distance = this.sm.calculateDistance(actualPos, evaluatePos);
		double distanceFit = (0.08 * distance)/3;
		fitness.set(2, distanceFit);

		return fitness;
	}
}