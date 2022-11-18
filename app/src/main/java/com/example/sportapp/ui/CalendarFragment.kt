package com.example.sportapp.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.sportapp.R
import com.example.sportapp.adapters.CalendarAdapter
import com.example.sportapp.viewmodels.CalendarViewModel
import java.util.ArrayList
import java.util.HashMap
import androidx.lifecycle.Observer
import com.example.sportapp.models.Calendario

class CalendarFragment : Fragment() {

    private var calendarAdapter: CalendarAdapter? = null
    private lateinit var diasList : List<String>
    private lateinit var agendaList: HashMap<String, List<String>>
    private lateinit var viewModel: CalendarViewModel

    companion object {
        fun newInstance() = CalendarFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View =inflater.inflate(R.layout.fragment_calendar, container, false)
        Log.i("CalendarFragment","onCreateView - ENTRO A onCreateView")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(this, CalendarViewModel.Factory(activity.application)).get(
            CalendarViewModel::class.java
        )

        viewModel.calendariovm.observe(viewLifecycleOwner, Observer<List<Calendario>> {
            it.apply {
                Log.i("CalendarFragment","modelos="+viewModel.calendariovm.value)
                showlist()
                calendarAdapter = CalendarAdapter(activity.applicationContext,diasList,agendaList)
                var expandableListView : ExpandableListView? = view?.findViewById(R.id.eListView);
                if (expandableListView != null) {
                    expandableListView.setAdapter(calendarAdapter)
                }
            }
        })
        viewModel.eventNetworkError.observe(
            viewLifecycleOwner,
            Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    fun showlist(){

        diasList = ArrayList()
        agendaList = HashMap()

        //Se cargan las fechas
        for (dia in viewModel.calendariovm.value!!){
            if (!diasList.contains(dia.fecha)){
                (diasList as ArrayList<String>).add(dia.fecha)
            }
        }
        //Se asocian actividades a fechas
        for (dial in diasList){
            val agenda : MutableList<String> = ArrayList()
            for (dia in viewModel.calendariovm.value!!){
                if(dial.contentEquals(dia.fecha)) {
                    agenda.add(dia.actividad + '-' + dia.detalle)
                }
            }
            agendaList[dial] = agenda
        }

        /*(diasList as ArrayList<String>).add("2021-11-10")
        (diasList as ArrayList<String>).add("2021-11-15")
        val agenda1 : MutableList<String> = ArrayList()
        agenda1.add("Actividad1")
        val agenda2 : MutableList<String> = ArrayList()
        agenda2.add("Actividad1")
        agendaList[diasList[0]] = agenda1
        agendaList[diasList[1]] = agenda2*/
    }
}