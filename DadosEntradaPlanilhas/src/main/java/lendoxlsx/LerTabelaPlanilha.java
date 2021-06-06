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
import model.ClientePlanilha;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LerTabelaPlanilha {

    public LerTabelaPlanilha(){}
    public ArrayList<ClientePlanilha> lerDados( String path ) {
        FileInputStream fisPlanilha = null;
        ArrayList<ClientePlanilha> clientes = new ArrayList<>();
        try {
            
            File file = new File(path);
            fisPlanilha = new FileInputStream(file);

            //cria um workbook = planilha toda com todas as abas
            XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha);
            System.out.println("NUMERO DE ABAS: " + workbook.getNumberOfSheets());
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                System.out.println("Iteração: " + i);
                XSSFSheet sheet = workbook.getSheetAt(i);
                //retorna todas as linhas da planilha i (aba 1)
                Iterator<Row> rowIterator = sheet.iterator();

                //varre todas as linhas da planilha i
                while (rowIterator.hasNext()) {
                    String[] dados = new String[4];

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
                                if (!cell.getStringCellValue().endsWith("(33)") && !cell.getStringCellValue().endsWith("(22)") && !cell.getStringCellValue().endsWith("(28)") && !cell.getStringCellValue().endsWith("(27)") && !cell.getStringCellValue().endsWith("(28)") && !cell.getStringCellValue().contains("CÓD") && !cell.getStringCellValue().contains("RAZÃO SOCIAL") && !cell.getStringCellValue().contains("TELEFONE") && !cell.getStringCellValue().contains("CONTATO. Srª/Sr")) {
                                    dados[contador] = cell.getStringCellValue();
                                    System.out.println(dados[contador]);
                                }
                                break;

                            case Cell.CELL_TYPE_NUMERIC:
                                dados[contador] = String.valueOf(cell.getNumericCellValue());
                                System.out.println(dados[contador]);
                        }
                        contador++;
                    }
                    /*
                    String[] vetorSemDadoVazio = new String[4];
                    for (int x = 0; x < dados.length; x++) {
                        if (dados[x] != null) {
                            vetorSemDadoVazio[x] = dados[x];

                        }
                    }*/

                    ArrayList<String> telefones = new ArrayList<>();
                    if (dados[2] != null && !dados[2].isEmpty()) {
                        String[] aux = dados[2].split("/");
                        for (int k = 0; k < aux.length; k++) {
                            //System.out.println(aux[i]);
                            telefones.add(aux[k]);
                        }
                    }

                    if (dados[3] != null) {
                        clientes.add(new ClientePlanilha(telefones, dados[3]));
                    }

                }
                int x = 0;
                for (ClientePlanilha c : clientes) {
                    System.out.println("Dados: " + x);
                    for (int j = 0; j < c.getTelefones().size(); j++) {
                        System.out.println("Telefone " + j + ": " + c.getTelefones().get(j));
                    }
                    System.out.println("Contato: " + c.getContato());
                    System.out.println("\n \n");
                    x++;
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fisPlanilha.close();
            } catch (IOException ex) {
                Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return clientes;
    }

}
