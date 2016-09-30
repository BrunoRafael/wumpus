package components;

import scenario.ComponentName;

public class Gold extends Component{

	public Gold(Point coord) {
		super(coord, ComponentName.GOLD);
	}

	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return ComponentName.GOLD.getSymbol();
	}

}
