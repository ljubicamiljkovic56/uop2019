package enumeracije;

public enum Uloga {

	pacijent,
	lekar,
	medicinska_sestra;
	
	public static Uloga valueOf(int a) {
		switch(a) {
		case 1:
			return pacijent;
		case 2:
			return lekar;
		default:
			return medicinska_sestra;
		}
	}
	public static int toInt(Uloga uloga) {
		switch(uloga) {
		case pacijent:
			return 1;
		case lekar:
			return 2;
		default:
			return 3;
		}
	}
}
