package scenario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import components.Movimentation;
import components.Point;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class HistoryBase {

	private Set<Point> availablePointsToNextPosition;
	
	private FastVector attributes;
	private Instances dataSet;
	private double[] values;
	
	private File f;
	
	public static String FILE_PATH = File.separator + "data" + 
									File.separator + "snapshots.arff";
	
	public HistoryBase() throws IOException{
		f = new File(".");
		availablePointsToNextPosition = new TreeSet<Point>();
		attributes = new FastVector();
		
		// Localization - string
	    attributes.addElement(new Attribute("localization", (FastVector) null));
	    // Perception - numeric 
	    attributes.addElement(new Attribute("perception"));
	    // Movimentation - string
	    attributes.addElement(new Attribute("movimentation", (FastVector) null));
	    // Exit - numeric 
	    attributes.addElement(new Attribute("exit"));
	    
	    // create Instances object
	    dataSet = new Instances("Moviments", attributes, 0);
	    
	    BufferedWriter writer = new BufferedWriter(new FileWriter(f.getCanonicalPath() + FILE_PATH));
		writer.write(dataSet.toString());
		writer.flush();
		writer.close();
	}
	
	public void saveMoviment(Movimentation mov) throws FileNotFoundException, IOException{
		
		Instances data = new Instances(new BufferedReader(
				new FileReader(f.getCanonicalPath() + FILE_PATH )));
		
		values = new double[dataSet.numAttributes()];
		values[0] = data.attribute(0).addStringValue(mov.getLocalization());
		values[1] = mov.getPerception();
		values[2] = data.attribute(2).addStringValue(mov.getMovimentation());
		values[3] = mov.getExit();
		data.add(new Instance(1.0, values));
		
		writeFile(data);
		
	}
	
	public boolean saveAvailablePointsToNextPosition(List<Point> points){
		return this.availablePointsToNextPosition.addAll(points);
	}

	public boolean removeExploredPointInNextPositions(Point pos) {
		return this.availablePointsToNextPosition.remove(pos);
	}

	public List<Point> getAvailablePointsToNextPosition() {
		return new LinkedList<Point>(availablePointsToNextPosition);
	}
	
	private void writeFile(Instances data) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(
	    		f.getCanonicalPath() + FILE_PATH ));
	    writer.write(data.toString());
	    writer.flush();
	    writer.close();
	}
}
