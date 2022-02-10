package com.snilloc.bonialinterview.data.network

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.snilloc.bonialinterview.data.network.model.*
import javax.inject.Inject

class ContentAdapter @Inject constructor(private val gson: Gson) : TypeAdapter<ContentList>() {
    override fun write(out: JsonWriter?, value: ContentList?) {}

    override fun read(jsonReader: JsonReader?): ContentList {
        /**
         * Manually parse the data from the json response
         */
        val contentList = mutableListOf<Content>()

        while (jsonReader?.hasNext() == true) {

            /**
             * We are only concerned with either an Object or an array of Objects
             */
            if (jsonReader.peek() == JsonToken.BEGIN_ARRAY || jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
                val nextObject = jsonReader.peek()?.name

                /**
                 * The first two contents from the json are arrays
                 * Arrays can easily be deserialized using Gson and the Content data class
                 */
                if (nextObject == JsonToken.BEGIN_ARRAY.toString()) {
//                    Log.d("BrochuresAct", "Next array name: $nextObject")

                    jsonReader.beginArray()

                    while (jsonReader.hasNext()) {
                        val contentStuff: Content =
                            gson.fromJson(jsonReader, Content::class.java)
                        contentList.add(contentStuff)
                    }

                    /**
                     * Current array of Content has been deserialized
                     * "close" the array and move to the next value
                     */
                    jsonReader.endArray()
                    continue
                } else {
                    /**
                     * Objects need to be deserialized manually
                     */
                    jsonReader.beginObject()

                    val content = Content()

                    while (jsonReader.hasNext()) {

                        /**
                         * Move to each value of the object checking the name against those in the Content data class
                         * Create a Content object from the deserialized values from the json
                         * Content() has nullable values so as to make this step easier
                         */
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

                            /**
                             * The arrays inside the Content object aren't useful to the app
                             * so we just "skip over them" by moving through the json stream
                             */
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
                    /**
                     * Add the created Content object from the just deserialized json object to the overall list of Contents
                     * Then move on to the next value in the json stream
                     */
                    contentList.add(content)

                    jsonReader.endObject()
                    continue
                }

            } else {
                /**
                 * Content found is null, so skip to the next value
                 */
                jsonReader.skipValue()
                continue
            }

        }
        /**
         * When there is no other value in the stream,
         * Return the list of the deserialized Content objects
         */

        return ContentList(contentList)
    }
}