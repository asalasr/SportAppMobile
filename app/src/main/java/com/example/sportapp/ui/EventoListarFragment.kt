package com.example.sportapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.R
import com.example.sportapp.adapters.EventoAdapter
import com.example.sportapp.databinding.FragmentEventoListarBinding
import com.example.sportapp.models.Evento
import com.example.sportapp.viewmodels.EventoViewModel

class EventoListarFragment : Fragment() {
    private var _binding: FragmentEventoListarBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: EventoViewModel
    private var viewModelAdapter: EventoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventoListarBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = EventoAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.fragmentsRvcollector
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        Log.i("NetworkServices","LLEGO a consultar ENDPOINT")
        viewModel = ViewModelProvider(this, EventoViewModel.Factory(activity.application)).get(
            EventoViewModel::class.java
        )
        viewModel.eventovm.observe(viewLifecycleOwner, Observer<List<Evento>> {
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