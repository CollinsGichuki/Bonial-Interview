package com.snilloc.bonialinterview.util

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.snilloc.bonialinterview.data.model.*

class ContentAdapter : TypeAdapter<ContentList>() {
    override fun write(out: JsonWriter?, value: ContentList?) {
        TODO("Not yet implemented")
    }

    override fun read(jsonReader: JsonReader?): ContentList {
        val contentList = mutableListOf<Content>()

        val gson = Gson()

        while (jsonReader?.hasNext() == true) {

            if (jsonReader.peek() == JsonToken.BEGIN_ARRAY || jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
                val nextObject = jsonReader.peek()?.name

                if (nextObject == JsonToken.BEGIN_ARRAY.toString()) {
//                    Log.d("BrochuresAct", "Next array name: $nextObject")

                    jsonReader.beginArray()

                    while (jsonReader.hasNext()) {
                        val contentStuff: Content =
                            gson.fromJson(jsonReader, Content::class.java)
                        contentList.add(contentStuff)
                    }

                    jsonReader.endArray()
//                    Log.d("BrochuresAct", "ContentList size with array: ${contentList.size}")
                    continue
                } else {
                    jsonReader.beginObject()

                    val content = Content()

                    while (jsonReader.hasNext()) {

//                        val nextObjct = jsonReader.peek().name
//                        Log.d("BrochuresAct", "Next Object: $nextObjct")

                        when (jsonReader.nextName()) {
                            "id" -> content.id = jsonReader.nextInt()
                            "brochureImage" -> content.brochureImage = jsonReader.nextString()
                            "distance" -> content.distance = jsonReader.nextDouble().toFloat()
                            "retailer" -> content.retailer =
                                gson.fromJson(jsonReader, RetailerData::class.java)

                            "title" -> content.title = jsonReader.nextString()
                            "validFrom" -> content.validFrom = jsonReader.nextString()
                            "validUntil" -> content.validUntil = jsonReader.nextString()
                            "publishedFrom" -> content.publishedFrom = jsonReader.nextString()
                            "publishedUntil" -> content.publishedUntil = jsonReader.nextString()
                            "type" -> content.type = jsonReader.nextString()

                            "pageCount" -> content.pageCount = jsonReader.nextInt()
                            "publisher" -> content.publisher =
                                gson.fromJson(jsonReader, PublisherData::class.java)

                            "isEcommerce" -> content.isEcommerce = jsonReader.nextBoolean()

                            "hideValidityDate" -> content.hideValidityDate =
                                jsonReader.nextBoolean()
                            "content" -> content.innerContent =
                                gson.fromJson(jsonReader, InnerContent::class.java)
                            "externalTracking" -> content.externalTracking =
                                gson.fromJson(jsonReader, ExternalTrackingData::class.java)
                            "campaign_item_id" -> content.campaignItemId = jsonReader.nextString()
                            "link" -> content.link = jsonReader.nextString()

                            "imgURL" -> content.imageURL = jsonReader.nextString()
                            "imageUrl" -> content.imageUrl2 = jsonReader.nextString()

                            "teaserText" -> content.teaserText = jsonReader.nextString()
                            "publisherId" -> content.publisherId = jsonReader.nextString()
                            "publisherImage" -> content.publisherImage = jsonReader.nextString()
                            "badges" -> {
                                if (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
                                    jsonReader.beginArray()
                                    jsonReader.endArray()
                                }
                            }
                            "items" -> {
                                if (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
                                    jsonReader.beginArray()
                                    jsonReader.endArray()
                                }
                            }
                        }
                    }

                    contentList.add(content)

                    jsonReader.endObject()
//                    Log.d("BrochuresAct", "Next: end of object")
                    continue
                }

            } else {
                jsonReader.skipValue()
//                Log.d("BrochuresAct", "Content is null")
                continue
            }

        }

//        Log.d("BrochuresAct", "ContentList final size: ${contentList.size}")

        return ContentList(contentList)
    }
}