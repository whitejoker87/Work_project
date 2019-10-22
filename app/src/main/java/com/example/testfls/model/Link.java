package com.example.testfls.model;

import com.tickaroo.tikxml.annotation.Attribute;
import com.tickaroo.tikxml.annotation.Xml;

@Xml
public class Link {
    @Attribute String href;
    @Attribute String rel;
    @Attribute String type;
}
