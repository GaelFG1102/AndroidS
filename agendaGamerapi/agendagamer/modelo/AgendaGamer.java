package edu.tesji.agendagamer.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AgendaGamer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    private int horasJugadas;

    private int logros;

    public AgendaGamer() {
    }

    public AgendaGamer(String nombre, int horasJugadas, int logros) {
        this.nombre = nombre;
        this.horasJugadas = horasJugadas;
        this.logros = logros;
    }

    public AgendaGamer(long id, String nombre, int horasJugadas, int logros) {
        this.id = id;
        this.nombre = nombre;
        this.horasJugadas = horasJugadas;
        this.logros = logros;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHorasJugadas() {
        return horasJugadas;
    }

    public void setHorasJugadas(int horasJugadas) {
        this.horasJugadas = horasJugadas;
    }

    public int getLogros() {
        return logros;
    }

    public void setLogros(int logros) {
        this.logros = logros;
    }
}
