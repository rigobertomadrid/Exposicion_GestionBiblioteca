package exposicion_gestionbibliotecas;

import java.io.*;

public class SerializacionUtil {

    public static void guardarObjeto(Object objeto, String rutaArchivo) throws Exception {
        File archivo = new File(rutaArchivo);
        File directorio = archivo.getParentFile();  

        if (directorio != null && !directorio.exists()) {
            directorio.mkdirs();
        }

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            salida.writeObject(objeto);
        }
    }

    public static Object cargarObjeto(String rutaArchivo) throws Exception {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return entrada.readObject();
        }
    }
}
