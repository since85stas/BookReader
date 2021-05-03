package stas.batura.bookreader

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity: AppCompatActivity() {

    private var mFadeInAnimation: Animation? = null
    private  var mFadeOutAnimation:Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hiding title bar of this activity
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //making this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        //4second splash time
        Handler().postDelayed({
            //start main activity
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            //finish this activity
            finish()
        }, 2000)

        // подключаем файл анимации
        mFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        mFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        mFadeInAnimation!!.setAnimationListener(animationFadeInListener);
        mFadeOutAnimation!!.setAnimationListener(animationFadeOutListener);

        // при запуске начинаем с анимации исчезновения
        welcText.startAnimation(mFadeOutAnimation);

    }

    var animationFadeOutListener: Animation.AnimationListener = object : Animation.AnimationListener {
        override fun onAnimationEnd(animation: Animation) {
            welcText.startAnimation(mFadeInAnimation)
        }

        override fun onAnimationRepeat(animation: Animation) {
        }

        override fun onAnimationStart(animation: Animation) {
        }
    }

    var animationFadeInListener: Animation.AnimationListener = object : Animation.AnimationListener {
        override fun onAnimationEnd(animation: Animation) {
            welcText.startAnimation(mFadeOutAnimation)
        }

        override fun onAnimationRepeat(animation: Animation) {
        }

        override fun onAnimationStart(animation: Animation) {
        }
    }

}