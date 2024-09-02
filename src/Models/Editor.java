package Models;

import java.io.Serializable;

public class Editor implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idEditor;
    private String name;
    private String email;

    public Editor() {
    }

    public Editor(String name, String email) {
        this.name = name;
        this.email = email;
    }
// Gerar Getters e Setters
    public Long getIdEditora() {
        return idEditor;
    }

    public void setIdEditora(Long idEditora) {
        this.idEditor = idEditora;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
