package repository;

import ConnectionDb.ConnectionFactory;
import Models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ConnectionDb.onCreate.databaseTask;

public class ProductRepository {

        public static List<Product> findAll() {
            return findByName("");
        }


        public static List<Product> findByName(String name) {
            String sql = "SELECT * FROM `Task.product` WHERE `nome` LIKE %%?%%;";
            List<Product> products = new ArrayList<>();
            try (Connection conn = ConnectionFactory.getConnectionTask();
                 PreparedStatement ps = findByNamePs(conn, name);
                 ResultSet rs = ps.executeQuery()
            ) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setSellingPrice(rs.getDouble("PurchasePrice"));
                    product.setPurchasePrice(rs.getDouble("sellingPrice"));
                    product.setQuantity(rs.getInt("quantity"));
                    products.add(product);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return products;
        }

        private static PreparedStatement findByNamePs(Connection conn, String name) throws SQLException {
            String sql = "SELECT * FROM Task.product WHERE `name` like ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, String.format("%%%s%%", name));
            return ps;
        }

        public static Product findByid(int codigo) throws SQLException {
            try (Connection conn = ConnectionFactory.getConnectionTask();
                 PreparedStatement ps = findByIdPs(conn, codigo);
                 ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new IllegalArgumentException("Codigo Invalido");
                }
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setDescription(rs.getString(3));
                product.setSellingPrice(rs.getDouble(4));
                product.setPurchasePrice(rs.getDouble(5));
                product.setQuantity(rs.getInt(6));
                return product;
            } catch (
                    SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private static PreparedStatement findByIdPs(Connection conn, Integer id) throws SQLException {
            String sql = "SELECT * FROM Task.product WHERE id  = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps;
        }

        public static void save(Product product) throws SQLException {
            try (Connection conn = ConnectionFactory.getConnectionTask();
                 PreparedStatement ps = savePs(conn, product)) {
                ps.execute();
            } catch (SQLException e) {
                Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost/", "root", "1234");
                PreparedStatement s = conn.prepareStatement(databaseTask);
                s.execute();
                save(product);
//                throw new RuntimeException(e + " - Something Whrong Happened");
            }
        }

        private static PreparedStatement savePs(Connection conn, Product product) throws SQLException {
            String sql = "INSERT INTO `product` (`name`,`description` ,`sellingPrice`, `PurchasePrice`, `quantity`) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getSellingPrice());
            ps.setDouble(4, product.getPurchasePrice());
            ps.setInt(5, product.getQuantity());
            return ps;
        }

        public static void update(Product product) {
            try (Connection conn = ConnectionFactory.getConnectionTask();
                 PreparedStatement ps = updatePs(conn, product);
            ) {
                ps.execute();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        private static PreparedStatement updatePs(Connection conn, Product product) throws SQLException {
            String sql = "UPDATE `Task`.`product` SET `name` = ?,`description` = ? ,`sellingPrice` = ?, `PurchasePrice` = ?, `quantity` = ? WHERE (`id` = ?);\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getSellingPrice());
            ps.setDouble(4, product.getPurchasePrice());
            ps.setInt(5, product.getQuantity());
            return ps;
        }

        public static void delete(int codigo) throws SQLException {
            try (Connection conn = ConnectionFactory.getConnectionTask();
                 PreparedStatement ps = deletePs(conn, codigo);
            ) {
                ps.execute();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private static PreparedStatement deletePs(Connection conn, int id) throws SQLException {
            String sql = "DELETE FROM `Task`.`product` WHERE (`id` = ?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps;
        }

    }