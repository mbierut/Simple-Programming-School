import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static User save(User user){
        if(user.getId() == 0){
//            robie insert do bazy
            List<String> params = new ArrayList<>();
            params.add(user.getUsername());
            params.add(user.getEmail());
            params.add(user.getPassword());
            try {
                Integer id = DbService.insertIntoDatabase(
                        "INSERT INTO users(username,email,password) VALUES (?,?,?)",params);
                user.setId(id.longValue());

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
//            aktualizuje obiekt
            List<String> params = new ArrayList<>();
            params.add(user.getUsername());
            params.add(user.getEmail());
            params.add(user.getPassword());
            params.add(String.valueOf(user.getId()));
            try {
                DbService.executeUpdate(
                        "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?",params);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    public static boolean delete(User user){
        boolean isDeleted = false;
        try{
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(user.getId()));
            int affectedRows = DbService.executeUpdate("DELETE FROM users WHERE id = ?",params);
            isDeleted = affectedRows>0;
        }catch (SQLException e){e.printStackTrace();}
        return isDeleted;
    }
    public static List<User> findAll(){
        List<User> users = new ArrayList<>();
        try {
            List<String[]> rows = DbService.getData("SELECT id, username, email, password FROM users",null);
            for (String[] item: rows){
                User user = new User();
                user.setId(Long.valueOf(item[0]));
                user.setUsername(item[1]);
                user.setEmail(item[2]);
                user.setPasswordWithoutHashing(item[3]);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User findById(int id){
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        try {
            List<String[]> rows  = DbService.getData("SELECT id, username, email, password FROM users WHERE id=?",params);
            if(rows.size()>0){
                User user = new User();
                user.setId(Long.valueOf(rows.get(0)[0])); //rows.get(0) pobiera mi pierwsza tablice w liscie
                user.setUsername(rows.get(0)[1]);
                user.setEmail(rows.get(0)[2]);
                user.setPassword(rows.get(0)[3]);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
