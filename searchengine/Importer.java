package searchengine;

import searchengine.parser.Parser;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Importer {

    static List<Document> parsedDocuments = new LinkedList<Document>();
    static Parser parser = new Parser();

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
            Document doc = parser.parseAndStemm(file);
            parsedDocuments.add(doc);
            debugStdoutLog(doc);
        }
    }

    private static void debugStdoutLog(Document doc){
        System.out.println("STEMMED DOC BEGIN: " + doc.getTitle() + "\n");
        for (int i=0; i<doc.getContent().length; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getContent()[i]).append(" ");
            System.out.print(sb.toString());
        }
        System.out.println("\n\nSTEMMED DOC END\n");
    }
}
