package components;

import java.util.LinkedList;
import java.util.List;

import scenario.ComponentName;

public class Hunter extends Component{
	
	public Hunter(List<Integer> coord){
		super(coord, ComponentName.HUNTER);
	}

	@Override
	public char getSymbol() {
		return ComponentName.HUNTER.getSymbol();
	}

}
