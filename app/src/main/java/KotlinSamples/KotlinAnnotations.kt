package KotlinSamples

import com.example.annotations.Encapsulate
import kotlin.reflect.full.findAnnotation

class KotlinAnnotations {
    init {
        val sourceDataImpl = SourceDataImpl::class
        val annotation = sourceDataImpl.findAnnotation<SourceData>()
        println("annotation name > ${annotation?.annotationClass?.simpleName}")
        println("author > ${annotation?.author}")
        println("version > ${annotation?.version}")
        println("last modified > ${annotation?.lastModified}")
    }
}

/*
@Target
CLASS — classes, interfaces, objects and annotation classes
FUNCTION— methods not including constructors
FIELD — field variables including the backing field
TYPE — Any expression
 */

/*
@Retention
SOURCE— Annotation is only valid in compile time and is removed in binary output. This is similar to Java’s SOURCE retention.
BINARY— Annotation persists in binary output but cannot be accessed via reflection.
RUNTIME — Annotation persists in binary output and can be used via reflection. This is the default retention policy.
 */

@SourceData(author = "Sunil", version = 1, lastModified = "july 6, 2022")
class SourceDataImpl{
    fun getRegards() = println("Hi, Good Day")
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class SourceData(val author:String, val version:Int, val lastModified:String)

@Encapsulate
data class APModel(private val name:String, val age:Int)

