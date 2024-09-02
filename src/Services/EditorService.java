package Services;

import Models.Editor;
import repository.EditorRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EditorService {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void findByName() {
        System.out.println("Qual é a editora que deseja pesquisar");
        String name = SCANNER.next();
        List<Editor> editors = EditorRepository.findByName(name);
            if(editors.isEmpty()){
                System.out.println("= = = = = = = = = = = = = =\n| Editoras nao Encontradas.|\n= = = = = = = = = = = = = =");
                return;
            }
        System.out.println(" ID  | Nome da Editora | Email da editora");
        for (int i = 0; i < editors.size(); i++)
            System.out.printf("[%d] - %s (email - %s)\n", editors.get(i).getIdEditora(), editors.get(i).getName(), editors.get(i).getEmail());

    }

    public static void findAll() {
        List<Editor> editors = EditorRepository.findAll();
        if(editors.isEmpty()){
            System.out.println("= = = = = = =\n| Sem Editoras.|\n= = = = = = =");
            return;
        }
        System.out.println("= = = = = Lista de Editoras = = = = =");
        for (int i = 0; i < editors.size(); i++) {
            System.out.printf("[ %d ] - %s (email - %s) \n", editors.get(i).getIdEditora(), editors.get(i).getName(), editors.get(i).getEmail());

        }
    }

    public static void save() {
        System.out.println("Insira o nome da editora.");
        String name = SCANNER.nextLine();
        System.out.println("Insira o e-mail da editora.");
        String email = SCANNER.nextLine();

        Editor editor = new Editor(name, email);
        EditorRepository.save(editor);
        System.out.println("operacao feita com sucesso.");
    }

    public static void update() throws SQLException {
        findAll();
        System.out.println("Insira o Id da editora");
        Editor editor = EditorRepository.findByid(Integer.parseInt(SCANNER.nextLine()));
        System.out.println("Digite o novo nome ou antigo:");
        String name = SCANNER.nextLine();
        System.out.println("Digite o novo email ou antigo:");
        String email = SCANNER.nextLine();
        editor.setIdEditora(editor.getIdEditora());
        editor.setName(name);
        editor.setEmail(email);
        EditorRepository.update(editor);
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
            EditorRepository.delete(id);
        }
    }
}
