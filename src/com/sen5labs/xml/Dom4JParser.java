/**
 *Dom4JParser.java[V 1.0.0]
 *classes : com.sen5labs.xml.Dom4JParser
 * Xlee Create at 2016-3-14 下午5:32:54
 */
package com.sen5labs.xml;

import java.util.List;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * com.sen5labs.xml.Dom4JParser <br/>
 * <br/>
 * Dom4JParser也结合XPath对XML节点进行解析<br/>
 * 起相应的Element等类就包装了JDOM中需要使用XPath类才能完成的路径选取功能。<br/>
 * 运行时也需要dom4j的实现jar包及jaxen jar包。
 * 
 * @author Xlee <br/>
 *         create at 2016-3-14 下午5:32:54
 */
public class Dom4JParser implements ServerParser {
    private SAXReader reader;

    public Dom4JParser() {
        reader = new SAXReader();
    }

    @Override
    public List<Host> getServer(String xmlFilePath) throws Exception {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(xmlFilePath);
            Document document = reader.read(stream);
            Element root = document.getRootElement();
            return parse(root);
        } finally {
            try {
                if (null != stream) {
                    stream.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private List<Host> parse(Element root) {
        List<Host> hostList = new ArrayList<Host>();
        // <host>节点是从根节点开始select，因此xpath需要使用绝对路径(/打头)
        String xPath = "/" + Constant.ROOT + "/" + Constant.SERVERS + "/" + Constant.HOST;
        // <host>节点有多个，因此需要使用selectNodes
        List<Node> hostElement = root.selectNodes(xPath);
        for (int i = 0; i < hostElement.size(); i++) {
            Element hostElment = (Element) hostElement.get(i);
            String hostName = hostElment.attributeValue(Constant.HOST_NAME);
            Host host = new Host();
            host.setName(hostName);
            parseHost(hostElment, host);
            hostList.add(host);
        }

        return hostList;
    }

    private void parseHost(Element hostElement, Host group) {
        // 一个<host>节点下有多个<url>节点，因此需要使用XPath.selectNodes
        List<Node> urlElement = hostElement.selectNodes(Constant.URL);
        for (int i = 0; i < urlElement.size(); i++) {
            Element machineElement = (Element) urlElement.get(i);
            String url = machineElement.getText();
            group.setUrl(url);
        }
    }

}
