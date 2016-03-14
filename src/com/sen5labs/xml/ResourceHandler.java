package com.sen5labs.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ResourceHandler extends DefaultHandler {

    private List<Host> mHostlist = null;
    /**
     * <Host>节点开始时压栈，结束时出栈
     */
    private Stack<Host> hostStack = new Stack<Host>();

    /**
     * 对<url>这样的节点，因为是节点+文本类型，因此需要用这个标示节点的内容
     */
    private String currentNodeText;

    /**
     * XML节点开始时处理方法：主要是遇到<host>节点的开始标签是需要把host入栈
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (Constant.SERVERS.equals(qName)) {
            mHostlist = new ArrayList<Host>();
        } else if (Constant.HOST.equals(qName)) {
            Host host = new Host();
            host.setName(attributes.getValue(Constant.HOST_NAME));
            hostStack.push(host);
        }
    }

    /**
     * XML节点直接加文本时的处理： 主要是处理<host>节点的值
     */
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentNodeText = String.valueOf(ch, start, length);
    }

    /**
     * XML节点结束时的处理方法： 1，主要是遇到<host>节点的结束标签时需要把Group和Machine出栈保存
     * 2，遇到<url>节点的结束标签时需要保存当前文本为相应的属性值并清空当前文本变量
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (Constant.HOST.equals(qName)) {
            mHostlist.add(hostStack.pop());
        } else if (Constant.URL.equals(qName)) {
            hostStack.peek().setUrl(currentNodeText);
            currentNodeText = null;
        }
    }

    public List<Host> getHostList() {
        return mHostlist;
    }
}