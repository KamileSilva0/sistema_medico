package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;

public class PlanoDeSaude {
    private String nomePlano;
    private List<String> telefones = new ArrayList<String>();

    public PlanoDeSaude(){
    
    }

    public PlanoDeSaude (String nomePlano, String telefone){
        this.nomePlano = nomePlano;
        this.telefones.add(telefone);

    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public void addTelefone(String telefone){
        this.telefones.add(telefone);
    }

    public void removeTelefone(String telefone) throws Exception{
        if (telefones.size() == 1 ){
            throw new Exception(
             "Exclusão não permitida! O Paciente tem que ter pelo menos um telefone cadastrado.");
        }
        this.telefones.remove(telefone);
    }

    public List<String> getTelefones() {
        return telefones;
    }

    @Override
    public String toString(){
        return this.nomePlano;
    }
}
