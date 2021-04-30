package stas.batura.bookreader.ui.main.read

import android.os.Bundle
import android.text.TextPaint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.read_fragment.*
import stas.batura.bookreader.MainActivity
import stas.batura.bookreader.R
import stas.batura.bookreader.ui.main.controls.ControlsViewModel
import stas.batura.bookreader.ui.main.utils.PageSplitter
import stas.batura.bookreader.ui.main.utils.ZoomOutPageTransformer
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

private val TAG = "ReadFragment.kt"

@AndroidEntryPoint
class ReadFragment: Fragment() {

    private val TAG = "MainActivity.kt"

//    private var pagesView: ViewPager2? = null

    private lateinit var pagerAdapter: ScreenSlidePagerAdapter

    private lateinit var testText: String

    private lateinit var viewModel: ReadViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(this).get(ReadViewModel::class.java)
        val root = inflater.inflate(R.layout.read_fragment, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val input: InputStream = resources.openRawResource(R.raw.koshki_test)

        val reader = BufferedReader(InputStreamReader(input))

        val result = StringBuilder()

        var line = reader.readLine()

        while (line != null) {
            result.append(line)
            result.append("\n")
            line = reader.readLine()
        }

        testText = result.toString()

        getPages()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()


    }

    private fun getPages() {
        viewPager.setPageTransformer(ZoomOutPageTransformer())

        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//        pagesView!!.adapter = pagerAdapter
//        pagesView!!.adapter = ViewPagerAdapter()

        //        // to get ViewPager width and height we have to wait global layout
        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val pageSplitter = PageSplitter(
                    viewPager.getWidth(),
                    viewPager.getHeight(),
                    1.toFloat(),
                    0
                )
                val textPaint = TextPaint()
                textPaint.textSize = resources.getDimension(R.dimen.text_size)
                pageSplitter.append(testText, textPaint)
                val pages = pageSplitter.getPages()
                pagerAdapter = ScreenSlidePagerAdapter(requireActivity().supportFragmentManager, pages)
                viewPager.setAdapter(pagerAdapter)
                viewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this)

                for (page in pages) {
                    pagerAdapter.addFragment(PageFragment.newInstance((page.toString())))
                }

                viewPager.currentItem = 3
            }
        })
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

        override fun getItemId(position: Int): Long {
            Log.d(TAG, "getItemId: $position" )
            return super.getItemId(position)
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