package it.epicode.dao;

import it.epicode.entity.Evento;
import it.epicode.entity.Location;
import jakarta.persistence.EntityManager;

import java.util.List;

public class LocationDAO {
    private EntityManager em;

    public LocationDAO(EntityManager em){this.em = em;}

    public void saveLocation (Location l){
        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
    }

    public Location getLocationById(Long id){
        return em.find(Location.class, id);
    }

    public void deleteLocation(Long id){
        Location location = getLocationById(id);
        em.getTransaction().begin();
        em.remove(location);
        em.getTransaction().commit();
    }

    public void insertAllLocations(List<Location> lista) {
        em.getTransaction().begin();
        for (Location l : lista) {
            em.persist(l);
        }
        em.getTransaction().commit();
    }
}
