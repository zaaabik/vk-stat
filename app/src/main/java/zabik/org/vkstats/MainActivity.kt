package zabik.org.vkstats

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import android.content.Intent
import android.widget.Toast
import zabik.org.vkstats.activities.StatisticActivity


class MainActivity : AppCompatActivity() {
    private var Scopes = arrayOf<String>(VKScope.MESSAGES,VKScope.PHOTOS,VKScope.FRIENDS)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        VKSdk.login(this,*Scopes)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!// User passed Authorization
        // User didn't pass Authorization
        VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
            override fun onResult(res: VKAccessToken) {
                Toast.makeText(this@MainActivity,"good",Toast.LENGTH_LONG).show()
                successAuth()
            }
            override fun onError(error: VKError) {
                Toast.makeText(this@MainActivity,"err",Toast.LENGTH_LONG).show()
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun successAuth(){
        val intent = Intent(this@MainActivity,StatisticActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}
