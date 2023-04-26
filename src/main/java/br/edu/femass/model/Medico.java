package br.edu.femass.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Medico {
    private Long id;
    private String crm;
    private String nomeMedico;
    private List<String> telefones = new ArrayList<String>();

    private static Long nextid = 0L;

    public Medico(){
        
    }

    public Medico(String crm, String nomeMedico, String telefone){
        this.crm = crm;
        this.nomeMedico = nomeMedico;
        this.telefones.add(telefone);
        
        this.id = nextid+1;
        nextid++;
    }

    public Long getId() {
        return id;
    }

    public String getCrm() {
        return crm;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void addTelefone(String telefone){
        this.telefones.add(telefone);
    }
    
    public void removeTelefone(String telefone) throws Exception{
        if (telefones.size() == 1 ){
            throw new Exception(
             "Exclusão não permitida! O Médico tem que ter pelo menos um telefone cadastrado.");
        }
        this.telefones.remove(telefone);
    }
    
    public List<String> getTelefones() {
        return telefones;
    }

    @Override
    public String toString(){
        return "Dr. " + this.nomeMedico + " - CRM: " + this.crm;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((crm == null) ? 0 : crm.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Medico other = (Medico) obj;
        if (crm == null) {
            if (other.crm != null)
                return false;
        } else if (!crm.equals(other.crm))
            return false;
        return true;
    }

    public static void atualizarId(Set<Medico> medicos){ // Gravar o útilmo código da lista, mantendo atualizado;
        for(Medico medico : medicos){
            if (medico.getId().longValue()>nextid){
                nextid = medico.getId()+1;
            }
        }
    }
    
}
