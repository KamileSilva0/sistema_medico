package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PacienteTest {
    
    @Test
    void construtorTest(){
        Paciente paciente = new Paciente(
            "05812672067", 
            "Test",
            "25", 
            "22 99999-9999");
            assertEquals(1, paciente.getId());
    }

    @Test
    void nextIdTest(){
        Paciente paciente1 = new Paciente(
            "05812672067", 
            "Test", 
            "25",
            "22 99999-9999");

            Paciente paciente2 = new Paciente(
            "45867860000", 
            "Test",
            "25", 
            "22 99999-9999");
            
            assertEquals(1, paciente1.getId());
            assertEquals(2, paciente2.getId());
    }

    @Test
    void cpfInvalidoTest(){
        assertThrows(IllegalArgumentException.class,
            () -> new Paciente(
                "123456789", 
                "Test", 
                "25",
                "22 99999-9999")
        );    
    }

    @Test
    void addTelefoneTest(){
        Paciente paciente = new Paciente(
            "05812672067", 
            "Test",
            "25", 
            "22 99999-9999");

            assertEquals(1, paciente.getTelefones().size());
    }

    @Test
    void addMaisTelefonesTest(){
        Paciente paciente = new Paciente(
            "05812672067", 
            "Test",
            "25",
            "22 99999-9999");

            paciente.addTelefone("22 92345-6789");
            assertEquals(2, paciente.getTelefones().size());
    }

    @Test
    void removeTelefoneTest() throws Exception{
        Paciente paciente = new Paciente(
            "05812672067", 
            "Test", 
            "25",
            "22 99999-9999");

            assertThrows(Exception.class,
                       () -> paciente.removeTelefone("22 99999-9999")
            );
    }

    @Test
    void removeTelefoneTendoMaisDeUmTest() throws Exception{
        Paciente paciente = new Paciente(
            "05812672067", 
            "Test", 
            "25",
            "22 99999-9999");
            
            paciente.addTelefone("22 93654-9874");
            paciente.removeTelefone("22 99999-9999");

            assertEquals(1, paciente.getTelefones().size());


    }
}
