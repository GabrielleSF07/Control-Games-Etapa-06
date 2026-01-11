package controlgames.controlgamesetapa06;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

    public class JogosDAO {
        private Connector conexao;
        EntityManager em;
        EntityTransaction et = em.getTransaction();

        public JogosDAO(EntityManager em){
            this.em = em;
        }

    //Classe de listagem
    public class listar{
      
    //Lista jogos da desenvolvedora    
    public List<Jogos> listarJogosDev(Desenvolvedores d) {
        try {
            return em.createQuery("SELECT j FROM Jogos j WHERE j.desenvolvedora = :dev", Jogos.class)
                     .setParameter("dev", d)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    //Lista jogos do usuário
    public List<Jogos> listarJogosUser(Usuarios u) {
        try {
            return em.createQuery(
                "SELECT j FROM Jogos j " +
                "JOIN j.desenvolvedora d " +
                "JOIN j.compras c " +
                "WHERE c.usuario = :u", Jogos.class)
                .setParameter("u", u)
                .getResultList();
        } finally {
            em.close();
        }
     }
    
    //Lista todos os jogos
    public List<Jogos> listarTodos() {
    return em.createQuery("SELECT j FROM Jogos j", Jogos.class)
             .getResultList();
     }
    }

    //Métodos de gerenciamento
    public class gerenciamento{
     
    //Salva um jogo    
    public void salvar(Jogos jogo){
        try{
            et.begin();
            em.persist(jogo);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
   
   //Edita um jogo 
   public void editar(Jogos jogo) {
    try {
        et.begin();
        em.merge(jogo);
        et.commit();
    } catch (Exception e) {
        if (et.isActive()) et.rollback();
        throw e;
    } finally {
        em.close();
     }
    }
   
   //Exclui um jogo
    public void excluir(Jogos jogo) {
    try {
        et.begin();
        Jogos jogoGerenciado = em.find(Jogos.class, jogo.getId());
        if (jogoGerenciado != null) {
            em.remove(jogoGerenciado);
        }
        et.commit();
    } catch (Exception e) {
        if (et.isActive()) et.rollback();
        throw e;
    } finally {
        em.close();
    }
    }
     }
   
   //Métodos de compra
   public class compra{ 
       
   //Usuário já tem o jogo    
   public boolean usuarioJaComprou(Usuarios u, Jogos j) {
    Long count = em.createQuery(
        "SELECT COUNT(c) FROM Compras c WHERE c.usuario = :u AND c.jogo = :j", Long.class)
        .setParameter("u", u)
        .setParameter("j", j)
        .getSingleResult();
    return count > 0;
    }
   
   //Usuário compra jogo
   public void comprarJogo(Usuarios u, Jogos j) {
    try {
        if (usuarioJaComprou(u, j)) {
            System.out.println("Você já comprou este jogo.");
            return; 
        }

        et.begin();
        Compras c = new Compras();
        c.setUsuario(u);
        c.setJogo(j);
        em.persist(c);
        et.commit();
        System.out.println("Jogo comprado com sucesso!");
        
    } catch (Exception e) {
        if (et.isActive()) et.rollback();
        throw e;
     }
    }
   }
}
