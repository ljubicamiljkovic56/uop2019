package enumeracije;

public enum Sluzba {
	Služba_opste_medicine,
	Služba_zdravstvene_zaštite_dece,
	Stomatološka_služba,
	Služba_zdravstvene_zaštite_radnika,
	Služba_za_pravne_i_ekonomske_poslove,
	Služba_za_tehničke_poslove; 
	
	public static Sluzba valueOf(int a) {
		switch(a) {
		case 1:
			return Služba_opste_medicine;
		case 2:
			return Služba_zdravstvene_zaštite_dece;
		case 3:
			return Stomatološka_služba;
		case 4:
			return Služba_zdravstvene_zaštite_radnika;
		case 5:
			return Služba_za_pravne_i_ekonomske_poslove;
		default:
			return Služba_za_tehničke_poslove;
		}
	}
	public static int toInt(Sluzba sluzba) {
		switch(sluzba) {
		case Služba_opste_medicine:
			return 1;
		case Služba_zdravstvene_zaštite_dece:
			return 2;
		case Stomatološka_služba:
			return 3;
		case Služba_zdravstvene_zaštite_radnika:
			return 4;
		case Služba_za_pravne_i_ekonomske_poslove:
			return 5;
		default:
			return 6;
		}
	}
}
