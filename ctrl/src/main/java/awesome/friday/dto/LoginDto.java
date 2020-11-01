package awesome.friday.dto;

/**
 * Create by yiran at 20.11.1 20:49
 */
public class LoginDto {
    private String username;
    private String password;
    private String uuid;
    private String text;

    public String getUsername() {
        return username;
    }

    public LoginDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public LoginDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getText() {
        return text;
    }

    public LoginDto setText(String text) {
        this.text = text;
        return this;
    }
}
