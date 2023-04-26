package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Consulta;


public class ConsultaDao extends Persistencia implements Dao<Consulta> {

    public ConsultaDao() {
        super("consultas.jason");
        
    }

    @Override
    public boolean gravar(Consulta objeto) throws StreamWriteException, IOException {
        Set<Consulta> consultas = mostrar();
        boolean registrou = consultas.add(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, consultas);
        return registrou;
    }

    @Override
    public void excluir(Consulta objeto) throws StreamWriteException, DatabindException, IOException {
        Set<Consulta> consultas = mostrar();
        consultas.remove(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, consultas);
    }

    @Override
    public Set<Consulta> mostrar() throws DatabindException {
        try{
            Set<Consulta> consultas = objectMapper.readValue(arquivo, new TypeReference<Set<Consulta>>(){});
            return consultas;
        } catch (IOException ioException){
            return new HashSet<Consulta>();
        }
    }
    
}
