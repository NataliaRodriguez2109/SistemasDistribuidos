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

 
    public String action(Paquete order, String ip)
    {
        
        System.out.println("Ingrese la operacion que quiere realizar = "+order.arreglo[0]);       
        
        String answer = "Indefinido";
        
        switch (order.arreglo[0]){
            case "crear":
                answer = term.create(ip, order.arreglo[1], order.arreglo[2]);                 
                break;
                
            case "listar":
                answer =term.list(ip);                 
                break;
                
            case "editar":            
                answer =term.update(ip, order);                 
                break;
                
            case "eliminar":
                answer =term.delete(ip, order);                 
                break;
                
            case "buscar":
                answer = term.find(ip, order, ip);                
                break;
        }
        
        return answer;
    }
    
    public void setTerm(Term term) {
        this.term = term;
    }
}
