import Models.Editora;
import Services.EditoraService;
import Services.ProdutoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static boolean TarefaProduto() throws SQLException {
        List<Editora> editoras = new ArrayList<>();
        boolean istrue = true;
       do {
            System.out.println(" - - - - ARMAZEM ROI - - - -");
            System.out.println("===============================");
            System.out.println("1. Listar todos Produtos.");
            System.out.println("2. Pesquisar pelo Nome do produto.");
            System.out.println("3. Registar produto. ");
            System.out.println("4. Actualizar produto. ");
            System.out.println("5. Eliminar produto. ");
            System.out.println("0. Voltar. ");
            System.out.println("Selecciona a opcao que deseja Seguir ");
            int op = Integer.parseInt(SCANNER.nextLine());
            switch (op) {
                case 1 -> ProdutoService.findAll();
                case 2 -> ProdutoService.findByName();
                case 3 -> ProdutoService.save();
                case 4 -> ProdutoService.update();
                case 5 -> ProdutoService.delete();
                case 0 -> {
                    istrue = false;
                    return false;
                }
            }
            continue;
        }while (true);
    }


    private static boolean BibliotecaRoi() throws SQLException {
        List<Editora> editoras = new ArrayList<>();
        boolean bool = true;
        do {
                System.out.println(" - - - - Biblioteca ROI - - - -");
                System.out.println("===============================");
                System.out.println("1. Listar todas Editoras.");
                System.out.println("2. Pesquisar pelo Nome da editora.");
                System.out.println("3. Registar Editora. ");
                System.out.println("4. Actualizar Editora. ");
                System.out.println("5. Eliminar Editora. ");
                System.out.println("0. Voltar. ");
                System.out.println("Selecciona a opcao que deseja Seguir ");
                int op = Integer.parseInt(SCANNER.nextLine());
                switch (op) {
                    case 1 -> EditoraService.findAll();
                    case 2 -> EditoraService.findByName();
                    case 3 -> EditoraService.save();
                    case 4 -> EditoraService.update();
                    case 5 -> EditoraService.delete();
                    case 0 -> {
                        return false;
                    }
                }
                continue;

            }while (true);

    }

    private static boolean mainMenu() throws SQLException {
        boolean bool = true;
            do {
                System.out.println(" - - - - SISTEMA ADMINISTRATIVO ROI - - - -");
                System.out.println("= = = = = = = = = = = = = = = = = = = = = = =");
                System.out.println("1. SISTEMA DE GESTAO DE PRODUTOS.");
                System.out.println("2. SISTEMA DE GESTAO DE BIBLIOTECA.");
                System.out.println("0. SAIR");
                int op = Integer.parseInt(SCANNER.nextLine());
                switch (op) {
                    case 1 -> TarefaProduto();
                    case 2 -> BibliotecaRoi();
                    case 0 -> {
                        return false;
                    }
                }
            }while (true);
    }

    public static void main(String[] args) throws SQLException {
        mainMenu();
    }
}

