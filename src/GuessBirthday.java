import java.util.HashMap;
import java.util.Map;

public class GuessBirthday {

	String set1 = " 1  3  5  7\n" + " 9 11 13 15\n" + "17 19 21 23\n" + "25 27 29 31";

	String set2 = " 2  3  6  7\n" + "10 11 14 15\n" + "18 19 22 23\n" + "26 27 30 31";

	String set3 = " 4  5  6  7\n" + "12 13 14 15\n" + "20 21 22 23\n" + "28 29 30 31";

	String set4 = " 8  9 10 11\n" + "12 13 14 15\n" + "24 25 26 27\n" + "28 29 30 31";

	String set5 = "16 17 18 19\n" + "20 21 22 23\n" + "24 25 26 27\n" + "28 29 30 31";

	int day = 0;
	int index = 0;

	Map<Integer, String> allDateSets = new HashMap<>();
	Map<Integer, Integer> dayToAdd = new HashMap<>();

	public GuessBirthday() {
		allDateSets.put(0, set1);
		allDateSets.put(1, set2);
		allDateSets.put(2, set3);
		allDateSets.put(3, set4);
		allDateSets.put(4, set5);

		dayToAdd.put(0, 1);
		dayToAdd.put(1, 2);
		dayToAdd.put(2, 4);
		dayToAdd.put(3, 8);
		dayToAdd.put(4, 16);
	}

	public String getSet(int index) {
		return allDateSets.get(index);
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getDay() {
		return this.day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void incrementDay(int index) {
		if (index <= 4) {
			day += dayToAdd.get(index);
		}
	}

}
