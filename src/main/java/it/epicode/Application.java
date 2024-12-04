package it.epicode;

import com.github.javafaker.Faker;
import it.epicode.dao.EventoDAO;
import it.epicode.entity.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Application {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");

        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker(new Locale("it"));

        EventoDAO eventoDAO = new EventoDAO(em);
        List<Evento> lista = new ArrayList<>();
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);

        Date start = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        for (int i = 0; i < 10; i++) {
            Evento e = new Evento();
            e.setTitolo(faker.book().title());
            e.setDescrizione(faker.lorem().sentence());

            e.setDataEvento(faker.date().between(start, end)
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            e.setNumeroMaxPartecipanti(faker.random().nextInt(10, 100));
            e.setTipoEvento(TipoEventoEnum.PRIVATO);

            lista.add(e);
        }

        eventoDAO.insertAll(lista);

        Evento e = eventoDAO.getById(3L);
        System.out.println(e);
        eventoDAO.delete(3L);

        Evento e1 = new Evento();
        e1.setTitolo(faker.book().title());
        e1.setDescrizione(faker.lorem().sentence());
        e1.setDataEvento(faker.date().between(start, end)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        e1.setNumeroMaxPartecipanti(faker.random().nextInt(10, 100));
        e1.setTipoEvento(TipoEventoEnum.PRIVATO);
        eventoDAO.save(e1);


        eventoDAO.save(e1);

        em.close();
        emf.close();


    }
}
