package br.edu.femass.model;

import java.util.Set;

public class Especialidade {
    private String nomeEspecialiade;
    private Long codEspecialidade;

    private static Long nextcod = 0L;

    public Especialidade() {
    }

    public Especialidade(String nomeEspecialidade){
        this.nomeEspecialiade = nomeEspecialidade;

        this.codEspecialidade = nextcod+1;
        Especialidade.nextcod++;
    }

    public String getNomeEspecialiade(){
        return nomeEspecialiade;
    }

    public Long getCodEspecialidade() {
        return codEspecialidade;
    }
    
    @Override
    public String toString(){
        return this.nomeEspecialiade;
    }

    public static void atualizarCod(Set<Especialidade> especialidades){ // Gravar o útilmo código da lista, mantendo atualizado;
        for(Especialidade especialidade : especialidades){
            if (especialidade.getCodEspecialidade().longValue()>nextcod){
                nextcod = especialidade.getCodEspecialidade()+1;
            }
        }
    }
    
}

