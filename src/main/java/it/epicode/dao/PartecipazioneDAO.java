package it.epicode.dao;

import it.epicode.entity.Evento;
import it.epicode.entity.Partecipazione;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PartecipazioneDAO {
    private EntityManager em;

    public PartecipazioneDAO(EntityManager em){this.em = em;}

    public void savePart (Partecipazione p){
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public Partecipazione getPartById(Long id){
        return em.find(Partecipazione.class, id);
    }

    public void deletePart(Long id){
        Partecipazione p = getPartById(id);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
    }

    public void insertAllPart(List<Partecipazione> lista) {
        em.getTransaction().begin();
        for (Partecipazione p : lista) {
            em.persist(p);
        }
        em.getTransaction().commit();
    }
}
