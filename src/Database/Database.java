package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.JDBC;

public class Database {

    private final String DB_URL  = "jdbc:sqlite:data.db";
    private Connection conn=null;

    public Database()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //TABLE CREATION HERE
        createCategoriesTable();
        createComplaintsTable();
        createEventsCategoriesConnectionsTable();
        createEventsTable();
        createNoticesTable();
        createOrganizationsTable();
        createUpdatesTable();
        createUsersTable();
        closeConnection();
    }

    // close the connection to the database
    public void closeConnection(){
        try {
            if (conn != null) {
                conn.close();
                conn=null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getDB_URL() {
        return DB_URL;
    }


    public Connection getConn()
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }



    /**
     * create a new users table if it not already exist's
     */
    private void createUsersTable() {
        // SQLite connection string
        // String url = "jdbc:sqlite:data.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	user_name TEXT PRIMARY KEY NOT NULL, \n"
                + "	password TEXT NOT NULL\n,"
                + "	email TEXT NOT NULL,\n"
                + "	rank INTEGER NOT NULL,\n"
                + "	organization TEXT NOT NULL,\n"
                + "	status TEXT NOT NULL,\n"
                + "	warning_counter INTEGER NOT NULL,\n"
                + "	pass_attempts INTEGER NOT NULL,\n"
                + "	admin BOOLEAN  NOT NULL,\n"
                + " CONSTRAINT name_unique UNIQUE(user_name)"
                + ");";

        try (Statement stmt = conn.createStatement();
             Statement statement =conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * create a new users table if it not already exist's
     */
    private void createOrganizationsTable() {
        // SQLite connection string
        // String url = "jdbc:sqlite:data.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS organizations (\n"
                + "	name TEXT PRIMARY KEY NOT NULL, \n"
                + "	admin_user_name TEXT NOT NULL\n,"
                + " CONSTRAINT name_unique UNIQUE(name)"
                + ");";

        try (Statement stmt = conn.createStatement();
             Statement statement =conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createCategoriesTable() {
        // SQLite connection string
        // String url = "jdbc:sqlite:data.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS categories (\n"
                + "	name TEXT PRIMARY KEY NOT NULL, \n"
                + " CONSTRAINT name_unique UNIQUE(name)"
                + ");";

        try (Statement stmt = conn.createStatement();
             Statement statement =conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createEventsTable() {
        // SQLite connection string
        // String url = "jdbc:sqlite:data.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS events (\n"
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + "	title TEXT NOT NULL, \n"
                + "	time_published TEXT NOT NULL, \n"
                + "	representative_user_name TEXT NOT NULL, \n"
                + "	status TEXT NOT NULL, \n"
                + "	organization TEXT NOT NULL, \n"
                + " CONSTRAINT id_unique UNIQUE(id)"
                + ");";

        try (Statement stmt = conn.createStatement();
             Statement statement =conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createEventsCategoriesConnectionsTable() {
        // SQLite connection string
        // String url = "jdbc:sqlite:data.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS eventscateogiresconnections (\n"
                + "event_id INTEGER NOT NULL, \n"
                + "	category_name TEXT NOT NULL, \n"
                +" CONSTRAINT fk_event_id FOREIGN KEY(event_id) REFERENCES events(id) ON DELETE CASCADE"
                +" CONSTRAINT fk_category_name FOREIGN KEY(category_name) REFERENCES categories(name) ON DELETE CASCADE);";

        try (Statement stmt = conn.createStatement();
             Statement statement =conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createUpdatesTable() {
        // SQLite connection string
        // String url = "jdbc:sqlite:data.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS updates (\n"
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + "	date_created TEXT NOT NULL, \n"
                + "	description TEXT NOT NULL, \n"
                + "	initial_description TEXT NOT NULL, \n"
                + "	event_id TEXT NOT NULL, \n"
                + "	previous_update_id TEXT NOT NULL, \n"
                +" CONSTRAINT fk_event_id FOREIGN KEY(event_id) REFERENCES events(id) ON DELETE CASCADE);";
//                +" CONSTRAINT fk_previous_update FOREIGN KEY(previous_update_id) REFERENCES updates(id) ON DELETE CASCADE);";

        try (Statement stmt = conn.createStatement();
             Statement statement =conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createNoticesTable() {
        // SQLite connection string
        // String url = "jdbc:sqlite:data.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS notices (\n"
                + "from_user TEXT NOT NULL, \n"
                + "	to_user TEXT NOT NULL, \n"
                + "	event_id TEXT NOT NULL, \n"
                + "	status TEXT NOT NULL, \n"
                + "	PRIMARY KEY (from_user, to_user, event_id) \n"
                + ");";

        try (Statement stmt = conn.createStatement();
             Statement statement =conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createComplaintsTable() {
        // SQLite connection string
        // String url = "jdbc:sqlite:data.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS notices (\n"
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " from_user TEXT NOT NULL, \n"
                + "	to_user TEXT NOT NULL, \n"
                + "	approved BOOLEAN NOT NULL, \n"
                + "	content TEXT NOT NULL, \n"
                + " CONSTRAINT id_unique UNIQUE(id)"
                + ");";

        try (Statement stmt = conn.createStatement();
             Statement statement =conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}
