package SearchEngine;

import java.io.File;

public class Importer {

    static void importData(File file){
        if(file.isDirectory()) {
            for(File dir : file.listFiles()){
                importData(dir);
            }
        }

        String name = file.getName();
        String ext = name.substring(name.lastIndexOf(".")+1);
        if (ext.equals("xml")){
            System.out.printf("%-20s %10d\n", name, file.length());
        }
    }
}
