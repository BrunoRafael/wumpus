package scenario;

public enum ComponentName {
	GOLD('G'), WUMPUS('W'), HUNTER('P'), HOLE('H'), BREEZE('B'), BED_SMELL('S');
	
	private char symbol;
	
	ComponentName(char symbol){
		this.symbol = symbol;
	}
	
	public char getSymbol(){
		return this.symbol;
	}
}
