package br.edu.femass.dao;

import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public interface Dao<T> {

    public boolean gravar(T objeto) throws StreamWriteException, IOException;

    public void excluir(T objeto) throws StreamWriteException, DatabindException, IOException;

    public Set<T> mostrar() throws DatabindException;
    
}
