/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloomfilters;

/**
 *
 * @author ifons
 */
public class BloomFilters {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BloomFilter a= new BloomFilter(10,.3);
        
        a.insertar(3);
        a.insertar(5);
        a.insertar(15);
        a.insertar(543);
        
        System.out.println(a.buscar(2));
        System.out.println(a.buscar(15));
    }
    
}
