package stas.batura.bookreader.ui.main.controls

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import stas.batura.bookreader.data.IRepository

class ControlsViewModel @ViewModelInject constructor(val repository: IRepository) : ViewModel() {

    init {
        Log.d("vm", "test: $repository")
    }


}