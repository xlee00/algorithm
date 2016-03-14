/**
 *Host.java[V 1.0.0]
 *classes : com.sen5labs.xml.Host
 * Xlee Create at 2016-3-14 下午3:57:55
 */
package com.sen5labs.xml;

/**
 * com.sen5labs.xml.Host
 * 
 * @author Xlee <br/>
 *         create at 2016-3-14 下午3:57:55
 */
public class Host {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Host [name=" + name + ", url=" + url + "]";
    }
}
