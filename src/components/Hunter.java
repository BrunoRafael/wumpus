package components;

import scenario.ComponentName;

public class Hunter extends Component{
	
	public Hunter(Point coord){
		super(coord, ComponentName.HUNTER);
	}

	@Override
	public char getSymbol() {
		return ComponentName.HUNTER.getSymbol();
	}

}
