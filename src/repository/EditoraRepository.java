package repository;

import ConnectionDb.ConnectionFactory;
import Models.Editora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditoraRepository {
    public static List<Editora> findAll() {
        return findByName("");
    }


    public static List<Editora> findByName(String name) {
        String sql = "SELECT * FROM `livraria.editora` WHERE `nome` LIKE %%?%%;";
        List<Editora> editoras = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = findByNamePs(conn, name);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Editora editora = new Editora();
                editora.setIdEditora(rs.getLong("idEditora"));
                editora.setName(rs.getString("nome"));
                editora.setEmail(rs.getString("email"));
                editoras.add(editora);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return editoras;
    }

    private static PreparedStatement findByNamePs(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM livraria.editora WHERE `nome` like ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }

    public static Editora findByid(int id) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = findByIdPs(conn, id);
             ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) {
                throw new IllegalArgumentException("Id Invalido");
            }
            Editora editora = new Editora();
            editora.setIdEditora(rs.getLong(1));
            editora.setName(rs.getString(2));
            editora.setEmail(rs.getString(3));
            return editora;
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement findByIdPs(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT * FROM livraria.editora WHERE idEditora  = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public static void save(Editora editora) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = savePs(conn, editora)) {
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e + " - Something Whrong Happened");
        }
    }

    private static PreparedStatement savePs(Connection conn, Editora editora) throws SQLException {
        String sql = "INSERT INTO `livraria`.`editora` (`nome`, `email`) VALUES (?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, editora.getName());
        ps.setString(2, editora.getEmail());
        return ps;
    }

    public static void update(Editora editora) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = updatePs(conn, editora);
        ) {
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private static PreparedStatement updatePs(Connection conn, Editora editora) throws SQLException {
        String sql = "UPDATE `livraria`.`editora` SET `nome` = ?, `email` = ? WHERE (`idEditora` = ?);\n";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, editora.getName());
        ps.setString(1, editora.getEmail());
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
        String sql = "DELETE FROM `livraria`.`editora` WHERE (`idEditora` = ?);";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

}
