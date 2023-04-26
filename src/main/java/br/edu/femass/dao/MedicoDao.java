package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Medico;

public class MedicoDao extends Persistencia implements Dao<Medico> {

    public MedicoDao() {
        super("medicos.jason");
    }

    @Override
    public boolean gravar(Medico objeto) throws StreamWriteException, IOException {
        Set<Medico> medicos = mostrar();
        boolean registrou = medicos.add(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
        return registrou;
    }

    @Override
    public void excluir(Medico objeto) throws StreamWriteException, DatabindException, IOException {
        Set<Medico> medicos = mostrar();
        medicos.remove(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
    }

    @Override
    public Set<Medico> mostrar() throws DatabindException {
        try{
            Set<Medico> pacientes = objectMapper.readValue(arquivo, new TypeReference<Set<Medico>>(){});
            Medico.atualizarId(pacientes);
            return pacientes;
        } catch (IOException ioException){
            return new HashSet<Medico>();
        }
    }
    
}
