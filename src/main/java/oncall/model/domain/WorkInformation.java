package oncall.model.domain;

public class WorkInformation {

    private final int month;
    private final String workerName;
    private final String day;
    private final boolean isHoliday;

    public WorkInformation(int month, String workerName, String day, boolean isHoliday) {
        this.month = month;
        this.workerName = workerName;
        this.day = day;
        this.isHoliday = isHoliday;
    }

    public int getMonth() {
        return month;
    }

    public String getWorkerName() {
        return workerName;
    }

    public String getDay() {
        return day;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    @Override
    public String toString() {
        return "WorkInformation{" +
                "day='" + day + '\'' +
                ", workerName='" + workerName + '\'' +
                ", isHoliday=" + isHoliday +
                '}';
    }
}
