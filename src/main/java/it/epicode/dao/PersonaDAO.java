package it.epicode.dao;

import it.epicode.entity.Evento;
import it.epicode.entity.Persona;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PersonaDAO {
    private EntityManager em;

    public PersonaDAO(EntityManager em){this.em = em;}

    public void savePerson (Persona p){
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public Persona getPersonaById(Long id){
        return em.find(Persona.class, id);
    }

    public void deletePerson(Long id){
        Persona p = getPersonaById(id);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
    }

    public void insertAllPersonas(List<Persona> lista) {
        em.getTransaction().begin();
        for (Persona p : lista) {
            em.persist(p);
        }
        em.getTransaction().commit();
    }
}
