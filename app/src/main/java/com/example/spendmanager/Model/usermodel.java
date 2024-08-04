package com.example.spendmanager.Model;

public class usermodel {
    private  int userId;
    private String username;
    private String userpassword;

    //contructor

    public usermodel(int userId, String username, String userpassword) {
        this.userId = userId;
        setUsername(username);
        setUserpassword(userpassword);
    }
    // get anf set

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty() || username.length() < 5 || username.length() > 20) {
            throw new IllegalArgumentException("Invalid username");
        }
        this.username = username;
    }
    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        if (userpassword==null || userpassword.trim().isEmpty()||userpassword.length() < 8 ||
                !userpassword.matches(".*[A-Z].*") || !userpassword.matches(".*\\d.*") ||
                !userpassword.matches(".*[!@#$%^&*].*")){
            throw new IllegalArgumentException("Invalid password");

        }
        this.userpassword = userpassword;
    }
}
