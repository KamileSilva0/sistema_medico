package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Especialidade;

public class EspecialidadeDao extends Persistencia implements Dao<Especialidade> {

    public EspecialidadeDao() {
        super("especialidades.jason");
    }
    @Override
    public boolean gravar(Especialidade objeto) throws StreamWriteException, IOException {
        Set<Especialidade> especialidades = mostrar();
        boolean registrou = especialidades.add(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, especialidades);
        return registrou;
    }

    @Override
    public void excluir(Especialidade objeto) throws StreamWriteException, DatabindException, IOException {
        Set<Especialidade> especialidades = mostrar();
        especialidades.remove(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, especialidades);
    }

    @Override
    public Set<Especialidade> mostrar() throws DatabindException {
        try{
            Set<Especialidade> especialidades = objectMapper.readValue(arquivo, new TypeReference<Set<Especialidade>>(){});
            Especialidade.atualizarCod(especialidades);
            return especialidades;
        } catch (IOException ioException){
            return new HashSet<Especialidade>();
        }
       
    }
    
}
