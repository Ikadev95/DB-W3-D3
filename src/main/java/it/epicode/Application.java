package it.epicode;

import it.epicode.dao.EventoDAO;
import it.epicode.dao.LocationDAO;
import it.epicode.dao.PartecipazioneDAO;
import it.epicode.dao.PersonaDAO;
import it.epicode.entity.Evento;
import it.epicode.entity.Location;
import it.epicode.entity.Partecipazione;
import it.epicode.entity.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {

    public static void main(String[] args) {
        // Creazione dell'EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
        EntityManager em = emf.createEntityManager();

        // Creazione degli oggetti DAO
        PersonaDAO personaDAO = new PersonaDAO(em);
        LocationDAO locationDAO = new LocationDAO(em);
        EventoDAO eventoDAO = new EventoDAO(em);
        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(em);

        try {
            // Creazione e salvataggio di una Location
            Location location = new Location();
            location.setNome("Auditorium");
            location.setCitta("Roma");
            locationDAO.saveLocation(location);

            // Creazione e salvataggio di una Persona
            Persona persona = new Persona();
            persona.setNome("Giovanni");
            persona.setCognome("Rossi");
            persona.setEmail("giovanni.rossi@example.com");
            persona.setDataDiNascita(LocalDate.of(1990, 5, 15));
            persona.setSesso(SessoEnum.M);
            personaDAO.savePerson(persona);

            // Creazione e salvataggio di un Evento
            Evento evento = new Evento();
            evento.setTitolo("Concerto di Natale");
            evento.setDataEvento(LocalDate.of(2024, 12, 25));
            evento.setDescrizione("Un evento imperdibile con musica natalizia");
            evento.setTipoEvento(TipoEventoEnum.PUBBLICO);
            evento.setNumeroMaxPartecipanti(200);
            evento.setLocation(location); // Associa la location all'evento
            eventoDAO.save(evento);

            // Creazione e salvataggio di una Partecipazione
            Partecipazione partecipazione = new Partecipazione();
            partecipazione.setPersona(persona); // Associa la persona all'evento
            partecipazione.setEvento(evento);   // Associa l'evento alla persona
            partecipazione.setStato(StatoEnum.CONFERMATA);
            partecipazioneDAO.savePart(partecipazione);

            System.out.println("Persona, location ed evento salvati con successo!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Chiusura dell'EntityManager
            em.close();
            emf.close();
        }
    }

}
