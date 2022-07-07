package DataSource

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import pojos.Actor
import pojos.ActorResponse
import pojos.SampleRetrofitBody
import retrofit2.Callback

interface IRemoteDataSource {
    fun fetchActorsDynamicUrl(actorsCallBack: Callback<ActorResponse>, url:String)
    fun fetchActorsDynamicUrlWithHeader(actorsCallBack: Callback<ActorResponse>,token:String, url:String)
    fun fetchActors(actorsCallBack: Callback<ActorResponse>, industryType:String, priorityIndustry:String)
    fun fetchActorsWithHeader(actorsCallBack: Callback<ActorResponse>, token:String, industryType:String, priorityIndustry:String)
    fun fetchActorsWithHeaderPost(actorsCallBack: Callback<ActorResponse>, token:String, model:SampleRetrofitBody)
    fun fetchActorsDirect(scope: CoroutineScope, liveData: MutableLiveData<List<Actor>>)
}