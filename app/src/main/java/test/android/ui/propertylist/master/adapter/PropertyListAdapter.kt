package test.android.ui.propertylist.master.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import test.android.databinding.ListItemPremiumBinding
import test.android.databinding.ListItemStandardBinding
import test.android.repository.apimodels.Property

/**
 * Adapter class for recycler view. The list has 2 types of views - Standard and Premium
 * @param items - list of properties
 * @param listener - callback listener to handle click events
 *
 * created by fahad on 21/08/2018
 */
class PropertyListAdapter(private var items: List<Property>, private var listener: OnItemClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //types of views
    companion object {
        const val TYPE_STANDARD = 0
        const val TYPE_PREMIUM = 1
    }

    //Instantiate the view holder based on the type of view.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder = when(viewType){
            TYPE_STANDARD -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemStandardBinding.inflate(layoutInflater, parent, false)
                ViewHolderStandard(binding)
            }
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPremiumBinding.inflate(layoutInflater, parent, false)
                ViewHolderPremium(binding)
            }
        }

        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    //Returns the view type based on isPremium flag.
    override fun getItemViewType(position: Int): Int {
        return when(items.get(position).isPremium){
            1 -> TYPE_PREMIUM
            else -> TYPE_STANDARD
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            TYPE_STANDARD -> {
                val viewHolderStandard = holder as ViewHolderStandard
                viewHolderStandard.bind(items[position], listener)
            }
            else -> {
                val viewHolderPremium = holder as ViewHolderPremium
                viewHolderPremium.bind(items[position], listener)
            }
        }
    }

    //Replace the current data with updated data
    fun replaceData(list: List<Property>){
        items = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClick(itemId: String)
    }

    class ViewHolderStandard(private var binding: ListItemStandardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Property, listener: OnItemClickListener){
            binding.item = item
            binding.root.setOnClickListener({
                _ -> listener.onItemClick(item.id)
            })

            binding.executePendingBindings()
        }
    }

    class ViewHolderPremium(private var binding: ListItemPremiumBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Property, listener: OnItemClickListener){
            binding.item = item
            binding.root.setOnClickListener({
                _ -> listener.onItemClick(item.id)
            })

            binding.executePendingBindings()
        }
    }
}