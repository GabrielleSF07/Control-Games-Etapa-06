
package controlgames.controlgamesetapa06;

import jakarta.persistence.EntityManager;

public class ControlGamesEtapa06 {

    public static void main(String[] args) {
        
     EntityManager em = Connector.getEntityManager();
     
     em.getTransaction().begin();

     UsuariosDAO Udao = new UsuariosDAO(em);
     Usuarios u = new Usuarios(1, "Gabrielle", "gabi123@gmail.com", "123", 18);

     Udao.salvarUsuario(u);

     em.getTransaction().commit();
     em.close();
     
    
     em.getTransaction().begin();

     DesenvolvedoresDAO Ddao = new DesenvolvedoresDAO(em);
     Desenvolvedores d = new Desenvolvedores(1, "Gabrielle", "gabi123@gmail.com", "123");

     Ddao.salvarDesenvolvedor(d);

     em.getTransaction().commit();
     em.close();
     }
}
