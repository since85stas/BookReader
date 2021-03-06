package stas.batura.bookreader.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import stas.batura.bookreader.R
import kotlin.math.log

public val ARG = "teststring"

class MainFragment : Fragment() {

    private lateinit var arg: String

    companion object {
        fun newInstance(arg: String) =             MainFragment().apply {
            arguments = Bundle().apply {
                putString(ARG, arg)
            }
        }
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.main_fragment, container, false)
//        view.findViewById<TextView>(R.id.message).text = arg
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        Log.d("fragm", "onActivityCreated: " + arg)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arg = arguments?.getString(ARG) ?: ""
        view.findViewById<TextView>(R.id.message).text = arg
    }
}