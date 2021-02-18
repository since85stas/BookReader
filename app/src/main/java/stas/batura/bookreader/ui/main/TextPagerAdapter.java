package stas.batura.bookreader.ui.main;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class TextPagerAdapter extends PagerAdapter {
    private final List<CharSequence> pageTexts;

    public TextPagerAdapter(FragmentManager fm, List<CharSequence> pageTexts) {
        this.pageTexts = pageTexts;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }


//
//    @Override
//    public Fragment getItem(int i) {
//        return PageFragment.newInstance(pageTexts.get(i));
//    }

    @Override
    public int getCount() {
        return pageTexts.size();
    }

}