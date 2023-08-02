package Database;

public class UnknownFile extends Exception {
    private final String error;

    public UnknownFile(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
