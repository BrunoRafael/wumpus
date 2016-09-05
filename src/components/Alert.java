package components;

import java.util.List;

import scenario.ComponentName;

public class Alert extends Component{
	
	private ComponentName cName;

	public Alert(List<Integer> coord, ComponentName cName) {
		super(coord);
		this.cName = cName;
	}

	@Override
	public char getSymbol() {
		return this.cName.getSymbol();
	}

}
