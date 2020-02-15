package enums;

public enum Side {

    BLACK(0,"黑方"),
    WHITE(1,"白方");

    private int code;

    private String desc;

    Side(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
