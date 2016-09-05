package components;

import java.util.List;

public abstract class Danger extends Component{

	public Danger(List<Integer> coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}

	public abstract Alert createAlert(List<Integer> position);

}
