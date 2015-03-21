package Shader.tool;

import java.nio.file.Path;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

class Index {

  public Index(Path pathos, String[] args) throws IOException, ParseException {

    Path path = pathos;
    String[] b = null;
    int hits1 = 0;

    // 1. Specify the analyzer for tokenizing text.
    // The same analyzer should be used for indexing and searching
    final File docDir = new File(path + "/Data/ShaderData");
    // String indexPath = "C:/Users/Home/AppData/Local/Temp/TestGitRepository";

    String indexPath = path.toString() + "/Index";

    boolean create = true;
    if (!docDir.exists() || !docDir.canRead()) {
      System.out.println("Document directory '" + docDir.getAbsolutePath()
          + "' does not exist or is not readable, please check the path");
      System.exit(1);
    }

    Date start = new Date();
    try {
      System.out.println("Indexing to directory '" + indexPath + "'...");

      Directory dir = FSDirectory.open(new File(indexPath));

      Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
      IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_47, analyzer);

      if (create) {
        // Create a new index in the directory, removing any
        // previously indexed documents:
        iwc.setOpenMode(OpenMode.CREATE);
      } else {
        // Add new documents to an existing index:
        iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
      }

      // 2. create the index
      IndexWriter writer = new IndexWriter(dir, iwc);
      indexDocs(writer, docDir);
      writer.close();

      /*
       * 
       * // 3. query //String querystr = args.length > 0 ? args[0] : "lighti" +
       * "~2"; String querystr = "shader"+"~0.5";
       * 
       * // the "title" arg specifies the default field to use // when no field
       * is explicitly specified in the query. Query q = new
       * QueryParser(Version.LUCENE_47, "contents", analyzer).parse(querystr);
       * //Query fuzzyQuery = new FuzzyQuery(new Term("contents", querystr), 1);
       * 
       * // 4. search int hitsPerPage = 100; IndexReader reader =
       * DirectoryReader.open(dir); IndexSearcher searcher = new
       * IndexSearcher(reader); TopScoreDocCollector collector =
       * TopScoreDocCollector.create(hitsPerPage, true); searcher.search(q,
       * collector); ScoreDoc[] hits = collector.topDocs().scoreDocs;
       * 
       * hits1 = hits.length; b = new String[hits.length];
       * 
       * // 5. display results System.out.println("Found " + hits.length +
       * " hits."); for(int i=0;i<hits.length;++i) { int docId = hits[i].doc;
       * Document d = searcher.doc(docId); System.out.println((i + 1) + ". " +
       * d.getField("path")); //System.out.println(d.getField("path"));
       * 
       * String a = d.getField("path").toString(); b[i] = a;
       * 
       * }
       * 
       * 
       * // 6. close the reader when there is no need reader.close();
       */

    } catch (Exception ex) {

    }

    /*
     * System.out.println(b[0]);
     * 
     * String[]c = new String[hits1]; for(int i=0;i<hits1;++i) {
     * 
     * 
     * if (b[i].contains("path")) { String div = b[i]; String result =
     * div.replaceAll(">",""); String parts[] = result.split("path:"); //parts =
     * b.split("\\path"); c[i] = parts[1]; //System.out.println(parts[0]);
     * //System.out.println(c[i]); Path prueba = Paths.get(c[i]);
     * //System.out.format("getFileName: %s%n", prueba.getFileName()); Path casa
     * = prueba.getFileName(); System.out.println(casa); } }
     * 
     * //System.out.println(c[10]);
     */

  }

  static void indexDocs(IndexWriter writer, File file) throws IOException {

    // do not try to index files that cannot be read

    if (file.canRead()) {
      if (file.isDirectory()) {
        String[] files = file.list();
        // an IO error could occur
        if (files != null) {
          for (int i = 0; i < files.length; i++) {
            indexDocs(writer, new File(file, files[i]));
          }
        }
      } else {
        if (file.getName().endsWith(".txt")) {
          FileInputStream fis;
          try {
            fis = new FileInputStream(file);
          } catch (FileNotFoundException fnfe) {
            return;
          }

          try {

            // make a new, empty document
            Document doc = new Document();
            Field pathField = new StringField("path", file.getPath(),
                Field.Store.YES);
            doc.add(pathField);

            doc.add(new LongField("modified", file.lastModified(),
                Field.Store.NO));

            doc.add(new TextField("contents", new BufferedReader(
                new InputStreamReader(fis, "UTF-8"))));

            if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
              // New index, so we just add the document (no old document can be
              // there):
              System.out.println("adding " + file);
              writer.addDocument(doc);
            } else {
              System.out.println("updating " + file);
              writer.updateDocument(new Term("path", file.getPath()), doc);
            }

          } finally {
            fis.close();
          }
        }
      }
    }

  }

}