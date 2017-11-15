package zabik.org.vkstats.activities

import android.app.Application
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.vk.sdk.api.*
import com.vk.sdk.api.model.VKList
import kotlinx.android.synthetic.main.statistic_layout.*
import kotlinx.android.synthetic.main.statistic_layout.view.*
import zabik.org.vkstats.R

/**
 * Created by zabik on 15.11.17.
 */
class StatisticActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(Bundle())
        setContentView(R.layout.statistic_layout)

        var req = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,"first_name,last_name"))
        req.executeWithListener(object : VKRequest.VKRequestListener(){
            override fun onComplete(response: VKResponse?) {
                super.onComplete(response)
                var list = response?.parsedModel as VKList<*>
                var arrayAdapter = ArrayAdapter<String>(this@StatisticActivity,android.R.layout.simple_list_item_1,list as List<String>)

                friends_list?.adapter = arrayAdapter
            }
        })
    }
}