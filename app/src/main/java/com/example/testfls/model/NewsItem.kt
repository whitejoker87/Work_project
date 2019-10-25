package com.example.testfls.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root


@Root(name = "rss", strict = false)
data class Rss (
    @field:Element(name = "channel") @param:Element(name = "channel")
    var channel: Channel

)

@Root(name = "channel", strict = false)
data class Channel (
    @field:Element(name = "title") @param:Element(name = "title")
    var title: String,

    @field:ElementList(name = "item", inline = true) @param:ElementList(name = "item", inline = true)
    var items: List<NewsItem>
)

@Entity(tableName = "news_items")
@Root(name = "item", strict = false)
data class NewsItem (
    @PrimaryKey @field:Element(name = "title") @param:Element(name = "title")var title: String,
    @field:Element(name = "description") @param:Element(name = "description")var description: String,
    @field:Element(name = "pubDate") @param:Element(name = "pubDate")var pubDate:String,
    @field:Element(name = "author") @param:Element(name = "author")var author:String,
    @field:Element(name = "link") @param:Element(name = "link")var link:String
)
