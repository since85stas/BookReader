package stas.batura.bookreader.ui.main.controls

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import stas.batura.bookreader.R
import stas.batura.bookreader.databinding.ControlFragmentBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.text.Typography.dagger

@AndroidEntryPoint
class ControlsFragment(): Fragment() {

    lateinit var viewModel: ControlsViewModel

    lateinit var bindings: ControlFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         viewModel =
            ViewModelProvider(this).get(ControlsViewModel::class.java)

        val root = inflater.inflate(R.layout.control_fragment, container, false)

        bindings = DataBindingUtil.inflate(inflater,
            R.layout.control_fragment,
            container,
            false)

        bindings.lifecycleOwner = viewLifecycleOwner

        bindings.viewModel = viewModel

        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bindings.readButton.setOnClickListener {
            goToReadFragm()
        }

        super.onViewCreated(view, savedInstanceState)
    }


    private fun goToReadFragm() {
        val action = ControlsFragmentDirections.actionControlsFragmentToReadFragment()
        findNavController().navigate(action)
    }

}