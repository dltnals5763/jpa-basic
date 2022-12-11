package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try {
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("helloJPA");
            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name= " + member.getName());
            }
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
