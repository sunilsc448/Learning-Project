package ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlintutorial.R
import com.squareup.picasso.Picasso
import listeners.IClickListener
import pojos.Actor

class ActorViewHolder(view: View, listener:IClickListener?):BaseViewHolder(view, listener) {
    private var imgVw:ImageView = mView.findViewById(R.id.image_view)
    private var nameVw:TextView = mView.findViewById(R.id.name_text)
    private var iconImgVw:ImageView = mView.findViewById(R.id.info_image_view)
    private var ageTxtVw:TextView = mView.findViewById(R.id.age_text)
    private var industryTxtVw:TextView = mView.findViewById(R.id.industry_text)

    fun setView(position: Int, actor: Actor){
        Picasso.with(imgVw.context).load(actor.photo).placeholder(R.drawable.placeholder_image).into(imgVw)
        nameVw.text = actor.name
        ageTxtVw.text = String.format(imgVw.resources.getString(R.string.years_format_string), actor.age)
        industryTxtVw.text = actor.industry
        iconImgVw.setOnClickListener {
            mListener?.onClick(position, actor)
        }
    }
}