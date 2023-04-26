package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.PlanoDeSaude;

public class PlanoDeSaudeDao extends Persistencia implements Dao<PlanoDeSaude>{

    public PlanoDeSaudeDao() {
        super("planos.jason");
    
    }

    @Override
    public boolean gravar(PlanoDeSaude objeto) throws StreamWriteException, IOException {
        Set<PlanoDeSaude> planos = mostrar();
        boolean registrou = planos.add(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
        return registrou;
    }

    @Override
    public void excluir(PlanoDeSaude objeto) throws StreamWriteException, DatabindException, IOException {
        Set<PlanoDeSaude> planos = mostrar();
        planos.remove(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
    }

    @Override
    public Set<PlanoDeSaude> mostrar() throws DatabindException {
        try{
            Set<PlanoDeSaude> planos = objectMapper.readValue(arquivo, new TypeReference<Set<PlanoDeSaude>>(){});
            return planos;
        } catch (IOException ioException){
            return new HashSet<PlanoDeSaude>();
        }
    }
    
}
