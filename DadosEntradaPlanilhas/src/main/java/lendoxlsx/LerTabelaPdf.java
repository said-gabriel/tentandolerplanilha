package lendoxlsx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ClientePDF;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LerTabelaPdf {

    public LerTabelaPdf() {
    }

    public ArrayList<ClientePDF> lerDados(String path) throws FileNotFoundException {

        FileInputStream fisPlanilha = null;
        ArrayList<ClientePDF> clientes = new ArrayList<>();
        try {
            File file = new File(path);
            fisPlanilha = new FileInputStream(file);

            //cria um workbook = planilha toda com todas as abas
            XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha);

            //recuperamos apenas a primeira aba ou primeira planilha
            XSSFSheet sheet = workbook.getSheetAt(0);
            //retorna todas as linhas da planilha i (aba 1)
            Iterator<Row> rowIterator = sheet.iterator();

            //varre todas as linhas da planilha i
            while (rowIterator.hasNext()) {
                String[] dados = new String[5];

                //recebe cada linha da planilha
                Row row = rowIterator.next();

                //pegamos todas as celulas desta linha
                Iterator<Cell> cellIterator = row.iterator();

                int contador = 0;
                //varremos todas as celulas da linha atual
                while (cellIterator.hasNext()) {

                    //criamos uma celula
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {

                        case Cell.CELL_TYPE_STRING:
                            if (!cell.getStringCellValue().contains("Paraiba") && !cell.getStringCellValue().contains("Codigo Loja Risco") && !cell.getStringCellValue().contains("Nome") && !cell.getStringCellValue().contains("Tipo") && !cell.getStringCellValue().contains("N Fantasia") && !cell.getStringCellValue().contains("CNPJ/CPF")) {
                                dados[contador] = cell.getStringCellValue();
                            }
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            dados[contador] = String.valueOf(cell.getNumericCellValue());
                    }
                    contador++;
                }
                String[] vetorSemDadoVazio = new String[5];
                for (int x = 0; x < dados.length; x++) {
                    if (dados[x] != null) {
                        vetorSemDadoVazio[x] = dados[x];

                    }
                }

                String codigo = "";
                String lojaRisco = "";

                if (vetorSemDadoVazio[4] != null && !vetorSemDadoVazio[4].isEmpty()) {
                    String[] aux = vetorSemDadoVazio[4].split("/");
                    codigo = aux[0];
                    lojaRisco = aux[1];
                }

                clientes.add(new ClientePDF(vetorSemDadoVazio[0], vetorSemDadoVazio[1], vetorSemDadoVazio[2], vetorSemDadoVazio[3], codigo, lojaRisco));

            System.out.println("Path printado: " + path ); 
            int x = 0;
            for (ClientePDF c : clientes) {
                System.out.println("Dados: " + x);
                System.out.println("Nome: " + c.getNome());
                System.out.println("Nome Fantasia: " + c.getNomeFantasia());
                System.out.println("Tipo: " + c.getTipo());
                System.out.println("CNPJ/CPF: " + c.getCnpj());
                System.out.println("Cdigo: " + c.getCodigo());
                System.out.println("Loja Risco: " + c.getLojaRisco());
                System.out.println("\n \n");
                x++;
            }
           
           
        }//try
        }
    
    catch (FileNotFoundException ex

    
        ) {
            Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IOException ex

    
        ) {
            Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
    }

    
        finally {
            try {
            fisPlanilha.close();
        } catch (IOException ex) {
            Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         return clientes;
}
    
}
