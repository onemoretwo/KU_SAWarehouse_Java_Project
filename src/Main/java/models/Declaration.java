package Main.java.models;

public class Declaration {
    private int id;
    private String username;
    private String status;
    private String timestamp;
    private String type;

    public Declaration(int id, String username, String status, String timestamp, String type) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.timestamp = timestamp;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }
}
