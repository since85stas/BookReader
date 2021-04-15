package stas.batura.bookreader.ui.main.read

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import stas.batura.bookreader.MainActivity
import stas.batura.bookreader.R

private val TAG = "ReadFragment.kt"

class ReadFragment: Fragment() {

    private val TAG = "MainActivity.kt"

    private var pagesView: ViewPager2? = null

    private lateinit var pagerAdapter: MainActivity.ScreenSlidePagerAdapter

    private lateinit var testText: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.read_fragment, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * A simple pager adapter that represents 2 ScreenSlidePageFragment objects, in
     * sequence.
     */
    inner class ScreenSlidePagerAdapter(
        val fragmentManager: FragmentManager,
        val pageTexts: List<CharSequence>?
    ) :
        FragmentStateAdapter(fragmentManager, viewLifecycleOwner.lifecycle) {

        private var arrayList: ArrayList<Fragment> = ArrayList()

        override fun createFragment(position: Int): Fragment {
            Log.d(TAG, "fragment pos=${position} created")
            Log.d(TAG, "createFragment: ")
            return arrayList.get(position)
        }

        fun addFragment(fragment: Fragment?) {
            arrayList.add(fragment!!)
        }



        override fun getItemCount(): Int {
            return pageTexts!!.size
        }

        fun removeFragments() {
            arrayList = ArrayList()
        }

    }

}