package stas.batura.bookreader.ui.main.read

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import stas.batura.bookreader.data.IRepository

class ReadViewModel @ViewModelInject constructor(val repository: IRepository) : ViewModel() {

    val currentPage : MutableLiveData<Int> = MutableLiveData()


    init {
        Log.d("vm", "test: $repository")
    }

    fun setCurrentPage(page: Int) {
        currentPage.value = page
    }
}