package br.edu.femass.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta {
    private String sala;
    private LocalDateTime data;
    private LocalDateTime hora;

    public Consulta(){
    }
    

    public Consulta(String sala){
        this.sala = sala;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public String getSala() {
        return sala;
    }

    @Override
    public String toString(){
    return 
           "Data: " + this.data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) 
            + "- Hora: " + this.hora.format(DateTimeFormatter.ofPattern("HH:mm"))
            + "- Sala: " + this.sala;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sala == null) ? 0 : sala.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((hora == null) ? 0 : hora.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Consulta other = (Consulta) obj;
        if (sala == null) {
            if (other.sala != null)
                return false;
        } else if (!sala.equals(other.sala))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (hora == null) {
            if (other.hora != null)
                return false;
        } else if (!hora.equals(other.hora))
            return false;
        return true;
    }



    
}
