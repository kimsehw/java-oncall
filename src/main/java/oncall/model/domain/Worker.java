package oncall.model.domain;

public class Worker {
    private String name;
    private boolean changed;

    public Worker(String name, boolean changed) {
        this.name = name;
        this.changed = changed;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
