/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Servidor {

    public static void main(String[] args) {
        HashMap<String, String> terminos = new HashMap<>();
        terminos.put("java", "Es un lenguaje de programación");
        terminos.put("String", "Cadena de caracteres");
        terminos.put("int", "Valor numerico");
        terminos.put("cpu", "La unidad central de procesamiento");
        terminos.put("ram", "Random Access Memory");
        terminos.put("netbeans", "Es un entorno de desarrollo integrado de código abierto y gratuito");
        terminos.put("python", "Es un lenguaje de programación");
        terminos.put("Html", "HyperText Markup Language (‘lenguaje de marcado de hipertexto’)");
        terminos.put("brave", "Brave es un navegador web");
        terminos.put("pc", "Un ordenador personal, computador personal u ordenador, conocido como PC");
        terminos.put("teclado", "Es un dispositivo de entrada");
        terminos.put("mouse", "Es un dispositivo apuntador");
        try {
            //Backload es el limite de conecciones

            int puerto = 6789;
            //int puerto= 55464;
            //Se crea el socket para poder receptar conexiones del cliente
            Socket cliente_conexion;//Socket
            DataOutputStream salida;//Enviar datos
            DataInputStream entrada;//Recibir datos
            System.out.println("Esperando clientes en el puerto:" + puerto);
            ServerSocket socketTCP = new ServerSocket(puerto);
            cliente_conexion = socketTCP.accept();//Se acepta la conexion con el cliente
            salida = new DataOutputStream(cliente_conexion.getOutputStream());//Se usa la conexion con el cliente para enviar datos
            salida.writeUTF("Solicitud aceptada!!");
            System.out.println("Cliente :" + cliente_conexion.getInetAddress().getHostName());

            entrada = new DataInputStream(cliente_conexion.getInputStream());

            while (true) {
                //Receptar y leer el mensaje enviado por el cliente
                String mensaje = entrada.readUTF().toLowerCase().trim();
                System.out.println("El cliente ha enviado el siguiente mensaje: " + mensaje);

                String resultado = "Este termino no esta almacenado en el Hashmap";
                for (String clave : terminos.keySet()) {
                    if (clave.equals(mensaje)) {
                        resultado = clave + ": " + terminos.get(clave);
                    }
                }
                salida.writeUTF(resultado);
                System.out.println("Resultado enviado: \"" + resultado +" \" ");
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
