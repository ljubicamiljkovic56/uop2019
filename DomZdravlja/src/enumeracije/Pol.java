package enumeracije;

public enum Pol {
	Muski,
	Zenski;
	
	public static Pol valueOf(int a) {
		switch(a) {
		case 1:
			return Muski;
		default:
			return Zenski;
			
		}
	}
	public static int toInt(Pol pol) {
		switch(pol) {
		case Muski:
			return 1;
		default:
			return 2;
		}
	}

}
