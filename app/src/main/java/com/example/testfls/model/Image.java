package com.example.testfls.model;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml
public class Image {
    @PropertyElement String url;
    @PropertyElement String title;
    @PropertyElement String link;
}
