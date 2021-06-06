
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lendoxlsx.LerTabelaPdf;
import model.ClientePDF;
import org.apache.commons.lang3.ArrayUtils;


public class Principal {

    public static void main(String[] args) {
        LerTabelaPdf planilhaPDF = new LerTabelaPdf();
        ArrayList<ClientePDF> clientesPDF = new ArrayList<>();
    
        ArrayList<ClientePDF> aux;
        try {
            //aux = planilhaPDF.lerDados("C:\\Users\\NOTE_190\\OneDrive\\Documentos\\xlsx\\planilhas\\ClientesSergio1.xlsx");
            //clientesPDF.addAll(aux);
    
            //aux = planilhaPDF.lerDados("C:\\Users\\NOTE_190\\OneDrive\\Documentos\\xlsx\\planilhas\\ClientesSergio2.xlsx");
            //clientesPDF.addAll(aux);
        
            aux = planilhaPDF.lerDados("C:\\Users\\NOTE_190\\Downloads\\Pasta de trabalho 1.xlsx");
            clientesPDF.addAll(aux);
        
            //aux = planilhaPDF.lerDados("C:\\Users\\NOTE_190\\OneDrive\\Documentos\\xlsx\\planilhas\\ClientesSergio4.xlsx");
            //clientesPDF.addAll(aux);
        
            //aux = planilhaPDF.lerDados("C:\\Users\\NOTE_190\\OneDrive\\Documentos\\xlsx\\planilhas\\ClientesSergio5.xlsx");
            //clientesPDF.addAll(aux);
   
            System.out.printf("Tamanho: " + clientesPDF.size() );
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
