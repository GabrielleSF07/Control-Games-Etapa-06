
package controlgames.controlgamesetapa06;

import jakarta.persistence.EntityManager;
import java.sql.Connection;
import java.util.List;

public class DesenvolvedoresDAO {
    private Connector conexao;
    private EntityManager em;
    
    public DesenvolvedoresDAO(EntityManager em){
        this.em = em;
    }
    
    //Salva desenvolvedor
    public void salvarDesenvolvedor(Desenvolvedores d){
        em.merge(d);
    }
   
    //Busca desenvolvedor pelo email
    public Desenvolvedores buscarDesenvolvedores (String email){
    try {
        return em.createQuery(
            "SELECT d FROM Desenvolvedores d WHERE d.email = :email", Desenvolvedores.class)
            .setParameter("email", email)
            .getSingleResult();
    } catch (Exception e) {
        return null;
    }
    }
    
    //Faz login do desenvolvedor
    public Desenvolvedores loginDesenvolvedores (String email, String senha){
    try {
        return em.createQuery(
            "SELECT d FROM Desenvolvedores d WHERE d.email = :email AND d.senha = :senha", Desenvolvedores.class)
            .setParameter("email", email)
            .setParameter("senha", senha)
            .getSingleResult();
    } catch (Exception e) {
        return null;
    }
        } 
    
    //lista dados do desenvolvedor
    public Object[] listarDados(String email) {
        try {
            String jpql = "SELECT d.nome, d.email, COUNT(j), SUM(j.quantidadeVendida), SUM(j.valor * j.quantidadeVendida) " +
                          "FROM Desenvolvedores d LEFT JOIN d.jogos j " +
                          "WHERE d.email = :email " +
                          "GROUP BY d.id, d.nome, d.email";
            return em.createQuery(jpql, Object[].class)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    } 
    }