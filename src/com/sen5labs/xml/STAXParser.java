/**
 *STAXParser.java[V 1.0.0]
 *classes : com.sen5labs.xml.STAXParser
 * Xlee Create at 2016-3-14 下午6:50:56
 */
package com.sen5labs.xml;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

/**
 * com.sen5labs.xml.STAXParser<br/>
 * <br/>
 * StAX是基于流的，在代码的结构上和SAX非常的相似，也可以借助栈来完成文件的解析。运行时也需要使用StAX的实现jar包
 * 
 * @author Xlee <br/>
 *         create at 2016-3-14 下午6:50:56
 */
public class STAXParser implements ServerParser {
    private XMLInputFactory factory;
    private List<Host> mHostlist = null;
    /**
     * <Host>节点开始时压栈，结束时出栈
     */
    private Stack<Host> hostStack = new Stack<Host>();

    @Override
    public List<Host> getServer(String xmlFilePath) throws Exception {
        XMLStreamReader reader = null;
        try {
            FileReader fileReader = new FileReader(xmlFilePath);
            reader = factory.createXMLStreamReader(fileReader);
            process(reader);
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
            }
        }
        return mHostlist;
    }

    public STAXParser() {
        factory = XMLInputFactory.newInstance();
    }

    /**
     * 循环处理，直到到达文件末尾
     * 
     * @param xmlr
     * @throws XMLStreamException
     */
    private void process(XMLStreamReader xmlr) throws XMLStreamException {
        xmlr.next();
        while (xmlr.getEventType() != XMLStreamConstants.END_DOCUMENT) {
            processEvent(xmlr);
            xmlr.next();
        }
    }

    /**
     * 处理单个事件：节点开始、节点结束...
     * 
     * @param xmlr
     * @throws XMLStreamException
     */
    private void processEvent(XMLStreamReader xmlr) throws XMLStreamException {
        switch (xmlr.getEventType()) {
        case XMLEvent.START_ELEMENT:
            if (Constant.SERVERS.equals(xmlr.getLocalName())) {
                mHostlist = new ArrayList<Host>();
            } else if (Constant.HOST.equals(xmlr.getLocalName())) {
                // parameter namespace is "".
                String hostName = xmlr.getAttributeValue(null, Constant.HOST_NAME);
                Host host = new Host();
                host.setName(hostName);
                hostStack.push(host);
            } else if (Constant.URL.equals(xmlr.getLocalName())) {
                String url = xmlr.getElementText();
                hostStack.peek().setUrl(url);
            }
            break;
        case XMLEvent.END_ELEMENT:
            if (Constant.HOST.equals(xmlr.getLocalName())) {
                mHostlist.add(hostStack.pop());
            }
            break;
        case XMLEvent.CHARACTERS:
            break;
        case XMLEvent.SPACE:
            break;
        case XMLEvent.PROCESSING_INSTRUCTION:
            break;
        case XMLEvent.CDATA:
            break;
        case XMLEvent.COMMENT:
            break;
        case XMLEvent.ENTITY_REFERENCE:
            break;
        case XMLEvent.START_DOCUMENT:
            break;
        }
    }
}
