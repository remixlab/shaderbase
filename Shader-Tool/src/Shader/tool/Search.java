package Shader.tool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import processing.app.Editor;

class Search {

  String searchin;
  int contaid;
  String[] searchid;
  String[] searchnames;
  String[] searchfolder;
  private static Editor editor = null;
  Path p5 = null;
  String[] b = null;
  int hits1 = 0;

  public Search(String[] searchid2, String[] searchnames2, Editor editor,
      Path pathos, String[] searchfolder2, String searchin2)
      throws ParseException {
    // TODO Auto-generated constructor stub
    this.editor = editor;
    p5 = pathos;
    searchin = searchin2;
    String indexPath = p5.toString() + "/Index";
    Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
    try {
      Directory dir = FSDirectory.open(new File(indexPath));

      // 3. query
      // String querystr = args.length > 0 ? args[0] : "lighti" + "~2";
      String querystr = searchin;

      // the "title" arg specifies the default field to use
      // when no field is explicitly specified in the query.
      Query q = new QueryParser(Version.LUCENE_47, "contents", analyzer)
          .parse(querystr);
      // Query fuzzyQuery = new FuzzyQuery(new Term("contents", querystr), 1);

      // 4. search
      int hitsPerPage = 100;
      IndexReader reader = DirectoryReader.open(dir);
      IndexSearcher searcher = new IndexSearcher(reader);
      TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage,
          true);
      searcher.search(q, collector);
      ScoreDoc[] hits = collector.topDocs().scoreDocs;

      hits1 = hits.length;
      b = new String[hits.length];

      // 5. display results
      System.out.println("Found " + hits.length + " hits.");
      for (int i = 0; i < hits.length; ++i) {
        int docId = hits[i].doc;
        Document d = searcher.doc(docId);
        System.out.println((i + 1) + ". " + d.getField("path"));
        // System.out.println(d.getField("path"));

        String a = d.getField("path").toString();
        b[i] = a;

      }

      // 6. close the reader when there is no need
      reader.close();

      String[] names = new String[hits1];
      String[] c = new String[hits1];
      for (int i = 0; i < hits1; ++i) {
        String searchnames1 = null;

        if (b[i].contains("path")) {
          String div = b[i];
          String result = div.replaceAll(">", "");
          String parts[] = result.split("path:");
          // parts = b.split("\\path");
          c[i] = parts[1];
          // System.out.println(parts[0]);
          // System.out.println(c[i]);
          Path prueba = Paths.get(c[i]);
          // System.out.format("getFileName: %s%n", prueba.getFileName());
          Path name = prueba.getFileName();
          searchnames1 = FilenameUtils.removeExtension(name.toString());
          names[i] = searchnames1;
        }

      }

      searchnames = names;

      String[] folder = new String[hits1];
      String[] conta = new String[hits1];
      for (int i = 0; i < hits1; ++i) {
        int k = 0;
        folder[i] = p5 + "/Data/ShaderData" + "/" + searchnames[i];

        conta[i] = Integer.toString(k);
        // k++;
      }

      searchfolder = folder;
      searchid = conta;
      // System.out.println(searchnames[1]);
      // System.out.println(searchfolder[0]);
      // System.out.println(searchid[1]);

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  Search changeSearch(String newValue) throws ParseException {

    searchin = newValue;
    // searchid =null;
    // searchnames = null;
    // searchfolder = null;

    return new Search(searchid, searchnames, editor, p5, searchfolder, searchin);

  }

}// end Search