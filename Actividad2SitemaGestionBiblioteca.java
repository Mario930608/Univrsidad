

package com.mycompany.actividad2sitemagestionbiblioteca;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class Actividad2SitemaGestionBiblioteca {

    public static void main(String[] args) {
        
        ArrayList<String[]> libros = new ArrayList<>();
        LinkedList<String[]> usuarios = new LinkedList<>();
        Stack<String[]> librosPrestados = new Stack<>();
        Queue<String[]> colaEspera = new LinkedList<>();
        
        Scanner entrada = new Scanner(System.in);
        
        int opcion;
        do {
            System.out.println("==============================================");
            System.out.println("    SISTEMA DE GESTION DE BIBLIOTECAS");
            System.out.println("==============================================");
            System.out.print("Mario Alejandro Artunduaga"+"1032458274");
            System.out.println("1. Agregar un libro");
            System.out.println("2. Registrar un usuario");
            System.out.println("3. prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros disponibles");
            System.out.println("6. Mostrar usuarios registrados");
            System.out.println("7. Salir");
            System.out.println("Seleccione una Opcion: ");
            
            
            while(!entrada.hasNextInt()){
                System.out.println("Error: Ingrese un numero valido!!");
                entrada.next();
                System.out.println("Seleccione una opcion: ");
            }    
           
            opcion = entrada.nextInt();
            entrada.nextLine();
            switch(opcion){
                case 1:
                    System.out.println("Ingrese el ID del libro (unico) ");
                    String idLibro = entrada.nextLine();
                    boolean idDuplicado= false;
                    for(String[] libro: libros){
                        if(libro[0].equals(idLibro)){
                            idDuplicado = true;
                            break;
                        }
                    }
                    if(idDuplicado){
                        System.out.println("Error: El ID del libro ya existe!!!");
                    }else{
                        System.out.println("Ingres el nombre del libro: ");
                        String nombreLibro = entrada.nextLine();
                        System.out.println("Ingres el autor del libro: ");
                        String autorLibro = entrada.nextLine();
                        libros.add(new String[]{idLibro, nombreLibro, autorLibro});
                        System.out.println("Libro agregado con exitosamente");
                    }

                    
                    break;
                case 2:
                    System.out.println("Ingrese la cedula del usuario (solo numeros): ");
                    
                while(!entrada.hasNextInt()){
                    System.out.println("Error: Ingrese un numero valido!!");
                    entrada.next();
                    System.out.println("Ingresa la cedula del usuario (SOLO NUMEROS!!): ");
                }
                int cedulaUsuario = entrada.nextInt ();
                entrada.nextLine();
                    System.err.println("Ingrese el nombre Id usuario");
                    String nombreUsuario = entrada.nextLine();
                    System.out.println("Ingrese los apellidos  del usuario: ");
                    String apellidosUsuario = entrada.nextLine();
                    
                    boolean cedulaDuplicado= false;
                    for(String[] usuario: usuarios){
                        if(usuario[0].equals(String.valueOf(cedulaUsuario))){
                            cedulaDuplicado = true;
                            break;
                        }
                    }
                    if(cedulaDuplicado){
                        System.out.println("Error: El usuario ya existe!!!");
                    }else{
                            usuarios.add(new String[]{String.valueOf(cedulaUsuario), nombreUsuario, apellidosUsuario});
                            System.out.println("Usuario registrado exitosamente!!!");
                    }
  
                    break;
                case 3:
                    System.out.println("Ingrese el Id del libro requerido: ");
                    String idSolicitar = entrada.nextLine();
                    System.out.println("Ingrese la cedula del Usuario que Solicita: ");
                    while(!entrada.hasNextInt()){
                    System.out.println("Error: Ingrese un numero valido!!");
                    entrada.next();
                    System.out.println("Ingresa la cedula del usuario (SOLO NUMEROS!!): ");
                } 
                int cedulaSolicitar = entrada.nextInt();
                entrada.nextLine();
                
                boolean usuarioRegistrado = false;
                for (String[] usuario:usuarios){
                    if(usuario[0].equals(String.valueOf(cedulaSolicitar))){
                        usuarioRegistrado = true;
                        break;
                    }
                    if (!usuarioRegistrado){
                        System.out.println("Error el usuario con cedula: "+cedulaSolicitar + "No se puede realizar el prestamo" );
                }else{
                boolean libroEncontrado= false;
                    for(String[] libro: libros){
                        if(libro[0].equals(idSolicitar)){
                            librosPrestados.push(new String[]{idSolicitar, String.valueOf(cedulaSolicitar)});
                            libros.remove(libro);
                            libroEncontrado = true;
                            System.out.println("Libro prestado con exito!!!");
                            break;
                        }
                    }
                    if (!libroEncontrado){
                        System.out.println("Libro no disponible. desea agregar a la cola de espera? (si/no)");
                        String respuesta = entrada.nextLine();
                        if(respuesta.equalsIgnoreCase("si")){
                            colaEspera.add(new String[]{idSolicitar, String.valueOf(cedulaSolicitar)});
                            System.out.println("agregado a la cola de espera");
                        }
                    }
                }
                    
                    break;
                case 4:
                    if (!librosPrestados.isEmpty()){
                        String[]libroDevuelto = librosPrestados.pop();
                        libros.add(new String[] {libroDevuelto[0], libroDevuelto[1], libroDevuelto[2]});
                        System.out.println("Libro devuelto exitosamente");
                                }
                    if(!colaEspera.isEmpty()){
                        String[] proximosEnCola = colaEspera.poll();
                        System.out.println("El usuario con cedula "+proximosEnCola [1] + "esta en cola y ahora se prestara con ID: "+ proximosEnCola [0]);
                    librosPrestados.push(proximosEnCola);
                    }else{
                        System.out.println("No hay libros prestados");
                    }
                    break;
                case 5:
                    if(libros.isEmpty()){
                        System.out.println("No hay libros disponibles");
                    }else{
                        System.out.println("----Libros Disponibles----");
                        System.out.printf("%-10s %-20s %-20s%n", "ID", "Nombre", "Autor");
                        for(String[] libro: libros){
                            System.out.printf("%-10s %-20s %-20s%n", libro [0], libro[1], libro[2]);
                        }
                    }
                    break;
                case 6: 
                    if(usuarios.isEmpty()){
                        System.out.println("No hay usuarios disponibles");
                    }else{
                        System.out.println("----Usuarios Disponibles----");
                        System.out.printf("%-10s %-15s %-20s%n", "Cedula", "Nombre", "Apellidos");
                        for(String[] usuario: usuarios){
                            System.out.printf("%-10s %-15s %-20s%n", usuario [0], usuario[1], usuario[2]);
                        }
                    }
                    break;
                case 7:
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente");
                    break;
                while (opcion !=7);
            }
        }

