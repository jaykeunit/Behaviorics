package behaviorics;

import behaviorics.DatabaseConnection;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class BehavioricsDatabaseConnection implements AutoCloseable {
    DatabaseConnection conn;

    public BehavioricsDatabaseConnection() throws IOException {
        conn = new DatabaseConnection();
        createConnection();
    }

    private void createConnection() throws IOException {
        try (InputStream input = new FileInputStream("config.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            conn.createConnection(prop.getProperty("dbdriver"),
                    prop.getProperty("dbname"),
                    prop.getProperty("dbuser"),
                    prop.getProperty("dbpassword"));
        }
    }

    public PreparedStatement prepareStatement(String query) throws IOException, SQLException {
        return conn.prepareStatement(query);
    }

    public void closeConnection() throws SQLException {
        conn.closeConnection();
    }

    @Override
    public void close() throws Exception {
        closeConnection();
    }
}

