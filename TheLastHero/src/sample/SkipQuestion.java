package sample;

public class SkipQuestion {
    private boolean skipBool = false;
    private boolean used;

    public void setSkipBool(boolean skipBool) {
        this.skipBool = skipBool;
    }

    public boolean isSkipBool() {
        return skipBool;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
