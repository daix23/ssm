package com.code.util;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.FilesystemResourceLoader;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;
/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-7-4
 * Time: 上午9:41
 * To change this template use File | Settings | File Templates.
 */
public class AnalyzerUtil {

    /**
     *
     * 此方法描述的是：进行中文拆分
     */
    public static String analyzeChinese(String input, boolean userSmart) throws IOException{
        StringBuffer sb = new StringBuffer();
        StringReader reader = new StringReader(input.trim());
        IKSegmenter ikSeg = new IKSegmenter(reader, userSmart);// true　用智能分词　，false细粒度
        for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
            sb.append(lexeme.getLexemeText()).append(" ");
        }
        return sb.toString();
    }

    /**
     *
     * 此方法描述的是：针对上面方法拆分后的词组进行同义词匹配，返回TokenStream
     */
    public static TokenStream convertSynonym(String input) throws IOException{
        Analyzer whitespaceAnalyzer = new WhitespaceAnalyzer();
        TokenStream ts=  whitespaceAnalyzer.tokenStream("remark", input);
        return ts;
    }

    /**
     *
     * 此方法描述的是：将tokenstream拼成一个特地格式的字符串，交给IndexSearcher来处理
     */
    public static String displayTokens(TokenStream ts) throws IOException
    {
        StringBuffer sb = new StringBuffer();
        CharTermAttribute termAttr = ts.addAttribute(CharTermAttribute.class);
        ts.reset();
        while (ts.incrementToken())
        {
            String token = termAttr.toString();
            sb.append(token).append(" ");
            System.out.print(token+"|");
//            System.out.print(offsetAttribute.startOffset() + "-" + offsetAttribute.endOffset() + "[" + token + "] ");
        }
        System.out.println();
        ts.end();
        ts.close();
        return sb.toString();
    }

    public static List<String> searchIndex(String keyword, String indexPath) throws Exception{
        List<String> result = new ArrayList<>();
        IndexSearcher indexSearcher = null;
        Analyzer analyzer = new WhitespaceAnalyzer();
        Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(indexPath));
        IndexReader reader = DirectoryReader.open(directory);
         indexSearcher = new IndexSearcher(reader);

        QueryParser queryParser = new QueryParser("remark", analyzer);
        Query query = queryParser.parse(keyword);
        TopDocs td = indexSearcher.search(query, 10);
        for (int i = 0; i < td.totalHits; i++) {
            Document document = indexSearcher.doc(td.scoreDocs[i].doc);
            result.add(document.get("remark"));
        }
        return result;
    }


    public static void main(String[] args) {
        String indexPath = "D:\\lucenceSSM\\";
        String input = "备";
        System.out.println("**********************");
        try {
            //String result = displayTokens(convertSynonym(analyzeChinese(input, true)));
//          MyIndexer.createIndex(indexPath);
            List<String> docs = searchIndex(input, indexPath);
            for (String string : docs) {
                System.out.println(string);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
