package com.code.dao;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
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
        String string = "中华人民共和国";
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
        //Analyzer analyzer = new IKAnalyzer();
        Analyzer analyzer = new StandardAnalyzer();
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
