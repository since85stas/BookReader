package stas.batura.bookreader.ui.main.read

import android.os.Bundle
import android.text.TextPaint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.databinding.DataBindingUtil
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
import stas.batura.bookreader.databinding.ReadFragmentBinding
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

    private lateinit var pagerAdapter: ScreenSlidePagerAdapter

    private lateinit var testText: String

    private lateinit var viewModel: ReadViewModel

    private lateinit var bindings: ReadFragmentBinding

    private var startPage: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(this).get(ReadViewModel::class.java)

        bindings = DataBindingUtil.inflate(inflater,
        R.layout.read_fragment,
        container,
        false)

        bindings.viewModel = viewModel

        bindings.lifecycleOwner = viewLifecycleOwner

        startPage = viewModel.repository.getLastPage()

        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        readData()

        getPages()

        viewModel.currentPage.observe(viewLifecycleOwner) {
            viewPager.currentItem = it
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Теперь только необходимое
                viewModel.savePageToDb(position)
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    /**
     * считываем данные
     */
    private fun readData() {
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
    }

    private fun getPages() {
        viewPager.setPageTransformer(ZoomOutPageTransformer())

        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

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

                viewPager.currentItem = startPage
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