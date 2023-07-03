import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.masterandroid.BWareMobileApp.GetOrganTitle
import com.masterandroid.BWareMobileApp.R

class LanguageAdapterOrgans(private val itemList: List<GetOrganTitle>,
                            private val onItemClick: (selectedLanguage: GetOrganTitle) -> Unit) :

    RecyclerView.Adapter<LanguageAdapterOrgans.ViewHolder>() {

    private var listener: OnItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.deseaseTitle)
        val getStartedButton: Button = itemView.findViewById(R.id.get_started_button)

        init {
            getStartedButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val languageData = itemList[position]
                    onItemClick(languageData)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.titleTextView.text = item.name
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    interface OnItemClickListener {
        fun onItemClick(item: GetOrganTitle)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}
