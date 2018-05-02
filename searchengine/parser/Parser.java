package searchengine.parser;

import searchengine.Document;

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
}
