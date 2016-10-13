package components;

public class Movimentation {
	private String localization;
	private int perception;
	private String movimentation;
	private int exit;
	
	public Movimentation(String localization, int perception, String movimentation, int exit) {
		super();
		this.localization = localization;
		this.perception = perception;
		this.movimentation = movimentation;
		this.exit = exit;
	}
	
	public Movimentation(){}
	
	public String getLocalization() {
		return localization;
	}
	public void setLocalization(String localization) {
		this.localization = localization;
	}
	public int getPerception() {
		return perception;
	}
	public void setPerception(int perception) {
		this.perception = perception;
	}
	public String getMovimentation() {
		return movimentation;
	}
	public void setMovimentation(String movimentation) {
		this.movimentation = movimentation;
	}
	public int getExit() {
		return exit;
	}
	public void setExit(int exit) {
		this.exit = exit;
	}
}
