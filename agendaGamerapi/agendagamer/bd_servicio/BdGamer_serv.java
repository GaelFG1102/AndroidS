package edu.tesji.agendagamer.bd_servicio;

import edu.tesji.agendagamer.modelo.AgendaGamer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

public interface BdGamer_serv {
    ArrayList<AgendaGamer> mostrarTodosJuegos();

    Optional<AgendaGamer> mostrarJuegoPorId(long id);

    AgendaGamer guardarJuego(AgendaGamer alumno);

    boolean borrarJuegoPorId(long id);

    boolean actualizarLogros(long id, AgendaGamer juegoActualizado);

    boolean actualizarHoras(long id, AgendaGamer juegoActualizado);

}
