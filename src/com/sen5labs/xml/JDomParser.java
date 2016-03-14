/**
 *JDomParser.java[V 1.0.0]
 *classes : com.sen5labs.xml.JDomParser
 * Xlee Create at 2016-3-14 下午7:10:38
 */
package com.sen5labs.xml;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPath;

/**
 * com.sen5labs.xml.JDomParser
 * 
 * JDOM借助于XPath完成解析，需要jdom的jar包实现及jaxen jar包(主要用于处理XPath)实现。
 * 
 * @author Xlee <br/>
 *         create at 2016-3-14 下午7:10:38
 */
@SuppressWarnings("deprecation")
public class JDomParser implements ServerParser {
    private SAXBuilder builder;

    public JDomParser() {
        builder = new SAXBuilder();
    }

    @Override
    public List<Host> getServer(String xmlFilePath) throws Exception {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(xmlFilePath);
            Document document = builder.build(stream);
            Element root = document.getRootElement();
            return parse(root);
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private List<Host> parse(Element root) throws JDOMException {
        List<Host> groupList = new ArrayList<Host>();
        // <host>节点是从根节点开始select，因此xpath需要使用绝对路径(/打头)
        String xPath = "/" + Constant.ROOT + "/" + Constant.SERVERS + "/" + Constant.HOST;
        // <host>节点有多个，因此需要使用XPath.selectNodes
        List<?> groupElements = XPath.selectNodes(root, xPath);
        for (int i = 0; i < groupElements.size(); i++) {
            Element hostElement = (Element) groupElements.get(i);
            String hostName = hostElement.getAttributeValue(Constant.HOST_NAME);
            Host host = new Host();
            host.setName(hostName);
            parseHost(hostElement, host);
            groupList.add(host);
        }

        return groupList;
    }

    private void parseHost(Element groupElement, Host host) throws JDOMException {
        // 一个<host>节点下有多个<url>节点，因此需要使用XPath.selectNodes
        List<?> machineElements = XPath.selectNodes(groupElement, Constant.URL);
        for (int i = 0; i < machineElements.size(); i++) {
            Element machineElement = (Element) machineElements.get(i);
            String url = machineElement.getText();
            host.setUrl(url);
        }
    }

}
