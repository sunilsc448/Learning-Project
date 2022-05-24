package DataSource

import pojos.ActorResponse
import pojos.SampleRetrofitBody
import retrofit2.Callback

interface IRemoteDataSource {
    fun fetchActorsDynamicUrl(actorsCallBack: Callback<ActorResponse>, url:String)
    fun fetchActorsDynamicUrlWithHeader(actorsCallBack: Callback<ActorResponse>,token:String, url:String)
    fun fetchActors(actorsCallBack: Callback<ActorResponse>, industryType:String, priorityIndustry:String)
    fun fetchActorsWithHeader(actorsCallBack: Callback<ActorResponse>, token:String, industryType:String, priorityIndustry:String)
    fun fetchActorsWithHeaderPost(actorsCallBack: Callback<ActorResponse>, token:String, model:SampleRetrofitBody)
}