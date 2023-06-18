package edu.tesji.agendagamer.bd_servicio;

import edu.tesji.agendagamer.bd_repo.BdGamer_repo;
import edu.tesji.agendagamer.modelo.AgendaGamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BdGamer_imple implements BdGamer_serv{

    @Autowired
    BdGamer_repo bdGamerRepo;
    @Override
    public ArrayList<AgendaGamer> mostrarTodosJuegos() {
        return (ArrayList<AgendaGamer>) bdGamerRepo.findAll();
    }

    @Override
    public Optional<AgendaGamer> mostrarJuegoPorId(long id) {
        return bdGamerRepo.findById(id);
    }

    @Override
    public AgendaGamer guardarJuego(AgendaGamer juego) {
        return bdGamerRepo.save(juego);
    }

    @Override
    public boolean borrarJuegoPorId(long id) {
        Optional<AgendaGamer> juegoOptional = bdGamerRepo.findById(id);
        if (juegoOptional.isPresent()) {
            bdGamerRepo.delete(juegoOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean actualizarHoras(long id, AgendaGamer juegoActualizado) {
        Optional<AgendaGamer> juegoOptional = bdGamerRepo.findById(id);
        if (juegoOptional.isPresent()) {
            AgendaGamer juegoExistente = juegoOptional.get();

            // Actualiza los campos necesarios del juego existente con los valores del juego actualizado
            juegoExistente.setHorasJugadas(juegoActualizado.getHorasJugadas());
            // ...

            // Guarda los cambios en la base de datos
            bdGamerRepo.save(juegoExistente);

            return true;
        }
        return false;
    }
    public boolean actualizarLogros(long id, AgendaGamer juegoActualizado) {
        Optional<AgendaGamer> juegoOptional = bdGamerRepo.findById(id);
        if (juegoOptional.isPresent()) {
            AgendaGamer juegoExistente = juegoOptional.get();

            // Actualiza los campos necesarios del juego existente con los valores del juego actualizado
            juegoExistente.setLogros(juegoActualizado.getLogros());
            // ...

            // Guarda los cambios en la base de datos
            bdGamerRepo.save(juegoExistente);

            return true;
        }
        return false;
    }
}
