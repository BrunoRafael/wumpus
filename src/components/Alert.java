package components;

import scenario.ComponentName;

public class Alert extends Component{

	public Alert(Point coord, ComponentName name) {
		super(coord, name);
	}

	@Override
	public char getSymbol() {
		return this.getName().getSymbol();
	}

}
