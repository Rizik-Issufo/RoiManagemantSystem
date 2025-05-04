package ConnectionDb;

import Models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class onCreate {
        public static String databaseTask = "CREATE DATABASE IF NOT EXISTS Task;\n"+
                "USE Task;\n" +
                "Create Table IF NOT EXISTS `Task.product`(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "name varchar(255) NOT NULL," +
                "description varchar(200) ," +
                "purchasePrice  DECIMAL DEFAULT 0.5," +
                "sellingPrice DECIMAL NOT NULL DEFAULT 1," +
                "quantity INTEGER DEFAULT 1," +
                "CHECK purchasePrice < sellingPrice" +
                ");";

//        List<Product> produtos = new ArrayList<>();
//        try (Connection conn = ConnectionFactory.getConnectionTask();
//             PreparedStatement ps = findByNamePs(conn, name);
//             ResultSet rs = ps.executeQuery()
//        ) {
//            while (rs.next()) {
//                Product produto = new Product();
//                produto.setId(rs.getInt("id"));
//                produto.setName(rs.getString("Name"));
//                produto.setDescription(rs.getString("description"));
//                produto.setSellingPrice(rs.getDouble("PurchasePrice"));
//                produto.setPurchasePrice(rs.getDouble("sellingPrice"));
//                produtos.add(produto);
//    }
}
