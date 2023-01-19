/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Cliente {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            int puerto = 6789;
            String host = "localhost";
            DataInputStream entrada;//Creacion de la variable que receptara los datos
            DataOutputStream salida;//Para enviar mensaje al servidor
            Socket socketTCP = new Socket(host, puerto);//Se crea socket que enviara una peticion de conexion al servidor
            entrada = new DataInputStream(socketTCP.getInputStream());//Se declara la variable entrada
            System.out.println(entrada.readUTF());//lectura de los datos
            while (true) {

                System.out.print("Ingrese un mensaje: ");
                String mensaje;
                mensaje = sc.nextLine();
                salida = new DataOutputStream(socketTCP.getOutputStream());//
                salida.writeUTF(mensaje);//Se envia el mensaje al servidor

                System.out.println(entrada.readUTF());//lectura de los datos

            }

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
