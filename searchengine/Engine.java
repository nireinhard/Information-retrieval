package searchengine;

import java.io.File;
import java.io.IOException;

public class Engine {

    /**
     * Please point the path to the unzipped directory structure
     */
    public static void main(String[] args) {
        try {
            String path = System.getenv("parsepath");
            if (path != null){
                Importer.importData(new File(path));
            }else{
                Importer.importData(new File("/Users/user1/Downloads/nyt/data/2000"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
