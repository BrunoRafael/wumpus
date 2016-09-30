package components;

import java.util.List;

import scenario.ComponentName;

public class Hole extends Danger{

	public Hole(Point coord) {
		super(coord, ComponentName.HOLE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Alert createAlert(Point position) {
		// TODO Auto-generated method stub
		return new Alert(position, ComponentName.BREEZE);
	}

	@Override
	public char getSymbol() {
		return ComponentName.HOLE.getSymbol();
	}

}
