package ec.edu.utpl.mad.ti.pintegrativa.chatbimi.repositories;

import ec.edu.utpl.mad.ti.pintegrativa.chatbimi.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BibRepository {
    private static Connection conn = null;

    private static void connect() {
        String urlConn = "jdbc:mysql://localhost:3306/P_INTEGRATIVA";
        if(conn == null) {
            try {
                conn = DriverManager.getConnection(urlConn, "root", "");
            } catch(SQLException exc) {
                exc.printStackTrace();
            }
        }
    }

    public static List<Book> getBooks() {
        String sql = "SELECT ID, CODE, TITLE, YEAR FROM BOOK";
        connect();

        try(PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                books.add(
                        new Book(
                                rs.getInt("ID"),
                                rs.getString("CODE"),
                                rs.getString("TITLE"),
                                rs.getInt("YEAR")
                        ));
            }
            rs.close();
            return books;

        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static Optional<Book> getBookByCode(String code) {
        String sql = "SELECT ID, CODE, TITLE, YEAR FROM BOOK WHERE CODE = ?";
        connect();
        try(PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, code);

            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                return Optional.of(new Book(
                        rs.getInt("ID"),
                        rs.getString("CODE"),
                        rs.getString("TITLE"),
                        rs.getInt("YEAR")));
            }

        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return Optional.empty();
    }
}