package repository

import DataSource.IRemoteDataSource
import Utility.RetrofitClient
import pojos.ActorResponse
import pojos.SampleRetrofitBody
import retrofit2.Callback

object ListRepository : IRemoteDataSource{
    override fun fetchActorsDynamicUrl(actorsCallBack: Callback<ActorResponse>, url:String) {
        makeAnApiCallDynamicUrl(actorsCallBack, url)
    }

    private fun makeAnApiCallDynamicUrl(actorsCallBack: Callback<ActorResponse>, url:String) {
        val call = RetrofitClient.getAPI().getActorsWithDynamicUrl(url)
        call.enqueue(actorsCallBack)
    }

    override fun fetchActorsDynamicUrlWithHeader(actorsCallBack: Callback<ActorResponse>,token:String, url:String) {
        makeAnApiCallDynamicUrlWithHeader(actorsCallBack, token, url)
    }

    private fun makeAnApiCallDynamicUrlWithHeader(actorsCallBack: Callback<ActorResponse>,token:String,  url:String) {
        val call = RetrofitClient.getAPI().getActorsWithDynamicUrlWithHeader(token, url)
        call.enqueue(actorsCallBack)
    }

    override fun fetchActors(actorsCallBack: Callback<ActorResponse>, industryType: String, priorityIndustry: String) {
        makeAnApiCall(actorsCallBack, industryType, priorityIndustry)
    }

    private fun makeAnApiCall(actorsCallBack: Callback<ActorResponse>,industryType:String, priorityIndustry:String) {
        val call = RetrofitClient.getAPI().getActors(industryType, priorityIndustry)
        call.enqueue(actorsCallBack)
    }

    override fun fetchActorsWithHeader(actorsCallBack: Callback<ActorResponse>, token: String, industryType: String, priorityIndustry: String) {
        makeAnApiCallWithHeader(actorsCallBack,token, industryType, priorityIndustry)
    }

    private fun makeAnApiCallWithHeader(actorsCallBack: Callback<ActorResponse>, token:String, industryType:String, priorityIndustry:String) {
        val call = RetrofitClient.getAPI().getActorsWithHeader(token, industryType, priorityIndustry)
        call.enqueue(actorsCallBack)
    }

    override fun fetchActorsWithHeaderPost(actorsCallBack: Callback<ActorResponse>, token: String, model:SampleRetrofitBody) {
        makeAnApiCallWithHeaderPost(actorsCallBack, token, model)
    }

    private fun makeAnApiCallWithHeaderPost(actorsCallBack: Callback<ActorResponse>, token:String,model:SampleRetrofitBody) {
        val call = RetrofitClient.getAPI().getActorsPost(token, model)
        call.enqueue(actorsCallBack)
    }
}