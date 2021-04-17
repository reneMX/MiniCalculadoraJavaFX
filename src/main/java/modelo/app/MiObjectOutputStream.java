package modelo.app;

/**
 * Javier Abell�n, 20 Marzo 2006 Redefinicion de la clase ObjectOutputStream
 * para que no escriba una cabecera al principio del Stream.
 */
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Redefinición de la clase ObjectOuputStream para que no escriba una cabecera
 * al inicio del Stream.
 *
 * @author String MX.
 *
 */
public class MiObjectOutputStream extends ObjectOutputStream {

    /**
     * Constructor que recibe OutputStream
     */
    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);//constructor padre
    }

    /**
     * Constructor sin par�metros
     */
    protected MiObjectOutputStream() throws IOException, SecurityException {
        super();
    }

    /**
     * Redefinición del método de escribir la cabecera para que no haga nada.
     */
    protected void writeStreamHeader() throws IOException {
        //NO HACER NADA
        // do not write a header, but reset:
        // this line added after another question
        // showed a problem with the original
        //reset();        
    }

}
