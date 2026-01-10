
package controlgames.controlgamesetapa06;

import jakarta.persistence.EntityManager;

public class ControlGamesEtapa06 {

    public static void main(String[] args) {
     EntityManager em = ControlGamesConnector.getEntityManager();
     em.getTransaction().begin();

     UsuariosDAO dao = new UsuariosDAO(em);
     Usuarios u = new Usuarios(1, "Gabrielle", "gabi123@gmail.com", "123", 18);

     dao.salvarUsuario(u);

     em.getTransaction().commit();
     em.close();
}
}