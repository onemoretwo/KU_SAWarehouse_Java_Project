package Main.java.models;

public class User {
    private String username;
    private String password;
    private String name;
    private String role;
    private int age;
    private String gender;
    private String telephoneNumber;
    private String email;
    private String created_at;
    private String imgName;

    public User(String username, String password, String name,
                String role, int age, String gender, String telephoneNumber,
                String email, String created_at, String imgName) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        this.age = age;
        this.gender = gender;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.created_at = created_at;
        this.imgName = imgName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getImgName() {
        return imgName;
    }
}
