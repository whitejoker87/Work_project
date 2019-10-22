package com.example.testfls.model;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

@Xml
public class Channel {
    @PropertyElement String title;
    @PropertyElement String description;
    @PropertyElement String link;
    @PropertyElement Image image;
    @PropertyElement String generator;
    @PropertyElement String lastBuildDate;
    @PropertyElement(name = "atom:link") Link linkAtom;
    @PropertyElement String pubDate;
    @PropertyElement String copyright;
    @PropertyElement String language;
    @PropertyElement String docs;
    @PropertyElement int ttl;
    @Element List<Item> items;
}