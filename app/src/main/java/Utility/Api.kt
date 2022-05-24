package Utility

import pojos.Actor
import pojos.ActorResponse
import pojos.SampleRetrofitBody
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET
    fun getActorsWithDynamicUrl(@Url url:String):Call<ActorResponse>
    @GET
    fun getActorsWithDynamicUrlWithHeader(@Header("mock_token")token:String,@Url url:String):Call<ActorResponse>
    @GET("/actors")
    fun getActors(@Query("industry")industryType:String, @Query("priority")priorityIndustry:String):Call<ActorResponse>
    @GET("/actors")
    fun getActorsWithHeader(@Header("mock_token")token:String, @Query("industry")industryType:String, @Query("priority")priorityIndustry:String):Call<ActorResponse>
    @POST("/actors")
    fun getActorsPost(@Header("mock_token")token:String, @Body model:SampleRetrofitBody):Call<ActorResponse>
}