import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {

    public static void save(Group group){
        if (group.getId() == 0) {
            List<String> par = new ArrayList<>();
            par.add(group.getName());
            try {
                Integer id = DbService.insertIntoDatabase("INSERT INTO group(name) VALUES (?)", par);
                group.setId(id.intValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            List<String> par = new ArrayList<>();
            par.add(group.getName());
            par.add(String.valueOf(group.getId()));
            try {
                DbService.executeUpdate("UPDATE group SET name=? WHERE id=?", par);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public static boolean delete(Group group){
        boolean isDeleted = false;
        try{
            List<String> par = new ArrayList<>();
            par.add(String.valueOf(group.getId()));
            int affectedRows = DbService.executeUpdate("DELETE FROM group WHERE id=?", par);
            isDeleted = (affectedRows > 0);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return isDeleted;

    }

    public static List<Group> findAll(){
        List<Group> groups = new ArrayList<>();
        try {
            List<String[]> rows = DbService.getData("SELECT id, username, email, password FROM users",null);
            for (String[] item: rows){
                Group group = new Group();
                group.setId(Integer.valueOf(item[0]));
                group.setName(item[1]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;

    }

    public static Group findById(int id){
        return null;
    }
}
