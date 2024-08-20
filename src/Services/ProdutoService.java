package Services;

import Models.Product;
import repository.EditoraRepository;
import repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProdutoService {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void findByName() {
        System.out.println("Qual é a produto que deseja pesquisar");
        String nome = SCANNER.next();
        List<Product> produtos = ProductRepository.findByName(nome);
        if (produtos.isEmpty()) {
            System.out.println("= = = = = = = = = = = = = =\n| Produtos nao Encontrados.|\n= = = = = = = = = = = = = =");
            return;
        }
        System.out.println("Codigo | Nome do Produto | Descricao do produto | P. Venda |Custo Compra | Quantidade");
        for (Product produto : produtos)
            System.out.printf("[%d] - %s | %s | %.2f | %.2f | %d\n", produto.getId(), produto.getName(), produto.getDescription(), produto.getSellingPrice(), produto.getPurchasePrice(), produto.getQuantity());

    }

    public static void findAll() {
        List<Product> produtos = ProductRepository.findAll();
        if (produtos.isEmpty()) {
            System.out.println("= = = = = = =\n| Sem Produtos.|\n= = = = = = =");
            return;
        }
        System.out.println("= = = = = Lista de Produtos = = = = =");
        for (Product produto : produtos) {
            System.out.printf("[%d] - %s | %s | %.2f | %.2f | %d\n",
                    produto.getId(),
                    produto.getName(),
                    produto.getDescription(),
                    produto.getSellingPrice(),
                    produto.getPurchasePrice(),
                    produto.getQuantity());

        }
    }

    public static void save() {
        System.out.println("Insira o nome da produto.");
        String nome = SCANNER.nextLine();
        System.out.println("Insira a descricao do produto.");
        String descricao = SCANNER.nextLine();
        System.out.println("Insira o o custo de venda do produto.");
        double venda = SCANNER.nextInt();
        System.out.println("Insira o Custo do produto.");
        double custo = SCANNER.nextDouble();
        System.out.println("Insira a quantidade do estoque.");
        int quantest = SCANNER.nextInt();

        Product product = new Product(nome, descricao, venda, custo, quantest);
        ProductRepository.save(product);
    }

    public static void update() throws SQLException {
        findAll();
        System.out.println("Insira o Codigo da produto");
        Product produto = ProductRepository.findByid(SCANNER.nextInt());
        System.out.println("Digite o novo nome ou antigo:");
        String nome = SCANNER.next();
        System.out.println("Digite o novo descricao ou antigo:");
        String descricao = SCANNER.next();
        System.out.println("Insira novo o custo de venda do produto.");
        double venda = SCANNER.nextInt();
        System.out.println("Insira o novo Custo do produto.");
        double custo = SCANNER.nextDouble();
        System.out.println("Insira a nova quantidade do estoque.");
        int quantest = SCANNER.nextInt();
        produto.setId(produto.getId());
        produto.setName(nome);
        produto.setDescription(descricao);
        produto.setSellingPrice(venda);
        produto.setPurchasePrice(custo);
        produto.setQuantity(quantest);

        ProductRepository.update(produto);

        System.out.println("Atualizacao feita com sucesso.\nPress ENTER");
        SCANNER.nextLine();
    }

    public static void delete() throws SQLException {
        findAll();
        System.out.println("Insira o Id do produto que pretende eliminar:");
        int id = Integer.parseInt(SCANNER.nextLine());
        System.out.print("Tem certeza que deseja eliminar este produto ?? (S)im / (N)ao");
        String escolha = (SCANNER.nextLine());
        if (escolha.toLowerCase().startsWith("s".toLowerCase())) {
            EditoraRepository.delete(id);
        }
    }
}
