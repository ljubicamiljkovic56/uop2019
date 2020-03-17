package enumeracije;

public enum Status {
	zatraen,
	zakazan,
	otkazan,
	završen;
	
	public static Status valueOf(int a) {
		switch(a) {
		case 1:
			return zatraen;
		case 2:
			return zakazan;
		case 3:
			return otkazan;
		default:
			return završen;
			
		}
	}
	public static int toInt(Status status) {
		switch(status) {
		case zatraen:
			return 1;
		case zakazan:
			return 2;
		case otkazan:
			return 3;
		default:
			return 4;
		}
	}

}
