package components;

import java.util.List;

import scenario.ComponentName;

public abstract class Danger extends Component{

	public Danger(List<Integer> coord, ComponentName name) {
		super(coord, name);
		// TODO Auto-generated constructor stub
	}

	public abstract Alert createAlert(List<Integer> position);

}
