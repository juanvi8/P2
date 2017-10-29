/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.is;

/**
 *
 * @author Lucas Nicolini, Juan Vicente Baixauli
 */
public class Cesion {
    private Miembro emisor, receptor;
    private Motocicleta moto;
    private static int id = 1;
    private int id_cesion;
    
    public Cesion(Miembro _emisor, Miembro _receptor, Motocicleta _moto)
    {
        emisor = _emisor;
        receptor = _receptor;
        moto = _moto;
        id_cesion = id;
        id++;
    }
    
    public String mostrarCesion()
    {
        if(id_cesion < 10)
            return("La cesion numero 00" + id_cesion + " el emisor " + emisor.getNombre() + " con id " + emisor.getId_miembro() + " le ha cedido la moto " + moto.getMarca() + " " + moto.getModelo()  + " con matricula " + moto.getMatricula() + " al receptor " + receptor.getNombre() + " con id " + receptor.getId_miembro());
        else if(id_cesion < 100)
            return("La cesion numero 0" + id_cesion + " el emisor " + emisor.getNombre() + " con id " + emisor.getId_miembro() + " le ha cedido la moto " + moto.getMarca() + " " + moto.getModelo()  + " con matricula " + moto.getMatricula() + " al receptor " + receptor.getNombre() + " con id " + receptor.getId_miembro());
        else
            return("La cesion numero " + id_cesion + " el emisor " + emisor.getNombre() + " con id " + emisor.getId_miembro() + " le ha cedido la moto " + moto.getMarca() + " " + moto.getModelo()  + " con matricula " + moto.getMatricula() + " al receptor " + receptor.getNombre() + " con id " + receptor.getId_miembro());
    }
}
