/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr7app;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author ivan
 */
public class Pr7app {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, IllegalStateException, FTPIllegalReplyException, FTPException, FileNotFoundException, FTPDataTransferException, FTPAbortedException {
        // Conexion y descarga de archivo

        URL url = new URL("http://192.168.122.200/docs/ic10-m04-WindowsServer.pdf"); //Creamos la clase URL. Le pasamos la url de donde descargar el archivo.
        url.openConnection(); //Abrimos la conexión.
        InputStream reader = url.openStream(); //Creamos un stream de entrada de donde podrá leer.
        
        FileOutputStream writer = new FileOutputStream("/home/jordi/Escritorio/jordibravomendez.pdf"); //Creamos un fichero de salida donde guardar lo que nos bajamos. 
        
        byte[] buffer = new byte[153600]; //Creamos el intermedio que leerá los datos de la url.
        int bytesRead = 0;
        
        while ((bytesRead = reader.read(buffer)) > 0) { //Mientras vaya leyendo y los bytes sean menores a 0, escribimos en el fichero.
            writer.write(buffer, 0, bytesRead);
        }
        
        //Cerramos la escritura y la lectura.
        writer.close();
        reader.close();
        
        
        //FTP
        
        FTPClient client = new FTPClient(); //Creamos un nuevo cliente FTP.
        
        client.connect("srv.toca.cat", 21); //Decimos donde tenemos que conectarnos. Importante no poner ftp://.
        
        client.login("fulano", "Platano123"); //Pasamos usuario y password.
        
        client.upload(new java.io.File("/home/jordi/Escritorio/jordibravomendez.pdf")); //Subimos el archivo a la conexion ya establecida.

        client.disconnect(true); //Desconectamos.
    }

}
