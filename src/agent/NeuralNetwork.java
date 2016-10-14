package agent;

import java.io.BufferedReader;
import java.io.FileReader;

import components.Movimentation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;

public class NeuralNetwork {
	
	private Instances train;
	private MultilayerPerceptron mlp;
	private String path;
	
	public NeuralNetwork(){
		mlp = new MultilayerPerceptron();
	}
	
	public void train(String filepath)
	{
		this.path = filepath;
		try{
			//Reading training arff or csv file
			train = new Instances(new BufferedReader(
					new FileReader(this.path)));
			int size = train.numAttributes();
			train.setClassIndex(size - 1);
			
			//Instance of NN
			//Setting Parameters
			mlp.setLearningRate(0.1);
			mlp.setMomentum(0.2);
			mlp.setTrainingTime(20000);
			mlp.setHiddenLayers("3");
			mlp.buildClassifier(train);
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public boolean predict(Movimentation mov){	
		double[] values = new double[train.numAttributes() - 1];
		values[0] = train.attribute(0).addStringValue(mov.getLocalization());
		values[1] = mov.getPerception();
		values[2] = train.attribute(2).addStringValue(mov.getMovimentation());
		values[3] = mov.getExit();
		Instance i = new Instance(1.0, values);
		
		try {
			double clsLabel = mlp.classifyInstance(i);
			System.out.println(clsLabel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	
	}

}
