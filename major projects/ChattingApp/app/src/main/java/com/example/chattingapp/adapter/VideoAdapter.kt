package com.example.chattingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingapp.DoubleClickListner
import com.example.chattingapp.R
import com.example.chattingapp.model.VideoModel
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class VideoAdapter(options:FirebaseRecyclerOptions<VideoModel?>)
    :FirebaseRecyclerAdapter<VideoModel?,VideoAdapter.MyViewHolder>(options){


    inner class MyViewHolder(v:View):RecyclerView.ViewHolder(v){

        lateinit var videoView:VideoView
        lateinit var title : TextView
        lateinit var desc : TextView
        lateinit var pbar:ProgressBar
        lateinit var fav:ImageView

        var isFav = false

        init {
            videoView = v.findViewById(R.id.videoView)
            title = v.findViewById(R.id.textVideoTitle)
            desc = v.findViewById(R.id.textVideoDescription)
            pbar = v.findViewById(R.id.videoProgressBar)
            fav = v.findViewById(R.id.favorites)
        }

        fun setData(obj:VideoModel){
            videoView.setVideoPath(obj.url)
            title.text = obj.title
            desc.text = obj.desc
            videoView.setOnPreparedListener{
                mediaPlayer ->
                pbar.visibility = View.GONE
                mediaPlayer.start()
            }

            videoView.setOnCompletionListener {
                mediaPlayer->mediaPlayer.start()
            }

            videoView.setOnClickListener(object : DoubleClickListner(){
                override fun onDoubleCLick(v: View?) {
                    isFav = if(!isFav){
                        fav.setImageResource(R.drawable.baseline_favorite_24)
                        true
                    } else{
                        fav.setImageResource(R.drawable.baseline_favorite_border_24)
                        false
                    }
                }

            })

            fav.setOnClickListener {
                isFav = if(!isFav){
                    fav.setImageResource(R.drawable.baseline_favorite_24)
                    true
                } else{
                    fav.setImageResource(R.drawable.baseline_favorite_border_24)
                    false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.single_video_row,parent,false)

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: VideoModel) {
        holder.setData(model)
    }
}