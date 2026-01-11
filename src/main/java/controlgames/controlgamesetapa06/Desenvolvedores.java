package controlgames.controlgamesetapa06;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "desenvolvedores")
public class Desenvolvedores {
    
    public Desenvolvedores(){
    }
   
    //Atributos
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "nome_empresa")
    private String nome;
    
    @Column
    private String email;
    
    @Column
    private String senha;
    
    @OneToMany(mappedBy = "desenvolvedora", fetch = FetchType.LAZY)
    private List<Jogos> jogos;

    
    //Método construtor
    public Desenvolvedores(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    //Métodos getters e setters
    
    public List<Jogos> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogos> jogos) {
        this.jogos = jogos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
