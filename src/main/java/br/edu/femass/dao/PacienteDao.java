package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import br.edu.femass.model.Paciente;

public class PacienteDao extends Persistencia implements Dao<Paciente> {
    public PacienteDao() {
        super("pacientes.jason");
    }
    
    public boolean gravar(Paciente objeto) throws StreamWriteException, IOException{
        Set<Paciente> pacientes = mostrar();
        boolean registrou = pacientes.add(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, pacientes);
        return registrou;
    }

    public void excluir(Paciente objeto) throws StreamWriteException, DatabindException, IOException{
        Set<Paciente> pacientes = mostrar();
        pacientes.remove(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, pacientes);
    }

    public Set<Paciente> mostrar() throws DatabindException{
        try{
            Set<Paciente> pacientes = objectMapper.readValue(arquivo, new TypeReference<Set<Paciente>>(){});
            Paciente.atualizarId(pacientes);
            return pacientes;
        } catch (IOException ioException){
            return new HashSet<Paciente>();
        }
    }



    
}
