package course.com.viewmodelarchitectureapp.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import course.com.viewmodelarchitectureapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(screen_container.id, ListFragment())
                    .commit()
        }

    }
}
