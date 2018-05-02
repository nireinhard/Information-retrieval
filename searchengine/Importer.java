package searchengine;

import searchengine.parser.NYTCorpusDocument;
import searchengine.parser.NYTCorpusDocumentParser;
import searchengine.parser.Parser;

import java.io.File;
import java.io.IOException;

public class Importer {

    static void importData(File file) throws IOException {
        if(!file.exists())throw new IOException("Datei existiert nicht!");
        if(!file.canRead())throw new IOException("keine Leserechte!");
        if(file.isDirectory()) {
            for(File dir : file.listFiles()){
                importData(dir);
            }
            return;
        }
        if(!file.isFile())throw new IOException("keine Datei oder Verzeichnis!"+ file.toString());
        String name = file.getName();
        String ext = name.substring(name.lastIndexOf(".")+1);
        if (ext.equals("xml")){
            System.out.printf("%-20s %s%10d\n", name,new Parser().parse(file).getId(), file.length());
        }

    }
}
