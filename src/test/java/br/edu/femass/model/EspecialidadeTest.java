package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EspecialidadeTest {

    @Test
    void construtorTest(){
        Especialidade especialidade = new Especialidade("Dermatologista");
        assertEquals(1, especialidade.getCodEspecialidade());
    }

    @Test
    void nextcodTest(){
        Especialidade especialidade1 = new Especialidade("Dermatologista");
        Especialidade especialidade2 = new Especialidade("Cardiologista");
        assertEquals(1, especialidade1.getCodEspecialidade());
        assertEquals(2, especialidade2.getCodEspecialidade());
    }
    
}
