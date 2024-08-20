package Services;

import Models.Editora;
import repository.EditoraRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EditoraService {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void findByName() {
        System.out.println("Qual é a editora que deseja pesquisar");
        String name = SCANNER.next();
        List<Editora> editoras = EditoraRepository.findByName(name);
            if(editoras.isEmpty()){
                System.out.println("= = = = = = = = = = = = = =\n| Editoras nao Encontradas.|\n= = = = = = = = = = = = = =");
                return;
            }
        System.out.println(" ID  | Nome da Editora | Email da editora");
        for (int i = 0; i < editoras.size(); i++)
            System.out.printf("[%d] - %s (email - %s)\n", editoras.get(i).getIdEditora(), editoras.get(i).getName(), editoras.get(i).getEmail());

    }

    public static void findAll() {
        List<Editora> editoras = EditoraRepository.findAll();
        if(editoras.isEmpty()){
            System.out.println("= = = = = = =\n| Sem Editoras.|\n= = = = = = =");
            return;
        }
        System.out.println("= = = = = Lista de Editoras = = = = =");
        for (int i = 0; i < editoras.size(); i++) {
            System.out.printf("[ %d ] - %s (email - %s) \n", editoras.get(i).getIdEditora(), editoras.get(i).getName(), editoras.get(i).getEmail());

        }
    }

    public static void save() {
        System.out.println("Insira o nome da editora.");
        String name = SCANNER.nextLine();
        System.out.println("Insira o e-mail da editora.");
        String email = SCANNER.nextLine();

        Editora editora = new Editora(name, email);
        EditoraRepository.save(editora);
    }

    public static void update() throws SQLException {
        findAll();
        System.out.println("Insira o Id da editora");
        Editora editora = EditoraRepository.findByid(Integer.parseInt(SCANNER.nextLine()));
        System.out.println("Digite o novo nome ou antigo:");
        String name = SCANNER.nextLine();
        System.out.println("Digite o novo email ou antigo:");
        String email = SCANNER.nextLine();
        editora.setIdEditora(editora.getIdEditora());
        editora.setName(name);
        editora.setEmail(email);
        EditoraRepository.update(editora);
        System.out.println("Atualizacao feita com sucesso.\nPress ENTER");
        SCANNER.nextLine();
    }

    public static void delete() throws SQLException {
        findAll();
        System.out.println("Insira o Id da editora que pretende eliminar:");
        int id = Integer.parseInt(SCANNER.nextLine());
        System.out.printf("Tem certeza que deseja eliminar esta editora ?? (S)im / (N)ao");
        String escolha = (SCANNER.nextLine());
        if (escolha.toLowerCase().startsWith("s".toLowerCase())) {
            EditoraRepository.delete(id);
        }
    }
}
