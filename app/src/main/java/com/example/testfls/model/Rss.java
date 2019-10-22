package com.example.testfls.model;

import com.tickaroo.tikxml.annotation.Attribute;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(writeNamespaces = {"xmlns:dc=http://purl.org/dc/elements/1.1/", "xmlns:content=http://purl.org/rss/1.0/modules/content/","xmlns:atom=http://www.w3.org/2005/Atom"})
public class Rss {
    @Attribute String version;
    @PropertyElement Channel channel;
}
