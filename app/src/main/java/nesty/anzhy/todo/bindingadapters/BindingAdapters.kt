package nesty.anzhy.todo.bindingadapters

import android.view.View
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import nesty.anzhy.todo.R
import nesty.anzhy.todo.data.models.Priority
import nesty.anzhy.todo.data.models.ToDoData
import nesty.anzhy.todo.fragments.list.ListFragmentDirections

class BindingAdapters {

    companion object{

        @BindingAdapter("navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean){
            view.setOnClickListener{
                if(navigate){
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>){
            when(emptyDatabase.value){
                true->view.visibility = View.VISIBLE
                false->view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(view: Spinner, priority: Priority){
            when(priority){
                Priority.HIGH->view.setSelection(0)
                Priority.MEDIUM->view.setSelection(1)
                Priority.LOW->view.setSelection(2)
            }
        }

        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(view: CardView, priority: Priority){
            when(priority){
                Priority.HIGH->view.setCardBackgroundColor(ContextCompat.getColor(view.context, R.color.red))
                Priority.MEDIUM->view.setCardBackgroundColor(ContextCompat.getColor(view.context, R.color.yellow))
                Priority.LOW->view.setCardBackgroundColor(ContextCompat.getColor(view.context, R.color.green))
            }
        }

        @BindingAdapter("android:setDataToUpdateFunction")
        @JvmStatic
        fun setDataToUpdateFunction(view: ConstraintLayout, currentItem: ToDoData){
            view.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                view.findNavController().navigate(action)
            }
        }

    }
}