/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.is;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Lucas Nicolini, Juan Vicente Baixauli 
 */
public class P1IS {

    /**
     * @param args the command line arguments
     */
    
       
    /*
    private Motocicleta mo;
    private int opcion, num_motos_poder, cantidad, cilindrada, opcion_moto, opcion_miembro;
    private Acama a;
    private float importe, coste;
    private Scanner teclado = new Scanner (System.in);
    private String nombre, matricula, marca, modelo;
    */
    public static void main(String[] args) throws IOException {
        int  precio_inicial, opcion  , num_motos_poder, cantidad, cilindrada, opcion_moto, opcion_miembro, emisor, receptor, moto;
        Scanner teclado = new Scanner (System.in);
        String nombre, matricula, marca, modelo, fichero;
        boolean ok=false, opmi, opmo;
        float importe, coste, otros_gastos;
        Motocicleta mo;
        Acama a = new Acama();
        
        do
        {
            System.out.print("Introduce precio inicial: ");
            precio_inicial = a.pedirOpcion();
        }
        while(precio_inicial <= 0);
        
        do
        {
            System.out.println("Bienvenido a la aplicacion de ACAMA \n");
            System.out.println("Elige una opcion: \n");
            System.out.println("1. Registrar un nuevo miembro ");
            System.out.println("2. Registrar una nueva motocicleta ");
            System.out.println("3. Registrar una cesión ");
            System.out.println("4. Listar en pantalla los miembros con motos en posesión ");
            System.out.println("5. Listar todas las motos ");
            System.out.println("6. Mostrar las cesiones realizadas ");
            System.out.println("7. Incrementar otros gastos a una moto ");
            System.out.println("8. Eliminar miembro ");
            System.out.println("9. Muestra miembros con mas cesiones ");
            System.out.println("10. Salir del programa \n");
            
            System.out.print("Dime una opcion: ");
            
            opcion = a.pedirOpcion();
            
            
            switch(opcion)
            {
                case 1:
                    Miembro m;
                    
                    System.out.println("Registrar un nuevo miembro \n");
                    System.out.print("Nombre del miembro: ");
                    
                    nombre = teclado.nextLine();

                    m = new Miembro(nombre, 0, 0);
                    a.anyadirMiembro(m);

                    System.out.println("Miembro registrado correctamente \n");
                    break;
                case 2:
                    
                    if(a.sinMotos())
                    {
                        System.out.println("No puedes registrar motos porque no hay miembros \n");
                    }
                    else
                    {
                        System.out.println("Registrar una nueva motocicleta \n");
                   
                        System.out.print("Matricula de la moto: ");
                        matricula = teclado.nextLine();
                        System.out.print("Marca de la moto: ");
                        marca = teclado.nextLine();
                        System.out.print("Modelo de la moto: ");
                        modelo = teclado.nextLine();
                        
                        do
                        {
                        System.out.print("Cilindrada de la moto: ");
                        cilindrada = a.pedirOpcion();
                        }
                        while(cilindrada <= 0);
                        do
                        {
                        System.out.print("Coste de la moto: ");
                        coste = a.pedirOpcionFloat();
                        }
                        while(coste <= 0);      
                        
                        do
                        {
                            System.out.print("Coste de otros gastos: ");
                            otros_gastos = a.pedirOpcionFloat();
                        }
                        while(otros_gastos <= 0);
                        System.out.println("");                        
                        
                        mo = new Motocicleta(matricula, marca, modelo, cilindrada, coste, otros_gastos);
                        if(!(coste > precio_inicial || coste < 0))
                        {
                            System.out.println("Quien va a tener en posesion la moto? \n");

                            do
                            {
                                do
                                {
                                    System.out.println(a.mostrarMiembros());
                                    opcion_miembro = a.pedirOpcion();
                                }
                                while(opcion_miembro <= 0);
                                
                                opmi = a.comprobarOpcionMiembro(opcion_miembro-1);
                                    if(opmi)
                                        ok = a.comprobarCoste(a.getMiembros().get(opcion_miembro-1), coste, precio_inicial);
                            }
                            while(!opmi && !ok);



                            if(ok)
                            {
                                a.anyadirMotoMiembro(mo, opcion_miembro-1);
                                a.cambiarImporte(mo, opcion_miembro-1);
                                a.cambiarNumMotos(opcion_miembro-1);
                                System.out.println("Motocicleta registrada correctamente \n");
                            }
                        }
                        else
                        {
                            System.out.println("El precio de la moto es mayor que el maximo permitido o negativo \n");
                        }
                        
                    }
                    
      
                    break;
                    
                case 3:
                    
                    System.out.println("Ceder motocicleta \n");
                                        
                    if(a.getMiembros().size()>1)
                    {
                        do
                        { 
                            System.out.println(a.mostrarMiembros());
                            System.out.print("Elige el miembro emisor: ");
                            emisor = a.pedirOpcion();
                            opmi = a.comprobarOpcionMiembro(emisor-1);
                        }
                        while(!opmi);
                        
                        if(a.mirarEmisor(emisor-1))
                        {
                            do
                            {
                                System.out.println(a.mostrarReceptores(emisor-1));
                                
                                do
                                {
                                    System.out.print("Elige el miembro receptor: ");
                                    receptor = a.pedirOpcion();
                                }
                                while(receptor == 0);
                                
                                opmi = a.comprobarOpcionMiembro(receptor-1);
                                
                                if(emisor == receptor)
                                    System.out.println("El receptor que ha indicado no existe");
                            }
                            while(!opmi || emisor == receptor);
                            
                            do
                            {
                                System.out.println(a.mostrarMotoMiembro(emisor-1));
                                boolean okm;
                                do
                                {
                                    System.out.print("Elige la moto que quieres ceder: ");
                                    moto = a.pedirOpcion();
                                    okm = a.comprobarMoto(emisor-1, moto);
                                    
                                    if(!okm)
                                        System.out.println("Ese miembro no tiene esa moto ");
                                }
                                while(moto <= 0 || !okm);
                                
                            }
                            while( moto <= 0);
                            
                            if(a.comprobarCoste(a.getMiembros().get(receptor-1), a.getMiembros().get(emisor-1).getMotos().get(a.buscarMotoMiembro(a.getMiembros().get(emisor-1), moto-1)).getCoste(),precio_inicial))
                            {
                                int pos = a.buscarMotoMiembro(a.getMiembros().get(emisor-1), moto-1);
                                a.cambioMoto(emisor-1, receptor-1, pos);
                            }

                        }
                        else
                        {
                            System.out.println("Ese emisor no tiene ninguna moto en poder, elige alguien que tenga una moto en poder \n");
                        }
                    }
                    else
                    {
                        System.out.println("No hay suficientes miembros para realizar una cesion \n");
                    }
                    

                    break;
                    
                case 4:
                    
                    System.out.println("Listar todos los miembros con motos \n");
                    if(a.getMiembros().size()>0)
                    {
                        System.out.println("Los miembros son: ");
                        a.miembroConMoto();
                        System.out.println("\n");
                    }
                    else
                        System.out.println("No hay miembros en la lista. \n");
                    break;
                    
                case 5:
                    
                    System.out.println("Listar todas las motos \n");
                    if(a.getMotos()>0)
                    {
                        System.out.println("Las motos son: ");
                        System.out.println(a.mostrarMotos());
                    }
                    else
                        System.out.println("No hay motos en la lista. \n");
                    break;
                    
                case 6:
                    
                    System.out.println("Listar las cesiones \n");
                    System.out.println("Las cesiones son: ");
                    System.out.println(a.mostrarCesiones());
                    break;
                    
                case 7:
                    System.out.print("Incrementar los gastos de una moto. \n ");
                    System.out.println(a.mostrarMotos());
                    
                    System.out.print("Que moto quieres incrementar los gastos: ");
                    int p= a.pedirOpcion();
                    
                    System.out.print("Cuanto quieres incrementar los gastos:  ");
                    float g = a.pedirOpcionFloat();                    
 
                    a.incrementarGastos(p, g);
                    
                    break;
                    
                case 8:
                    System.out.println("Eliminar miembro. ");
                    System.out.print(a.mostrarMiembros());
                    System.out.print("¿Que miembro quieres eliminar?:  ");
                    int eliminar;
                    do
                    {
                        eliminar = a.pedirOpcion();
                    }
                    while(eliminar <= 0);
                    
                    if(a.comprobarOpcionMiembro(eliminar-1))
                            a.eliminarMiembro(eliminar, precio_inicial);
                    
                    break;
                 
                case 9:
                    System.out.print("Miembros con mas cesiones \n");
                    a.masCesiones();
                    
                    break;
                    
                case 10:
                    System.out.print("Como quieres llamar al archivo?: ");
                    fichero = teclado.nextLine();
                    
                    a.guardar(fichero);
                    System.out.println("Hasta pronto!");
                    break;  
                    
                default:
                    
                    System.out.println("Opcion no válida, elige una de las que sale en el menú \n");
                    break;
            }
        
        }

        while(opcion != 10);  
    }   
}



