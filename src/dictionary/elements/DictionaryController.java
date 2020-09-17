/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.elements;

/**
 *
 * @author nata_
 */
public class DictionaryController {
    private Term term;

    public DictionaryController() {
        term = new Term();
    }

 
    public String action(String order, String ip)
    {
        
        System.out.println("Ingrese la operacion que quiere realizar = "+order);       
        
        String answer = "Indefinido";
        
        switch (order){
            case "agregar":
                term.create(ip);
                answer = "Ok";
                break;
                
            case "listar":
                term.list(ip);
                answer = "Ok";
                break;
                
            case "editar":            
                term.update(ip);
                answer = "Ok";
                break;
                
            case "eliminar":
                term.delete(ip);
                answer = "Ok";
                break;
                
            case "buscar":
                answer = term.find(ip);                
                break;
        }
        
        return answer;
    }
    
    public void setTerm(Term term) {
        this.term = term;
    }
}
