/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverYCliente;

/**
 *
 * @author brais.fernandezvazqu
 */
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.InetAddress;

public class Servidor {

    public static void main(String[] args) {
        byte[] mensaje;
        InputStream flujoLectura;
        DataInputStream flujo;
        try {
            ServerSocket socketServidor = new ServerSocket(8000);
            Socket comunicaCliente = socketServidor.accept();
            System.out.println("Comunicación establecida");
            //leer datos mensaje
            flujoLectura = comunicaCliente.getInputStream();
            flujo = new DataInputStream(flujoLectura);
            int i = flujo.readInt();
            mensaje = new byte[i];
            flujo.readFully(mensaje);
            System.out.println(new String(mensaje));
            //Crear inet a partir de socket
            InetAddress addr=comunicaCliente.getLocalAddress();
            //Imprimir por consola ip y FQDN
            String texto=addr.getHostAddress()+" "+addr.getCanonicalHostName();
            texto=texto.concat(" Brais Fernández");
            System.out.println(texto);
            //cerrar 
            comunicaCliente.close();
            socketServidor.close();
        } catch (IOException e) {
            System.out.println("Error en las comunicaciones");
            System.exit(0);
        } catch (SecurityException e) {
            System.out.println("Comunicacion no permitida por razones de seguridad");
            System.exit(0);
        }
    }
}

