
import org.mindrot.jbcrypt.BCrypt;

public class User {

    private long id;
    private String username;
    private String email;
    private String password;
    private long user_group_id; // chce zeby tu byl obiekt klasy Group
    //private Group group;


    public User(String username, String email, String password) {
        this.id = 0;
        this.username = username;
        this.email = email;
        this.setPassword(password);
    }

    public User() {
    }
    public void setId(long id){
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public void setPasswordWithoutHashing(String password){
        this.password = password;
    }
    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password,BCrypt.gensalt());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public long getUser_group_id() {
        return user_group_id;
    }

    public void setUser_group_id(long user_group_id) {
        this.user_group_id = user_group_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", user_group_id=" + user_group_id +
                '}';
    }
}
