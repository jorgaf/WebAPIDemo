package ec.edu.utpl.mad.ti.pintegrativa.chatacadbimi;

import ec.edu.utpl.mad.ti.pintegrativa.chatacadbimi.model.Book;
import ec.edu.utpl.mad.ti.pintegrativa.chatacadbimi.util.Mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BibRepository {
    private static Connection conn = null;

    private static void connect() {
        String urlConn = "jdbc:mysql://localhost:3306/P_INTEGRATIVA";

        if (conn == null) {
            try {
                conn = DriverManager.getConnection(urlConn, "root", "");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public static List<Book> getBooks() {
        String sql = "SELECT ID, CODE, TITLE, YEAR FROM BOOK";
        connect();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                books.add(Mapper.toBook(rs.getInt("ID"), rs.getString("CODE"), rs.getString("TITLE"), rs.getInt("YEAR")));
            }
            return books;
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static Optional<Book> getByCode(String code) {
        String sql = "SELECT ID, CODE, TITLE, YEAR FROM BOOK WHERE CODE = ?";
        connect();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, code);
            ResultSet rs = statement.executeQuery();

            rs.next();
            return Optional.of(Mapper.toBook(rs.getInt("ID"), rs.getString("CODE"), rs.getString("TITLE"), rs.getInt("YEAR")));

        } catch (SQLException exp) {
            exp.printStackTrace();
        }
        return Optional.empty();
    }
}