package components;

import java.util.List;

import scenario.ComponentName;

public class Hunter extends Component{
	
	public Hunter(List<Integer> coord){
		super(coord);
	}
	
	public Hunter(int x, int y){
		super(x, y);
	}

	@Override
	public char getSymbol() {
		return ComponentName.HUNTER.getSymbol();
	}

}
