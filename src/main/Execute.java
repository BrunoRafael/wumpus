package main;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import components.Point;

public class Execute {
	public static void main(String[] args) throws ParseException {
		List<Point> points = new LinkedList<Point>();
		Point p1 = new Point(2, 5);
		Point p2 = new Point(2, 4);
		Point p3 = new Point(1, 5);
		Point p4 = new Point(2, 6);
		
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		for(Point p : points){
			System.out.print(p.getX() + "," + p.getY());
			System.out.println();
		}
		System.out.println("----------------------------------------");
		List<Point> removePoints = new LinkedList<Point>();
		removePoints.add(p1);
		removePoints.add(p2);
		Point p5 = new Point(8, 4);
		Point p6 = new Point(10, 5);
		Point p7 = new Point(12, 6);
		removePoints.add(p5);
		removePoints.add(p6);
		removePoints.add(p7);
		
		System.out.println(points.containsAll(removePoints));
		points.removeAll(removePoints);
		
		for(Point p : points){
			System.out.print(p.getX() + "," + p.getY());
			System.out.println();
		}
		/*FastVector      atts;
	    FastVector      attsRel;
	    FastVector      attVals;
	    FastVector      attValsRel;
	    Instances       data;
	    Instances       dataRel;
	    double[]        vals;
	    double[]        valsRel;
	    int             i;

	    // 1. set up attributes
	    atts = new FastVector();
	    // - numeric
	    atts.addElement(new Attribute("att1"));
	    // - nominal
	    attVals = new FastVector();
	    for (i = 0; i < 5; i++)
	      attVals.addElement("val" + (i+1));
	    atts.addElement(new Attribute("att2", attVals));
	    // - string
	    atts.addElement(new Attribute("att3", (FastVector) null));
	    // - date
	    atts.addElement(new Attribute("att4", "yyyy-MM-dd"));
	    // - relational
	    attsRel = new FastVector();
	    // -- numeric
	    attsRel.addElement(new Attribute("att5.1"));
	    // -- nominal
	    attValsRel = new FastVector();
	    for (i = 0; i < 5; i++)
	      attValsRel.addElement("val5." + (i+1));
	    attsRel.addElement(new Attribute("att5.2", attValsRel));
	    dataRel = new Instances("att5", attsRel, 0);
	    atts.addElement(new Attribute("att5", dataRel, 0));

	    // 2. create Instances object
	    data = new Instances("MyRelation", atts, 0);

	    // 3. fill with data
	    // first instance
	    vals = new double[data.numAttributes()];
	    // - numeric
	    vals[0] = Math.PI;
	    // - nominal
	    vals[1] = attVals.indexOf("val3");
	    // - string
	    vals[2] = data.attribute(2).addStringValue("This is a string!");
	    // - date
	    vals[3] = data.attribute(3).parseDate("2001-11-09");
	    // - relational
	    dataRel = new Instances(data.attribute(4).relation(), 0);
	    // -- first instance
	    valsRel = new double[2];
	    valsRel[0] = Math.PI + 1;
	    valsRel[1] = attValsRel.indexOf("val5.3");
	    dataRel.add(new Instance(1.0, valsRel));
	    // -- second instance
	    valsRel = new double[2];
	    valsRel[0] = Math.PI + 2;
	    valsRel[1] = attValsRel.indexOf("val5.2");
	    dataRel.add(new Instance(1.0, valsRel));
	    vals[4] = data.attribute(4).addRelation(dataRel);
	    // add
	    data.add(new Instance(1.0, vals));

	    // second instance
	    vals = new double[data.numAttributes()];  // important: needs NEW array!
	    // - numeric
	    vals[0] = Math.E;
	    // - nominal
	    vals[1] = attVals.indexOf("val1");
	    // - string
	    vals[2] = data.attribute(2).addStringValue("And another one!");
	    // - date
	    vals[3] = data.attribute(3).parseDate("2000-12-01");
	    // - relational
	    dataRel = new Instances(data.attribute(4).relation(), 0);
	    // -- first instance
	    valsRel = new double[2];
	    valsRel[0] = Math.E + 1;
	    valsRel[1] = attValsRel.indexOf("val5.4");
	    dataRel.add(new Instance(1.0, valsRel));
	    // -- second instance
	    valsRel = new double[2];
	    valsRel[0] = Math.E + 2;
	    valsRel[1] = attValsRel.indexOf("val5.1");
	    dataRel.add(new Instance(1.0, valsRel));
	    vals[4] = data.attribute(4).addRelation(dataRel);
	    // add
	    data.add(new Instance(1.0, vals));

	    // 4. output data
	    System.out.println(data);*/
	}
}
