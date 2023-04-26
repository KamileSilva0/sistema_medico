package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.edu.femass.utils.Validador;

public class Paciente {
    private String nomePacienete;
    private String idade;
    private String cpf;
    private Long id;
    private String endereco;
    private String email;
    private List<String> telefones = new ArrayList<String>();

    private static Long nextid = 0L;

    public Paciente(){
        //Atender os requisitos do Json;
    }

    public Paciente(String cpf, String nomePaciente, String idade, String telefone){
        if(Validador.validarCPF(cpf)==false) throw new IllegalArgumentException("CPF Inválido!");
        this.cpf = cpf;
        this.nomePacienete = nomePaciente;
        this.idade = idade;
        this.telefones.add(telefone);

        this.id = nextid+1;
        nextid++;
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNomePacienete() {
        return nomePacienete;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return this.nomePacienete + ", " + this.idade + " anos";
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
        Paciente other = (Paciente) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    public static void atualizarId(Set<Paciente> pacientes){ // Gravar o útilmo código da lista, mantendo atualizado;
        for(Paciente paciente : pacientes){
            if (paciente.getId().longValue()>nextid){
                nextid = paciente.getId()+1;
            }
        }
    }
}
