package controlgames.controlgamesetapa06;

import jakarta.persistence.EntityManager;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;

public class UsuariosDAO {
    private ControlGamesConnector conexao;
    private EntityManager em;
    
    public UsuariosDAO(EntityManager em){
        this.em = em;
    }
    
    //Salvar usuário
    public void salvarUsuario(Usuarios u){
      em.merge(u);
    }
    
    //Busca usuário pelo email
    public Usuarios buscarUsuario(String email){
    try {
        return em.createQuery(
            "SELECT u FROM Usuarios u WHERE u.email = :email", Usuarios.class)
            .setParameter("email", email)
            .getSingleResult();
    } catch (Exception e) {
        return null;
    }
    }
    
    //Faz login do usuário
    public Usuarios loginUsuario (String email, String senha){
    try {
        return em.createQuery(
            "SELECT u FROM Usuarios u WHERE u.email = :email AND u.senha = :senha", Usuarios.class)
            .setParameter("email", email)
            .setParameter("senha", senha)
            .getSingleResult();
    } catch (Exception e) {
        return null;
    }
        }
    
   //Lista dados do usuário
   public Object[] listarDados(String email) {
    try {
        String jpql = "SELECT u.nome, u.email, COUNT(c) " +
                      "FROM Usuarios u LEFT JOIN u.compras c " +
                      "WHERE u.email = :email " +
                      "GROUP BY u.id, u.nome, u.email";
        
        return em.createQuery(jpql, Object[].class)
                 .setParameter("email", email)
                 .getSingleResult();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}
   