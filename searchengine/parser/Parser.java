package searchengine.parser;

import searchengine.Document;
import searchengine.stemmer.SnowballStemmer;
import searchengine.stemmer.englishStemmer;

import java.io.File;

public class Parser {
    private NYTCorpusDocumentParser nytParser;

    public Parser(){
        nytParser = new NYTCorpusDocumentParser();
    }

    public Document parse(File file) {
        NYTCorpusDocument nytDoc = nytParser.parseNYTCorpusDocumentFromFile(file,false);
        return new Document(nytDoc);
    }

    public Document parseAndStemm(File file){
        SnowballStemmer stemmer = (SnowballStemmer) new englishStemmer();
        Document doc = parse(file);
        if(doc.getContent() != null){
            for(int i = 0; i<doc.getContent().length; i++){
                stemmer.setCurrent(doc.getContent()[i]);
                stemmer.stem();
                String current = stemmer.getCurrent();
                doc.getContent()[i] = current;
            }
        }
        return doc;
    }
}
