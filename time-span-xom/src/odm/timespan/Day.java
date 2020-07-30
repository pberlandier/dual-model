package odm.timespan;

public class Day {

	private int dayOfTheWeek;
	private boolean holiday = false;
	private boolean workingDay = false;
	private boolean overtime = false;
	private boolean worked = false;

	public Day(int dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public boolean isSaturday() {
		return (dayOfTheWeek == 5);
	}

	public boolean isSunday() {
		return (dayOfTheWeek == 6);
	}

	public boolean isHoliday() {
		return holiday;
	}

	public void setHoliday(boolean holiday) {
		this.holiday = holiday;
	}

	public boolean isWorkingDay() {
		return workingDay;
	}

	public void setWorkingDay(boolean workingDay) {
		this.workingDay = workingDay;
	}

	public boolean isOvertime() {
		return overtime;
	}

	public void setOvertime(boolean overtime) {
		this.overtime = overtime;
	}

	public boolean isWorked() {
		return worked;
	}

	public void setWorked(boolean worked) {
		this.worked = worked;
	}

}
