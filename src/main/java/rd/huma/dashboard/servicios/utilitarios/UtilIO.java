package rd.huma.dashboard.servicios.utilitarios;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UtilIO {

	/**
	 * Copia un inputstrem para outputstream
	 * @param entrada
	 * @param salida
	 * @return cantidad de bytes leidos
	 * @throws IOException
	 *
	 */
	public static long copiar(InputStream entrada,  OutputStream salida)   throws IOException {
        long nread = 0L;
        byte[] buf = new byte[8192];
        int n;
        while ((n = entrada.read(buf)) > 0) {
            salida.write(buf, 0, n);
            nread += n;
        }
        return nread;
    }
}
