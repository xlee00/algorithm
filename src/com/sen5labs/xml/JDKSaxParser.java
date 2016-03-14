/**
 *JDKSaxParser.java[V 1.0.0]
 *classes : com.sen5labs.xml.JDKSaxParser
 * Xlee Create at 2016-3-14 上午11:35:45
 */
package com.sen5labs.xml;

import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * com.sen5labs.xml.JDKSaxParser
 * 
 * @author Xlee <br/>
 *         create at 2016-3-14 上午11:35:45 <br/>
 *         SAX(Simple API for
 *         XML)是基于事件的，其不会加载整个文件到内存中，属于按需取内容。一般使用SAX解析时都会借助于栈来完成元素和数据模型的衔接。<br/>
 * <br/>
 *         SAX解析技术具有所有流式解析技术的优点和缺点，但是由于在整个解析过程中，解析器掌握着控制权直到文档结束，
 *         应用程序很难在获得所需的部分数据后停止解析过程
 *         (可以通过抛出异常的方式终止解析过程，但较为复杂，而且终止后也无法继续解析过程)，因此产生了由应用程序掌握控制权的拉式解析方式。
 *         拉式解析(StAX解析技术)
 * 
 */
public class JDKSaxParser implements ServerParser {
    private XMLReader reader = null;

    /**
     * @throws SAXException
     */
    public JDKSaxParser() throws SAXException {
        reader = XMLReaderFactory.createXMLReader();
    }

    @Override
    public List<Host> getServer(String xmlFilePath) throws Exception {
        ResourceHandler handler = new ResourceHandler();
        reader.setContentHandler(handler);
        reader.parse(xmlFilePath);
        return handler.getHostList();
    }
}
