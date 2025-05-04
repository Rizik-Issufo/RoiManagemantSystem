import Models.Editor;
import Services.EditorService;
import Services.ProductService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static int op;

    private static void ProductMenu() throws SQLException {
        List<Editor> editors = new ArrayList<>();
        boolean isTrue = true;
            System.out.println(" - - - - ARMAZEM ROI - - - -");
       do {
            System.out.println("===============================");
            System.out.println("1. Listar todos Produtos.");
            System.out.println("2. Pesquisar pelo Nome do produto.");
            System.out.println("3. Registar produto. ");
            System.out.println("4. Actualizar produto. ");
            System.out.println("5. Eliminar produto. ");
            System.out.println("0. Voltar. ");
            System.out.println("Selecciona a opcao que deseja Seguir ");
            op = SCANNER.nextByte();
            switch (op) {
                case 1 -> ProductService.findAll();
                case 2 -> ProductService.findByName();
                case 3 -> ProductService.save();
                case 4 -> ProductService.update();
                case 5 -> ProductService.delete();
                case 0 -> {
                    isTrue = false;
                    return;
                }
            }
            continue;
        }while (true);
    }


    private static boolean LibraryMethod() throws SQLException {
        List<Editor> editors = new ArrayList<>();
        boolean bool = true;
                System.out.println(" - - - - Biblioteca ROI - - - -");
        do {
                System.out.println("===============================");
                System.out.println("1. Listar todas Editoras.");
                System.out.println("2. Pesquisar pelo Nome da editora.");
                System.out.println("3. Registar Editora. ");
                System.out.println("4. Actualizar Editora. ");
                System.out.println("5. Eliminar Editora. ");
                System.out.println("0. Voltar. ");
                System.out.println("Selecciona a opcao que deseja Seguir ");
                op = SCANNER.nextInt();
                switch (op) {
                    case 1 -> EditorService.findAll();
                    case 2 -> EditorService.findByName();
                    case 3 -> EditorService.save();
                    case 4 -> EditorService.update();
                    case 5 -> EditorService.delete();
                    case 0 -> {
                        return false;
                    }
                }
                continue;

            }while (true);

    }

    private static boolean mainMenu() throws SQLException {
//        int op;
        boolean bool = true;
            do {
                System.out.println(" - - - - SISTEMA ADMINISTRATIVO ROI - - - -");
                System.out.println("= = = = = = = = = = = = = = = = = = = = = = =");
                System.out.println("1. SISTEMA DE GESTAO DE PRODUTOS.");
                System.out.println("2. SISTEMA DE GESTAO DE BIBLIOTECA.");
                System.out.println("0. SAIR");
                op =SCANNER.nextInt();

                switch (op) {
                    case 1 -> ProductMenu();
                    case 2 -> LibraryMethod();
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

