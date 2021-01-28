package hashi;

public class StdIsland implements Island {

	// ATTRIBUTS

	private int x;
	private int y;
	private int value;
	private int firstValue;
	private boolean created = false;
	private Island[] neighbors;

	// CONSTRUCTEURS

	public StdIsland(Island i) {
		this(i.x(), i.y(), i.value());
		this.firstValue = i.firstValue();
		this.created = i.isCreated();
	}

	public StdIsland(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
		neighbors = new Island[4];
	}

	// REQUETES

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public int value() {
		return value;
	}

	public void addValue(int add) {
		value += add;
	}

	public void removeValue(int add) {
		value -= add;
	}

	public void changeValueTo(int v) {
		value = v;
	}

	public void created() {
		this.firstValue = this.value;
		this.created = true;
	}

	public int firstValue() {
		return this.firstValue;
	}

	public boolean isCreated() {
		return this.created;
	}

	public Island[] getNeighbor() {
		return neighbors;
	}

	public Island getNeighborIndex(int i) {
		return neighbors[i];
	}

	public Island setNeighbor(int i) {
		neighbors[i] = null;
		return neighbors[i];
	}

	public Island nextNeighbor(int i, Island island) {
		neighbors[i] = island;
		return neighbors[i];
	}

	public void setInitialValue(int v) {
		firstValue = v;
	}

	// COMMANDES

	@Override
	public String toString() {
		String result = "";
		result += this.formatInt(x);
		result += this.formatInt(y);
		return result;
	}

	public String formatInt(int i) {
		String result = "";
		if (i < 10) {
			result += "0" + i;
		} else {
			result += i;
		}

		return result;
	}
}
