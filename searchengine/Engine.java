package searchengine;

import java.io.File;
import java.io.IOException;

public class Engine {

    public static void main(String[] args) {
        try {
            Importer.importData(new File("/home/manuel/Dokumente/HTW/6.Semester/IR/nyt/data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
