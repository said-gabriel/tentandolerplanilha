package model;

import java.util.ArrayList;

public class ClientePlanilha {
    ArrayList<String> telefones;
    private String contato;

    public ClientePlanilha(){
    
}
    public ClientePlanilha( ArrayList<String> telefones, String contato) {
        this.telefones = telefones;
        this.contato = contato;
    }

    public ArrayList<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(ArrayList<String> telefones) {
        this.telefones = telefones;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
    
    
}
