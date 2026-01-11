package controlgames.controlgamesetapa06;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jogos")
public class Jogos {
    
    public Jogos(){
        
    }

    //Atributos
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private String categoria;

    @Column(name = "faixa_etaria")
    private String faixaEtaria;

    @Column
    private double valor;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @ManyToOne
    @JoinColumn(name = "desenvolvedora_id")
    private Desenvolvedores desenvolvedora;
    
    @Column
    private int quantidadeVendida;
    
    @OneToMany(mappedBy = "jogo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compras> compras = new ArrayList<>();

    //Construtor
    
    public Jogos(int id, String nome, String descricao, String categoria, String faixaEtaria, double valor, LocalDate dataLancamento, Desenvolvedores desenvolvedora, int quantidadeVendida) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.faixaEtaria = faixaEtaria;
        this.valor = valor;
        this.dataLancamento = dataLancamento;
        this.desenvolvedora = desenvolvedora;
        this.quantidadeVendida = quantidadeVendida;
    }

    //Métodos getters e setters
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public Desenvolvedores getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(Desenvolvedores desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public List<Compras> getCompras() {
        return compras;
    }

    public void setCompras(List<Compras> compras) {
        this.compras = compras;
    }
     
    
}