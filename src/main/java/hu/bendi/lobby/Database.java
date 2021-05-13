package hu.bendi.lobby;

import java.sql.*;

public class Database {

    static Connection conn;

    public static void connect() throws SQLException {
        String url = "jdbc:postgresql://" + System.getenv("IP") + "/minecraft?user=" + System.getenv("USER") + "&password=" + System.getenv("PASS");
        conn = DriverManager.getConnection(url);
    }

    public static User getUserByName(String name) throws SQLException {
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM auth WHERE Name='" + name + "'");
        if (!rs.next()) return null;
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setUsername(rs.getString("name"));
        u.setPassword(rs.getString("password"));
        u.setIPProtected(rs.getBoolean("isipprotected"));
        u.setLastIp(rs.getString("lastip"));
        return u;
    }


    public static class User {
        private int id;
        private String username;
        private String password;
        private String lastIp;
        private boolean isIPProtected;

        public User(int id, String username, String password, String lastIp, boolean isIPProtected) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.lastIp = lastIp;
            this.isIPProtected = isIPProtected;
        }

        public User() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getLastIp() {
            return lastIp;
        }

        public void setLastIp(String lastIp) {
            this.lastIp = lastIp;
        }

        public boolean isIPProtected() {
            return isIPProtected;
        }

        public void setIPProtected(boolean IPProtected) {
            isIPProtected = IPProtected;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", lastIp='" + lastIp + '\'' +
                    ", isIPProtected=" + isIPProtected +
                    '}';
        }
    }
}
