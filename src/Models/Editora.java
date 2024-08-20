package Models;

import java.io.Serializable;

public class Editora implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idEditora;
    private String name;
    private String email;

    public Editora() {
    }

    public Editora(String name, String email) {
        this.name = name;
        this.email = email;
    }
// Gerar Getters e Setters
    public Long getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(Long idEditora) {
        this.idEditora = idEditora;
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
