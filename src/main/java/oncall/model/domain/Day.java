package oncall.model.domain;

public class Day {

    private boolean isHolyDay;
    private int dayOrder;

    public Day(boolean isHolyDay, int dayOrder) {
        this.isHolyDay = isHolyDay;
        this.dayOrder = dayOrder;
    }

    public boolean isHolyDay() {
        return isHolyDay;
    }
}
