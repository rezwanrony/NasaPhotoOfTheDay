package com.friendroid.nasaphotooftheday.Activity

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.friendroid.nasaphotooftheday.Core.GetDataReport
import com.friendroid.nasaphotooftheday.Core.Presenter
import com.friendroid.nasaphotooftheday.Model.PhotoOfTheDay
import com.friendroid.nasaphotooftheday.R
import com.friendroid.nasaphotooftheday.Utills.ConnectionDetectorUtils
import com.friendroid.nasaphotooftheday.Utills.Utility
import com.friendroid.nasaphotooftheday.Utills.YouTubeHelper
import com.github.tntkhang.fullscreenimageview.library.FullScreenImageViewActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class PhotoOfTheDayActivity : AppCompatActivity(),GetDataReport.View {

    private var mPresenter: Presenter? = null
    val myCalendar = Calendar.getInstance()
    var date:String?=  null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val date =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                updateLabel()
            }

        mPresenter = Presenter(mGetDataView = this)
        mPresenter!!.getDataFromURL(applicationContext, Utility.ApiBaseUrl)
        img_calendar.setOnClickListener(View.OnClickListener {
                DatePickerDialog(
                    this@PhotoOfTheDayActivity, date, myCalendar[Calendar.YEAR],
                    myCalendar[Calendar.MONTH],
                    myCalendar[Calendar.DAY_OF_MONTH]
                ).show()
        })
    }

    override fun onGetDataSuccess(message: String?, list: List<PhotoOfTheDay?>?) {
        progressbar.visibility=View.GONE
        tv_title_photooftheday.setText(list?.get(0)?.title)
        tv_desc_photooftheday.setText(list?.get(0)?.explanation)
        if(list?.get(0)?.mediaType.equals("image")){
            img_playvideo.visibility=View.GONE
            Glide.with(applicationContext).load(list?.get(0)?.hdurl).dontTransform().into(img_photoftheday)
            img_zoomin.setOnClickListener {
                val fullImageIntent = Intent(
                    this@PhotoOfTheDayActivity,
                    FullScreenImageViewActivity::class.java
                )
                var listofImage=ArrayList<String>();
                listofImage.add(list?.get(0)?.hdurl!!)
// uriString is an ArrayList<String> of URI of all images
                // uriString is an ArrayList<String> of URI of all images
                fullImageIntent.putExtra(FullScreenImageViewActivity.URI_LIST_DATA, listofImage)
// pos is the position of image will be showned when open
                // pos is the position of image will be showned when open
                fullImageIntent.putExtra(
                    FullScreenImageViewActivity.IMAGE_FULL_SCREEN_CURRENT_POS,
                    0
                )
                startActivity(fullImageIntent)


            }
        }
        else{
            img_zoomin.visibility=View.GONE
            img_playvideo.visibility=View.VISIBLE
            var youTubeHelper=YouTubeHelper()
            val videoId = youTubeHelper.extractVideoIdFromUrl(list?.get(0)?.url.toString())

            val videoUrl = "https://img.youtube.com/vi/"+videoId+"/hqdefault.jpg"
            img_photoftheday.setImageDrawable(null)
            Glide.with(this)
                .asBitmap()
                .load(videoUrl)
                .dontTransform()
                .into(img_photoftheday)

            img_playvideo.setOnClickListener {
                startActivity(Intent(this,FullScreenVideoActivity::class.java)
                    .putExtra("videoId",videoId)
                )
            }
        }

    }

    override fun onGetDataFailure(message: String?) {
        progressbar.visibility=View.GONE
        if (!ConnectionDetectorUtils.NetworkCheck(applicationContext)){
            ConnectionDetectorUtils.buildDialog(this@PhotoOfTheDayActivity).show()
        }
        else{
            Log.d("Status", message);
        }
    }

    override fun onGetDataSuccessWithDate(message: String?, list: List<PhotoOfTheDay?>?) {
        progressbar.visibility=View.GONE
        tv_title_photooftheday.setText(list?.get(0)?.title)
        tv_desc_photooftheday.setText(list?.get(0)?.explanation)
        if(list?.get(0)?.mediaType.equals("image")){
            img_playvideo.visibility=View.GONE
            Glide.with(applicationContext).load(list?.get(0)?.hdurl).into(img_photoftheday)
            img_zoomin.setOnClickListener {
                val fullImageIntent = Intent(
                    this@PhotoOfTheDayActivity,
                    FullScreenImageViewActivity::class.java
                )
                var listofImage=ArrayList<String>();
                listofImage.add(list?.get(0)?.hdurl!!)
// uriString is an ArrayList<String> of URI of all images
                // uriString is an ArrayList<String> of URI of all images
                fullImageIntent.putExtra(FullScreenImageViewActivity.URI_LIST_DATA, listofImage)
// pos is the position of image will be showned when open
                // pos is the position of image will be showned when open
                fullImageIntent.putExtra(
                    FullScreenImageViewActivity.IMAGE_FULL_SCREEN_CURRENT_POS,
                    0
                )
                startActivity(fullImageIntent)


            }
        }
        else{
            img_zoomin.visibility=View.GONE
            img_playvideo.visibility=View.VISIBLE
            var youTubeHelper=YouTubeHelper()
            val videoId = youTubeHelper.extractVideoIdFromUrl(list?.get(0)?.url.toString())
            val videoUrl = "https://img.youtube.com/vi/"+videoId+"/hqdefault.jpg"
            img_photoftheday.setImageDrawable(null)
                Glide.with(this)
                    .asBitmap()
                    .load(videoUrl)
                    .dontTransform()
                    .into(img_photoftheday)

            img_playvideo.setOnClickListener {
                startActivity(Intent(this,FullScreenVideoActivity::class.java)
                    .putExtra("videoId",videoId)
                )
            }

        }
    }

    override fun onGetDataFailureWithDate(message: String?) {
        progressbar.visibility=View.GONE
        if (!ConnectionDetectorUtils.NetworkCheck(applicationContext)){
            ConnectionDetectorUtils.buildDialog(this@PhotoOfTheDayActivity).show()
        }
        else{
            Log.d("Status", message);
        }
    }

    private fun updateLabel() {
        val myFormat = "yyyy-MM-dd" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        date=sdf.format(myCalendar.getTime())
        progressbar.visibility=View.VISIBLE
        mPresenter!!.getDataWithDateFromURL(applicationContext,Utility.ApiBaseUrl,date)

    }

}
