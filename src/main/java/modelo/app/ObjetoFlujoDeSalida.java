package modelo.app;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author renemm
 */
public class ObjetoFlujoDeSalida extends ObjectOutputStream{


    public ObjetoFlujoDeSalida(OutputStream salida) throws IOException{
        super(salida);
    }
    
    protected ObjetoFlujoDeSalida() throws IOException, SecurityException{
        super();
    }

    @Override
    public void write(int b) throws IOException {
//        throw new Unsupporte  dOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
