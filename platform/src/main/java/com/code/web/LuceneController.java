package com.code.web;

import com.code.dao.CoordinatesMapper;
import com.code.dto.Result;
import com.code.entity.Coordinates;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-28
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/lucene")
public class LuceneController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CoordinatesMapper coordinatesMapper;

    /*@Autowired(required = false)
    private Analyzer analyzer;
    @Autowired(required = false)
    private IndexWriter indexWriter;
    @Autowired(required = false)
    private IndexSearcher indexSearcher;*/

    /**
     * http://localhost:8080/platform/lucene/search?keyword=江
     * @param keyword
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public Result<Object> search( @RequestParam("keyword") String keyword) throws ParseException {
        long begin = System.currentTimeMillis();
        //Analyzer analyzer = new StandardAnalyzer();
        //"庖丁解牛"中文分词器java.lang.VerifyError:
        //Analyzer analyzer = new PaodingAnalyzer();
        //创建IKAnalyzer中文分词对象
        IKAnalyzer analyzer = new IKAnalyzer();
        //analyzer.setUseSmart(true);
        String[] queryString = {keyword};//注意字段与值要一一对应哦，同下 ，查询内容，
        String[] fields = {"remark"};////注意字段与值要一一对应哦，同上，字段名称
        BooleanClause.Occur[] clauses = {BooleanClause.Occur.MUST};//这里就是 and 的关系，详细策略看文档哈
        Query query = MultiFieldQueryParser.parse(queryString, fields, clauses, analyzer);
        List<Coordinates> list = doSearch(query);
        System.out.println("create cost:" + (System.currentTimeMillis() - begin) / 1000 + "s");
        return new Result<Object>(true, list);
    }

    /**
     * http://localhost:8080/platform/lucene/create
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public Result<Object> create() throws Exception{
        String sec = createIndex();
        Object obj = (Object)("耗时"+sec+"秒");
        return new Result<Object>(true,obj);
    }

    public String createIndex() {
        long begin = System.currentTimeMillis();
        // 采集数据
        List<Coordinates> list = coordinatesMapper.queryAll();
        IndexWriterConfig writerConfig = new IndexWriterConfig(new StandardAnalyzer());
            /*
             * 创建索引写入对象，该对象既可以把索引写入到磁盘中也可以写入到内存中。 参数说明：
             * public IndexWriter(Directory directory, IndexWriterConfig conf)
             * directory:目录对象,也可以是FSDirectory 磁盘目录对象
             * conf:写入对象的控制
             */
        IndexWriter indexWriter = null;
        try {
            Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("D:\\lucenceSSM\\"));
            indexWriter = new IndexWriter(directory, writerConfig);
            indexWriter.deleteAll(); //清除以前的index
            Document document;
            //通过IndexWriter对象将Document写入到索引库中
            for (Coordinates one : list) {
                document = new Document();
                // store:如果是yes，则说明存储到文档域中
                // id
                Field id = new TextField("id", Integer.toString(one.getId()), Field.Store.YES);
                // 类型
                Field geotype = new TextField("geotype", one.getGeotype(), Field.Store.YES);
                // 空间坐标
                Field geo = new TextField("geo", one.getGeo(), Field.Store.YES);
                // 备注
                Field remark = new TextField("remark", one.getRemark(), Field.Store.YES);
                // 将field域设置到Document对象中
                document.add(id);
                document.add(geotype);
                document.add(geo);
                document.add(remark);
                indexWriter.addDocument(document);
            }
            indexWriter.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(indexWriter!=null){
                    indexWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String sec = (System.currentTimeMillis() - begin) / 1000+"";
        System.out.println("create cost:" + sec + "s");
        return sec;
    }

    private List<Coordinates> doSearch(Query query) {
        List<Coordinates> list = new ArrayList<Coordinates>();
        try {
            // 通过searcher来搜索索引库
            Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("D:\\lucenceSSM\\"));
            IndexReader reader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(reader);
            // 第二个参数：指定需要显示的顶部记录的N条
            TopDocs topDocs = indexSearcher.search(query, 10);
            // 根据查询条件匹配出的记录总数
            int count = topDocs.totalHits;
            System.out.println("匹配出的记录总数:" + count);
            // 根据查询条件匹配出的记录
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                // 获取文档的ID
                int docId = scoreDoc.doc;
                // 通过ID获取文档
                Coordinates one = new Coordinates();
                Document doc = indexSearcher.doc(docId);
                System.out.println("主键id：" + doc.get("id"));
                one.setId(Integer.parseInt(doc.get("id")));
                System.out.println("geo类型：" + doc.get("geotype"));
                one.setGeotype(doc.get("geotype"));
                System.out.println("备注：" + doc.get("remark"));
                one.setRemark(doc.get("remark"));
                System.out.println("==========================");
                list.add(one);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


}
