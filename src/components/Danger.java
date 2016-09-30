package components;

import scenario.ComponentName;

public abstract class Danger extends Component{

	public Danger(Point coord, ComponentName name) {
		super(coord, name);
		// TODO Auto-generated constructor stub
	}

	public abstract Alert createAlert(Point position);

}
