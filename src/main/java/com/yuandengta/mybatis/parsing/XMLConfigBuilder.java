package com.yuandengta.mybatis.parsing;

import com.yuandengta.mybatis.mapping.MyConfiguration;

import java.io.InputStream;

public class XMLConfigBuilder {

    private XPathParser xPathParser;

    public XPathParser getxPathParser() {
        return xPathParser;
    }

    public void setxPathParser(XPathParser xPathParser) {
        this.xPathParser = xPathParser;
    }

    public XMLConfigBuilder(InputStream inputStream) {
        this.xPathParser = new XPathParser(inputStream);
    }

    public MyConfiguration parse() {
        //TODO: 02---待完善

        return null;
    }
}
