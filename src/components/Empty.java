package components;

import scenario.ComponentName;

public class Empty extends Component{

	@Override
	public char getSymbol() {
		return ComponentName.EMPTY.getSymbol();
	}
}
