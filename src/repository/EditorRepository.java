package repository;

import ConnectionDb.ConnectionFactory;
import Models.Editor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditorRepository {
    public static List<Editor> findAll() {
        return findByName("");
    }


    public static List<Editor> findByName(String name) {
        String sql = "SELECT * FROM `editor` WHERE `nome` LIKE %%?%%;";
        List<Editor> editors = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = findByNamePs(conn, name);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Editor editor = new Editor();
                editor.setIdEditora(rs.getLong("idEditor"));
                editor.setName(rs.getString("name"));
                editor.setEmail(rs.getString("email"));
                editors.add(editor);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return editors;
    }

    private static PreparedStatement findByNamePs(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM editor WHERE `name` like ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }

    public static Editor findByid(int id) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = findByIdPs(conn, id);
             ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) {
                throw new IllegalArgumentException("Id Invalido");
            }
            Editor editor = new Editor();
            editor.setIdEditora(rs.getLong(1));
            editor.setName(rs.getString(2));
            editor.setEmail(rs.getString(3));
            return editor;
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement findByIdPs(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT * FROM editor WHERE idEditor  = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public static void save(Editor editor) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = savePs(conn, editor)) {
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e + " - Something Whrong Happened");
        }
    }

    private static PreparedStatement savePs(Connection conn, Editor editor) throws SQLException {
        String sql = "INSERT INTO `editor` (`name`, `email`) VALUES (?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, editor.getName());
        ps.setString(2, editor.getEmail());
        return ps;
    }

    public static void update(Editor editor) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = updatePs(conn, editor);
        ) {
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private static PreparedStatement updatePs(Connection conn, Editor editor) throws SQLException {
        String sql = "UPDATE `editor` SET `name` = ?, `email` = ? WHERE `idEditor` = ?;\n";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, editor.getName());
        ps.setString(2, editor.getEmail());
        ps.setString(3, editor.getIdEditora().toString());
        return ps;
    }

    public static void delete(int id) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = deletePs(conn, id);
        ) {
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement deletePs(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM `editor` WHERE (`idEditor` = ?);";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

}
