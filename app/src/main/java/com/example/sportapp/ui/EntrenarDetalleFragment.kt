package com.example.sportapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sportapp.R
import com.example.sportapp.models.AsignarDetallePlan
import com.example.sportapp.viewmodels.EntrenamientoViewModel


class EntrenarDetalleFragment : Fragment() {

    private lateinit var entrenamientoViewModelClass: EntrenamientoViewModel
    private lateinit var plan: AsignarDetallePlan

    companion object {
        fun newInstance() = EntrenarDetalleFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_entrenar_detalle, container, false)
        Log.i("EntrenarDetalleFragment","onCreateView - ENTRO A onCreateView")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("EntrenarDetalleFragment","onActivityCreated inicializa viewmodel")

        val playPause: Button? = view?.findViewById(R.id.buttonGuardadDia)

        if (playPause != null) {
            playPause.setOnClickListener(View.OnClickListener {
                guardarDia()
            })
        }

        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        entrenamientoViewModelClass = ViewModelProvider(this, EntrenamientoViewModel.Factory(activity.application)).get(
            EntrenamientoViewModel::class.java
        )

        plan= arguments?.get("plan") as AsignarDetallePlan

        val entrenarDia: TextView? = view?.findViewById(R.id.entrenarDia)
        entrenarDia!!.text= "DIA "+plan.numDia
        val entrenarEstado: TextView? = view?.findViewById(R.id.entrenarEstado)
        entrenarEstado!!.text= plan.estado
        val entrenarMarcaStreet: TextView? = view?.findViewById(R.id.entrenarMarcaStreet)
        entrenarMarcaStreet!!.text= plan.marcaStreet
        val entrenarFechaInicio: TextView? = view?.findViewById(R.id.entrenarFechaInicio)
        entrenarFechaInicio!!.text= plan.fechaInicio
        val entrenarFechaFin: TextView? = view?.findViewById(R.id.entrenarFechaFin)
        entrenarFechaFin!!.text= plan.fechaFin
        val entrenarCalorias: TextView? = view?.findViewById(R.id.entrenarCalorias)
        entrenarCalorias!!.text= plan.calorias.toString()
        val entrenarVelocidadMaxima: TextView? = view?.findViewById(R.id.entrenarVelocidadMaxima)
        entrenarVelocidadMaxima!!.text= plan.velocidadMaxima.toString()
        val entrenarDistanciaRecorrida: TextView? = view?.findViewById(R.id.entrenarDistanciaRecorrida)
        entrenarDistanciaRecorrida!!.text= plan.distanciaRecorrida.toString()

    }

    fun guardarDia() {

        val asignarDetallePlan: AsignarDetallePlan? = validateFormDia()

        if (asignarDetallePlan != null) {
            Log.i("EntrenarDetalleFragment", "Click Button guardar")
            Toast.makeText (entrenamientoViewModelClass.getApplication() , "Actualizando plan diario...", Toast.LENGTH_LONG).show()

            entrenamientoViewModelClass = EntrenamientoViewModel(entrenamientoViewModelClass.getApplication())

            entrenamientoViewModelClass.putActualizarEntrenamiento(
                asignarDetallePlan.idAsignarPlanId,
                asignarDetallePlan.idDetallePlan,
                asignarDetallePlan.calorias,
                asignarDetallePlan.estado,
                asignarDetallePlan.fechaFin,
                asignarDetallePlan.distanciaRecorrida,
                asignarDetallePlan.fechaInicio,
                asignarDetallePlan.idDeportista,
                asignarDetallePlan.ids,
                asignarDetallePlan.marcaStreet,
                asignarDetallePlan.numDia,
                asignarDetallePlan.velocidadMaxima,
                {
                    if (it) {
                        showResponseAlert(
                            R.string.Plan_diario_actualizado,
                            android.R.drawable.ic_dialog_info,
                            true
                        )
                    }
                }, {
                    showResponseAlert(
                        R.string.Actualizacion_fallida,
                        android.R.drawable.ic_dialog_alert,
                        false
                    )
                })
        } else {
            return
        }
    }

    fun validateFormDia(): AsignarDetallePlan? {


        val entrenarFechaInicio: TextView? = view?.findViewById(R.id.entrenarFechaInicio)
        val entrenarFechaFin: TextView? = view?.findViewById(R.id.entrenarFechaFin)
        val entrenarCalorias: TextView? = view?.findViewById(R.id.entrenarCalorias)
        val entrenarDistanciaRecorrida: TextView? = view?.findViewById(R.id.entrenarDistanciaRecorrida)
        val entrenarVelocidadMaxima: TextView? = view?.findViewById(R.id.entrenarVelocidadMaxima)

        var cal :Double=0.0
        var vel :Double=0.0
        var dist :Double=0.0
        var finicio : String=""
        var ffin : String=""
        Log.i("EntrenarDetalleFragment", "AsignarDetallePlan - validateFormDia")

        if (entrenarCalorias != null) {
            Log.i("EntrenarDetalleFragment", "AsignarDetallePlan - cal:"+entrenarCalorias.text.toString())
            cal = entrenarCalorias.text.toString().toDouble()
        }

        if (entrenarVelocidadMaxima != null) {
            vel = entrenarVelocidadMaxima.text.toString().toDouble()
        }

        if (entrenarDistanciaRecorrida != null) {
            dist = entrenarDistanciaRecorrida.text.toString().toDouble()
        }

        if (entrenarFechaInicio != null) {
            finicio = entrenarFechaInicio.text.toString()
        }

        if (entrenarFechaFin != null) {
            ffin = entrenarFechaFin.text.toString()
        }
        Log.i("EntrenarDetalleFragment", "AsignarDetallePlan - devolver asignarDetallePlan ")

        var asignarDetallePlan: AsignarDetallePlan? = AsignarDetallePlan(
            fechaFin = ffin,
            fechaInicio = finicio,
            calorias = cal,
            distanciaRecorrida = dist,
            velocidadMaxima = vel,
            ids = plan.ids,
            idDeportista = plan.idDeportista,
            idAsignarPlanId = plan.idAsignarPlanId,
            idDetallePlan = plan.idDetallePlan,
            numDia = plan.numDia,
            marcaStreet = plan.marcaStreet,
            estado = plan.estado
        )
        return asignarDetallePlan

    }

    fun showResponseAlert(message: Int, icon: Int, goBack: Boolean) {
        val builder = AlertDialog.Builder(entrenamientoViewModelClass.getApplication())

        builder.setTitle("Actualizar rutina")
        builder.setMessage(message)
        builder.setIcon(icon)
        Toast.makeText (entrenamientoViewModelClass.getApplication() , "Estado actulizado.", Toast.LENGTH_LONG).show()

        builder.setPositiveButton("OK") { dialogInterface, which ->
            if (goBack) {
                //val intent = Intent(this, EntrenarActivity::class.java)
                //startActivity(intent)
                val fragmentB = EntrenarListarFragment()
                activity?.getSupportFragmentManager()?.beginTransaction()
                    ?.replace(R.id.fragments_rvcollector, fragmentB, "fragmnetId")
                    ?.commit();
            }
        }
        //val alertDialog = builder.create()
        //alertDialog.setCancelable(false)
       // alertDialog.show()
    }

}