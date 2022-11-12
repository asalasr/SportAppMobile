package com.example.sportapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.R
import com.example.sportapp.adapters.CalendarioAdapter
import com.example.sportapp.databinding.FragmentCalendarioListarBinding
import com.example.sportapp.models.Calendario
import com.example.sportapp.viewmodels.CalendarioViewModel


class CalendarioListarFragment : Fragment() {
    private var _binding: FragmentCalendarioListarBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CalendarioViewModel
    private var viewModelAdapter: CalendarioAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarioListarBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter =CalendarioAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.fragmentsRvcalendario
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        Log.i("CalenListarFragment","Habilita los Observe")
        viewModel = ViewModelProvider(this, CalendarioViewModel.Factory(activity.application)).get(
            CalendarioViewModel::class.java
        )
        viewModel.calendariovm.observe(viewLifecycleOwner, Observer<List<Calendario>> {
            it.apply {
                viewModelAdapter!!.collectors = this
            }
        })


        viewModel.eventNetworkError.observe(
            viewLifecycleOwner,
            Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}