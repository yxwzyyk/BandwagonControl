package xyz.yxwzyyk.bandwagoncontrol.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.analytics.HitBuilders
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import xyz.yxwzyyk.bandwagoncontrol.R
import xyz.yxwzyyk.bandwagoncontrol.app.AnalyticsTrackers
import xyz.yxwzyyk.bandwagoncontrol.db.Host
import xyz.yxwzyyk.bandwagoncontrol.utils.Constant
import xyz.yxwzyyk.bandwagoncontrol.utils.FragmentCallBack
import xyz.yxwzyyk.bandwagoncontrol.views.AboutDialog

class MainActivity : AppCompatActivity() {
    val mTracker = AnalyticsTrackers.instance[AnalyticsTrackers.Target.APP]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val listFragment = ListFragment()

        listFragment.callback = object : FragmentCallBack {
            override fun callBack(data: Any) {
                val host = data as Host
                val hostFragment = HostFragment(host)
                hostFragment.callback = object : FragmentCallBack {
                    override fun callBack(data: Any) {
                        when (data) {
                            Constant.GOTO_SHELL_FRAGMENT -> {
                                val shellFragment = ShellFragment(host)
                                supportFragmentManager.beginTransaction().add(R.id.fragment, shellFragment)
                                        .addToBackStack(null).commit()
                            }

                            Constant.GOTO_REINSTALL_OS_FRAGMENT -> {
                                val reinstallOSFragment = ReinstallOSFragment(host)
                                supportFragmentManager.beginTransaction().add(R.id.fragment, reinstallOSFragment)
                                        .addToBackStack(null).commit()
                            }

                            Constant.GOTO_LOCATIONS_FRAGMENT -> {
                                val locationsFragment = LocationsFragment(host)
                                supportFragmentManager.beginTransaction().add(R.id.fragment, locationsFragment)
                                        .addToBackStack(null).commit()
                            }
                        }

                    }
                }
                async() {
                    Thread.sleep(100)
                    uiThread {
                        supportFragmentManager.beginTransaction().add(R.id.fragment, hostFragment)
                                .addToBackStack(null).commit()
                    }
                }
            }
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, listFragment)
                .commit()

    }

    override fun onResume() {
        super.onResume()
        mTracker.setScreenName(this.localClassName)
        mTracker.send(HitBuilders.ScreenViewBuilder().build())
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_about) {
            AboutDialog(this).show()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
