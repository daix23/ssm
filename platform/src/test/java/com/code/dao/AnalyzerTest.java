package com.code.dao;


import org.ansj.lucene6.AnsjAnalyzer;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apdplat.word.lucene.ChineseWordAnalyzer;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.lionsoul.jcseg.analyzer.JcsegAnalyzer;
import org.lionsoul.jcseg.tokenizer.core.JcsegTaskConfig;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.Reader;
import java.io.StringReader;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-7-3
 * Time: 下午5:06
 * To change this template use File | Settings | File Templates.
 */
public class AnalyzerTest {

    public static void main(String args[]) throws Exception{
        String string = "浙江省测绘科学技术研究院";
        /*Analyzer analyzer = new StandardAnalyzer();
        Reader r = new StringReader(string);
        TokenStream stream=  analyzer.tokenStream("", string);
        //获取每个单词信息,获取词元文本属性
        CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
        stream.reset();
        while(stream.incrementToken()){
            System.out.println("" + cta + "");
        }
*/
        //智能分词
        //Analyzer analyzer = new IKAnalyzer();
        //标准分词
        //Analyzer analyzer = new StandardAnalyzer();
        //jcseg分词
        /*Analyzer analyzer = new JcsegAnalyzer(JcsegTaskConfig.SIMPLE_MODE);
        JcsegAnalyzer jcseg = (JcsegAnalyzer) analyzer;
        JcsegTaskConfig config = jcseg.getTaskConfig();
        config.setAppendCJKSyn(true);
        config.setAppendCJKPinyin(true);*/
        //word分词
        //Analyzer analyzer = new ChineseWordAnalyzer(SegmentationAlgorithm.FullSegmentation);
        //ansj分词
        //System.out.println(ToAnalysis.parse(string));
        Analyzer analyzer = new AnsjAnalyzer(AnsjAnalyzer.TYPE.query_ansj);

        Reader r = new StringReader(string);
        TokenStream stream=  analyzer.tokenStream("remark", string);
        //获取每个单词信息,获取词元文本属性
        CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
        stream.reset();
        while(stream.incrementToken()){
            System.out.println("" + cta + "");
        }


    }

}
