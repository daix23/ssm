package com.code.util;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.lionsoul.jcseg.analyzer.JcsegAnalyzer;
import org.lionsoul.jcseg.tokenizer.core.JcsegTaskConfig;
/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-7-4
 * Time: 上午10:50
 * To change this template use File | Settings | File Templates.
 */
public class JcsegAnalyzerUtil {

    public static void main(String[] args) {
        Analyzer analyzer = new JcsegAnalyzer(JcsegTaskConfig.SIMPLE_MODE);
        //非必须(用于修改默认配置): 获取分词任务配置实例
        JcsegAnalyzer jcseg = (JcsegAnalyzer) analyzer;
        JcsegTaskConfig config = jcseg.getTaskConfig();
        //追加同义词到分词结果中, 需要在jcseg.properties中配置jcseg.loadsyn=1
        config.setAppendCJKSyn(true);
        //追加拼音到分词结果中, 需要在jcseg.properties中配置jcseg.loadpinyin=1
        config.setAppendCJKPinyin(true);
        //更多配置, 请查看com.webssky.jcseg.core.JcsegTaskConfig类
        String words = "中华人民共和国";
        TokenStream stream = null;

        try {
            stream = analyzer.tokenStream("myfield", words);
            stream.reset();
            CharTermAttribute  offsetAtt = stream.addAttribute(CharTermAttribute.class);
            while (stream.incrementToken()) {
                System.out.println(offsetAtt.toString());
            }
            stream.end();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                if(stream != null)
                    stream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
