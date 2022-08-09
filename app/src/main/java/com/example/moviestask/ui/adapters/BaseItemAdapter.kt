package com.example.moviestask.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseItemAdapter<T> : RecyclerView.Adapter<BaseItemAdapter.MyViewHolder>() {
    /**
     * Adapter items list
     * @return List items
     */
    abstract fun adapterItems(): ArrayList<T>

    /**
     * Adapter list has more data or not
     * @return True if adapter can load more data
     */
    abstract fun hasMore(): Boolean

    /**
     * Add new data to adapter
     */
    open fun addItems(items: List<T>) {
        val myList = adapterItems()
        var count = myList.size
        // Remove loading indicator dummy item
        if (count > 0 && hasMore()) {
            count--
            myList.removeAt(count)
            notifyItemRemoved(count)
        }
        // Insert extra data
        myList.addAll(items)
        notifyItemRangeInserted(count, items.size)
    }

    /**
     * Clear adapter items
     */
    open fun clearList() {
        val myList = adapterItems()
        val count = myList.size
        myList.clear()
        notifyItemRangeRemoved(0, count)
    }

    /**
     * Add item to adapter at given index
     * @param item new item to be inserted
     * @param index insertion index
     */
    open fun addItem(item: T, index: Int) {
        adapterItems().add(index, item)
        notifyItemInserted(index)
    }

    /**
     * Remove item at given index
     * @param index removal index
     */
    open fun removeItem(index: Int) {
        adapterItems().removeAt(index)
        notifyItemRemoved(index)
    }

    /**
     * Move item at index [from] to index [to]
     * @param from Item initial position
     * @param to Item final position
     */
    open fun moveItem(from: Int, to: Int) {
        val myList = adapterItems()
        val temp = myList[from]
        myList[from] = myList[to]
        myList[to] = temp
        notifyItemMoved(from, to)
    }

    /**
     * Update [item] at index [index]
     * @param item new item
     * @param index index to put new item
     */
    open fun updateItem(item: T, index: Int) {
        val myList = adapterItems()
        myList[index] = item
        notifyItemChanged(index)
    }

    /**
     * Get [item] at index [index]
     * @param index index of item to return
     */
    open fun getItemAt(index: Int): T {
        return adapterItems()[index]
    }

    open class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

}