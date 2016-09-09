package components;

import java.util.List;

import scenario.ComponentName;

public class Alert extends Component{

	public Alert(List<Integer> coord, ComponentName name) {
		super(coord, name);
	}

	@Override
	public char getSymbol() {
		return this.getName().getSymbol();
	}

}
