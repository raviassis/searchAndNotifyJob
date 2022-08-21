package repositories;

import entities.SearchAndNotify;

import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchAndNotifyRepository {
    private final Connection connection;

    public SearchAndNotifyRepository(DBConnection dbConnection) {
        this.connection = DBConnection.getConnection();
    }

    public List<SearchAndNotify> findAll() throws SQLException {
        List<SearchAndNotify> searchAndNotifies = new ArrayList<>();
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM search_and_notify");

        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            var searchAndNotify = new SearchAndNotify(
                result.getLong("id"),
                URI.create(result.getString("page")),
                result.getString("text_regex"),
                result.getString("email")
            );
            searchAndNotifies.add(searchAndNotify);
        }

        result.close();
        stmt.close();
        return searchAndNotifies;
    }
}
