package dev.chsr.passwordmanager;

public class PasswordItem {
    private final String key;
    private String email;
    private String password;
    private String notes;

    public PasswordItem(String key, String email, String password, String notes) {
        this.key = key;
        this.email = email;
        this.password = password;
        this.notes = notes;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getKey() {
        return key;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNotes() {
        return notes;
    }
}