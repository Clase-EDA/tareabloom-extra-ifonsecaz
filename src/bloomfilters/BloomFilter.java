/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloomfilters;

import java.util.Random;

/**
 *
 * @author ifons
 */
public class BloomFilter <T>{
    private int[] datos;
    private int[] semillas;
    private int cont;
    private double prob;
    private int numHash;
    
    public BloomFilter(int numDatos, double prob){
        this.prob=prob;
        datos=new int[tamanoIdeal(numDatos,prob)];
        numHash=numHash(numDatos);
        semillas= new int[numHash];
        Random r = new Random(System.currentTimeMillis());
        for(int i=0; i<numHash; i++){
            semillas[i]=r.nextInt();
        }
    }
    
    public void insertar(int dato){
        cont++;
        byte[] d = new byte[]{
            (byte)(dato >>> 24),
            (byte)(dato >>> 16),
            (byte)(dato >>> 8),
            (byte)(dato)
        };
        for (int i=0; i<numHash; ++i) {
            int h = MurmurHash.hash32(d, 4, semillas[i]);
            datos[Math.abs(h)%datos.length]=1;
        }
    }
    
    public boolean buscar(int dato){
        boolean res=true;
        byte[] d = new byte[]{
            (byte)(dato >>> 24),
            (byte)(dato >>> 16),
            (byte)(dato >>> 8),
            (byte)(dato)
        };
        int i=0;
        while(res && i<numHash){
            int h = MurmurHash.hash32(d, 4, semillas[i]);
            if(datos[Math.abs(h)%datos.length]!=1)
                res=false;
            i++;
        }
        return res;
    }
    
    private int tamanoIdeal(int numDatos, double prob){
        int m;
        
        m=(int) -((numDatos*Math.log(prob))/Math.pow(Math.log(2),2));
        
        return m;
    }
    
    private int numHash(int num){
       int k=(int) ((datos.length/num)*Math.log(2));
       
       return k;
    }
}
