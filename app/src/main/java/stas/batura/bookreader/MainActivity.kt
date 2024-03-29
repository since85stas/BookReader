package stas.batura.bookreader

import android.os.Bundle
import android.text.TextPaint
import android.util.Log
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import stas.batura.bookreader.ui.main.utils.PageSplitter
import stas.batura.bookreader.ui.main.utils.ZoomOutPageTransformer
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity.kt"

//    private val NUM_PAGES = 2;
//
//    private var pagesView: ViewPager2? = null
//
//    private lateinit var pagerAdapter: ScreenSlidePagerAdapter
//
//    private lateinit var testText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_podcastlist
//            ), drawerLayout
//        )
//
//        val input: InputStream = resources.openRawResource(R.raw.koshki_test)
//
//        val reader = BufferedReader(InputStreamReader(input))
//
//        val result = StringBuilder()
//
//        var line = reader.readLine()
//
//        while (line != null) {
//            result.append(line)
//            result.append("\n")
//            line = reader.readLine()
//        }
//
//        testText = result.toString()

        Log.d(TAG, "onCreate: text")
    }

//    override fun onStart() {
//        pagesView = findViewById(R.id.pages)
////        pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager, null)
//        pagesView!!.setPageTransformer(ZoomOutPageTransformer())
//
//        pagesView!!.orientation = ViewPager2.ORIENTATION_HORIZONTAL
////        pagesView!!.adapter = pagerAdapter
////        pagesView!!.adapter = ViewPagerAdapter()
//
//        //        // to get ViewPager width and height we have to wait global layout
//        pagesView!!.getViewTreeObserver().addOnGlobalLayoutListener(object :
//            OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                val pageSplitter = PageSplitter(
//                    pagesView!!.getWidth(),
//                    pagesView!!.getHeight(),
//                    1.toFloat(),
//                    0
//                )
//                val textPaint = TextPaint()
//                textPaint.textSize = resources.getDimension(R.dimen.text_size)
//                pageSplitter.append(testText, textPaint)
//                val pages = pageSplitter.getPages()
//                pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager, pages)
//                pagesView!!.setAdapter(pagerAdapter)
//                pagesView!!.getViewTreeObserver().removeOnGlobalLayoutListener(this)
//
//                for (page in pages) {
//                    pagerAdapter.addFragment(PageFragment.newInstance((page.toString())))
//                }
//            }
//        })
//
//
//
//        super.onStart()
//    }

    /**
     * A simple pager adapter that represents 2 ScreenSlidePageFragment objects, in
     * sequence.
     */
//    inner class ScreenSlidePagerAdapter(
//        val fragmentManager: FragmentManager,
//        val pageTexts: List<CharSequence>?
//    ) :
//            FragmentStateAdapter(fragmentManager, this@MainActivity.lifecycle) {
//
//        private var arrayList: ArrayList<Fragment> = ArrayList()
//
//        override fun createFragment(position: Int): Fragment {
//            Log.d(TAG, "fragment pos=${position} created")
//            Log.d(TAG, "createFragment: ")
//            return arrayList.get(position)
//        }
//
//        fun addFragment(fragment: Fragment?) {
//            arrayList.add(fragment!!)
//        }
//
//
//
//        override fun getItemCount(): Int {
//            return pageTexts!!.size
//        }
//
//        fun removeFragments() {
//            arrayList = ArrayList()
//        }
//
//    }

}