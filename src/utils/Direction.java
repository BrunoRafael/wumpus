package utils;

public enum Direction {
	UP("0001"), RIGHT("0010"), DOWN("0100"), LEFT("1000");

	private String code;
	
	Direction(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
	
}
