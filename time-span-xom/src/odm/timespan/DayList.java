package odm.timespan;

import java.util.ArrayList;
import java.util.List;

public class DayList {
	private List<Day> members;

	public List<Day> getMembers() {
		return members;
	}

	public void setMembers(List<Day> members) {
		this.members = members;
	}

	/**
	 * Creates a sample instance with initialized elements.
	 * 
	 * @param count
	 * @return
	 */
	public static DayList createSampleInstance(int count) {
		DayList instance = new DayList();
		ArrayList<Day> members = new ArrayList<Day>();
		instance.setMembers(members);
		for (int i = 0; i < count; i++) {
			Day day = new Day(i % 7);
			members.add(day);
			//
			// Every 50 days is a holiday!
			//
			if (i % 50 == 0) {
				day.setHoliday(true);
			}
			//
			// Worker takes a day off every 42 days.
			//
			if (i % 42 != 0) {
				day.setWorked(true);
			}
		}
		return instance;
	}
}
