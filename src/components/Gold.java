package components;

import java.util.List;

import scenario.ComponentName;

public class Gold extends Component{

	public Gold(List<Integer> coord) {
		super(coord);
	}

	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return ComponentName.GOLD.getSymbol();
	}

}
