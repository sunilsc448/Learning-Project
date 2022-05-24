package ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import listeners.IClickListener

open class BaseViewHolder(view: View, listener: IClickListener?): RecyclerView.ViewHolder(view) {
    var mView = view
    var mListener = listener
}