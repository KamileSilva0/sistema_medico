package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MedicoTest {
    
    @Test
    void construtorTest(){
        Medico medico = new Medico("0024", "Dr. Test", "22 99999-9999");
        assertEquals(1, medico.getId());
    }

    @Test
    void nextIdTest(){
        Medico medico1 = new Medico("0024", "Dr. Test", "22 99999-9999");
        Medico medico2 = new Medico("3256", "Dr. Test", "22 99999-9999");
        assertEquals(1, medico1.getId());
        assertEquals(2, medico2.getId());
    }

}
    
    
