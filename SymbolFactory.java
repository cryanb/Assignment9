
public class SymbolFactory {
	public GameSymbol setSymbol(int turn) {
		if (turn % 2 == 0) {
			return new xSymbol();
		} else
			return new oSymbol();
	}

}
