package scenario;

import java.util.LinkedList;
import java.util.List;

public enum ComponentName {
	GOLD('G'), WUMPUS('W'), HUNTER('P'), HOLE('H'), BREEZE('b'), BED_SMELL('s'), EMPTY('e');
	
	private char symbol;
	
	ComponentName(char symbol){
		this.symbol = symbol;
	}
	
	public char getSymbol(){
		return this.symbol;
	}
	
	public static List<ComponentName> getComponentsNotRepeated(){
		List<ComponentName> componentsNotRepeated = new LinkedList<ComponentName>();
		componentsNotRepeated.add(HOLE);
		componentsNotRepeated.add(WUMPUS);
		componentsNotRepeated.add(GOLD);
		componentsNotRepeated.add(HUNTER);
		
		return componentsNotRepeated;
	}
	
	public static boolean isComponentNotRepeated(ComponentName cName){
		return cName == WUMPUS || cName == HUNTER || cName == GOLD || cName == HOLE;
	}
	
	@Override
	public String toString(){
		return " " + getSymbol() + " ";
	}
}
