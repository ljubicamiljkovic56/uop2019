package enumeracije;

public enum Kategorija {
	prva, 
	druga,
	treca;
	
	public static Kategorija valueOf(int a) {
		switch(a) {
		case 1:
			return prva;
		case 2:
			return druga;
		default:
			return treca;
			
		}
	}
	public static int toInt(Kategorija kategorija) {
		switch(kategorija) {
		case prva:
			return 1;
		case druga:
			return 2;
		default:
			return 3;
		}
	}
}
