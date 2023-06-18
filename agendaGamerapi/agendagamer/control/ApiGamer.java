package edu.tesji.agendagamer.control;

import edu.tesji.agendagamer.bd_repo.BdGamer_repo;
import edu.tesji.agendagamer.bd_servicio.BdGamer_serv;
import edu.tesji.agendagamer.modelo.AgendaGamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("bdgamer")
public class ApiGamer {

    @Autowired
    BdGamer_serv bdGamerServ;

    @GetMapping("/prueba")
    public String prueba() {
        return "Hola Mundo desde Spinng Java";
    }

    @GetMapping("/mostrar-todos")
    public ArrayList<AgendaGamer> mostrarTodosAlumn() {
        return bdGamerServ.mostrarTodosJuegos();
    }

    @PostMapping("/guardar")
    public AgendaGamer guardarJuego(@RequestBody AgendaGamer juego) {
        return bdGamerServ.guardarJuego(juego);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarJuego(@PathVariable long id) {
        boolean borrado = bdGamerServ.borrarJuegoPorId(id);
        if (borrado) {
            return ResponseEntity.ok("Juego eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el juego con el ID especificado.");
        }
    }

    @GetMapping("/finbyid/{id}")
    public Optional<AgendaGamer> mostrarJuegoPorId(@PathVariable long id) {
        Optional<AgendaGamer> juegoOptional = bdGamerServ.mostrarJuegoPorId(id);
        if (juegoOptional.isPresent()) {
            return bdGamerServ.mostrarJuegoPorId(id);
        } else {
            return Optional.empty();
        }
    }

    @PostMapping("/horas/{id}")
    public ResponseEntity<AgendaGamer> actualizarHoras(@PathVariable long id, @RequestBody AgendaGamer juegoActualizado) {
        Optional<AgendaGamer> juegoOptional = bdGamerServ.mostrarJuegoPorId(id);
        if (juegoOptional.isPresent()) {
            AgendaGamer juegoExistente = juegoOptional.get();
            // Actualiza los campos necesarios del juego existente con los valores del juego actualizado
            juegoExistente.setHorasJugadas(juegoActualizado.getHorasJugadas());
            // ...

            // Guarda los cambios en la base de datos
            AgendaGamer juegoActualizadoGuardado = bdGamerServ.guardarJuego(juegoExistente);
            return ResponseEntity.ok(juegoActualizadoGuardado);
        } else {
            // Maneja el caso en el que el juego no existe
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/logros/{id}")
    public ResponseEntity<AgendaGamer> actualizarLogros(@PathVariable long id, @RequestBody AgendaGamer juegoActualizado) {
        Optional<AgendaGamer> juegoOptional = bdGamerServ.mostrarJuegoPorId(id);
        if (juegoOptional.isPresent()) {
            AgendaGamer juegoExistente = juegoOptional.get();
            // Actualiza los campos necesarios del juego existente con los valores del juego actualizado
            juegoExistente.setLogros(juegoActualizado.getLogros());
            // ...

            // Guarda los cambios en la base de datos
            AgendaGamer juegoActualizadoGuardado = bdGamerServ.guardarJuego(juegoExistente);
            return ResponseEntity.ok(juegoActualizadoGuardado);
        } else {
            // Maneja el caso en el que el juego no existe
            return ResponseEntity.notFound().build();
        }
    }
}
