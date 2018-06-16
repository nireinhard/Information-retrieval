package searchengine;

public class Engine {

    /**
     * Please point the path to the unzipped directory structure
     */
    public static void main(String[] args) {
//        try {
//            String path = System.getenv("parsepath");
//            if (path != null){
//                Importer.importData(new File(path));
//            }else{
//                Importer.importData(new File("/Users/niklasreinhard/Downloads/nyt/data/2000"));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        QueryProcessoer qp = new QueryProcessoer();
        qp.process("year");

    }
}
