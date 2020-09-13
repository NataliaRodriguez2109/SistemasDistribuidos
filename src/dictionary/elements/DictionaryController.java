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

 
    public String action(String order)
    {
        System.out.println("Ingrese la operacion que quiere realizar = "+order);       
        
        String answer = "Indefinido";
        
        switch (order){
            case "agregar":
                term.create();
                answer = "Ok";
                break;
                
            case "listar":
                term.list();
                answer = "Ok";
                break;
                
            case "editar":            
                term.update();
                answer = "Ok";
                break;
                
            case "eliminar":
                term.delete();
                answer = "Ok";
                break;
                
            case "buscar":
                term.find();
                answer = "Ok";
                break;
        }
        
        return answer;
    }
    
    public void setTerm(Term term) {
        this.term = term;
    }
}
