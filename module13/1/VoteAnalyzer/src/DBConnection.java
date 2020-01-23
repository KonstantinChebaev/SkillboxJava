import java.sql.*;

/**
 * Created by Danya on 24.02.2016.
 */
public class DBConnection {
    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "mmm333";
    private static StringBuilder builder = new StringBuilder();
    private static final int BUFFER_SIZE = 256_000;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("TRUNCATE TABLE voter_count");
//                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
//                connection.createStatement().execute("CREATE TABLE voter_count(" +
//                        "id INT NOT NULL AUTO_INCREMENT, " +
//                        "name TINYTEXT NOT NULL, " +
//                        "birthDate DATE NOT NULL, " +
//                        "`count` INT NOT NULL, " +
//                        "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        if (builder.length() > BUFFER_SIZE){
            flushCounter ();
        }
        if (builder.length()==0){
            builder.append("INSERT INTO voter_count(name, birthDate, `count`) VALUES");
        } else {
            builder.append(",");
        }
        builder.append("('" + name + "', '" + birthDay + "', 1)");
    }

    public static void flushCounter () throws SQLException {
        builder.append(" ON DUPLICATE KEY UPDATE `count` = `count`+1");
        DBConnection.getConnection().createStatement().execute(builder.toString());
        builder = new StringBuilder();
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }
}
