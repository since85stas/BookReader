package stas.batura.bookreader

import android.os.Bundle
import android.text.TextPaint
import android.util.Log
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import stas.batura.bookreader.ui.main.MainFragment
import stas.batura.bookreader.ui.main.TextPagerAdapter
import stas.batura.bookreader.ui.main.ViewPagerAdapter
import stas.batura.bookreader.ui.main.utils.PageSplitter
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity.kt"

    private val NUM_PAGES = 2;

    private var pagesView: ViewPager2? = null

    private lateinit var pagerAdapter: ScreenSlidePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        pagesView = findViewById(R.id.pages) as ViewPager

//        // to get ViewPager width and height we have to wait global layout
//        pagesView!!.getViewTreeObserver().addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                val pageSplitter = PageSplitter(pagesView!!.getWidth(), pagesView!!.getHeight(), 1.toFloat(), 0)
//                val textPaint = TextPaint()
//                textPaint.textSize = resources.getDimension(R.dimen.text_size)
//                for (i in 0..999) {
//                    pageSplitter.append("Hello, ", textPaint)
//                    textPaint.isFakeBoldText = true
//                    pageSplitter.append("world", textPaint)
//                    textPaint.isFakeBoldText = false
//                    pageSplitter.append("! ", textPaint)
//                    if ((i + 1) % 100 == 0) {
//                        pageSplitter.append("\n", textPaint)
//                    }
//                }
//                pagesView!!.setAdapter(TextPagerAdapter(supportFragmentManager, pageSplitter.getPages()))
//                pagesView!!.getViewTreeObserver().removeOnGlobalLayoutListener(this)
//            }
//        })
    }

    override fun onStart() {
        pagesView = findViewById(R.id.pages)
//        pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager, null)


        pagesView!!.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//        pagesView!!.adapter = pagerAdapter
//        pagesView!!.adapter = ViewPagerAdapter()

        //        // to get ViewPager width and height we have to wait global layout
        pagesView!!.getViewTreeObserver().addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val pageSplitter = PageSplitter(pagesView!!.getWidth(), pagesView!!.getHeight(), 1.toFloat(), 0)
                val textPaint = TextPaint()
                textPaint.textSize = resources.getDimension(R.dimen.text_size)
//                for (i in 0..999) {
//                    pageSplitter.append("Hello, ", textPaint)
//                    textPaint.isFakeBoldText = true
//                    pageSplitter.append("world", textPaint)
//                    textPaint.isFakeBoldText = false
//                    pageSplitter.append("! ", textPaint)
//                    if ((i + 1) % 100 == 0) {
//                        pageSplitter.append("\n", textPaint)
//                    }
//                }
                val text = resources.getString(R.string.cats_text)
                pageSplitter.append(text, textPaint)
                val pages = pageSplitter.getPages()
                pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager, pages)
                pagesView!!.setAdapter(pagerAdapter)
                pagesView!!.getViewTreeObserver().removeOnGlobalLayoutListener(this)

                for (page in pages) {
                    pagerAdapter.addFragment(MainFragment.newInstance((page.toString())))
                }

//                pagerAdapter!!.addFragment(MainFragment.newInstance("my argument"))
//                pagerAdapter!!.addFragment(MainFragment.newInstance("my argument1"))
            }
        })



        super.onStart()
    }

    /**
     * A simple pager adapter that represents 2 ScreenSlidePageFragment objects, in
     * sequence.
     */
    inner class ScreenSlidePagerAdapter(val fragmentManager: FragmentManager,
                                        val pageTexts: List<CharSequence>?
                                        ) :
            FragmentStateAdapter(fragmentManager, this@MainActivity.lifecycle) {

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