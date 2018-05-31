/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Readers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author Jose Paniagua
 */
public class PDFReader implements IReader{
    //Constructor de mi clase PDFReaders
    private ArrayList<String> tokens;
    public PDFReader(){
        tokens =new ArrayList<String>();
    }
    
    @Override
    public ArrayList<String> readFile(String path) {
        
        try {
            PDDocument document= PDDocument.load(new File(path));
            if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);            
            tokens=new ArrayList<>(Arrays.asList(text.split("[^\\w+'*]+")));
            document.close();
            }else{
                System.out.println("El documento esta encriptado");
            }
        } catch (IOException ex) {
            
        }        
        return tokens;
    }
    
}
