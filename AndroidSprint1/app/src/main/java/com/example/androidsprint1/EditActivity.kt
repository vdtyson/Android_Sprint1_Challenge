package com.example.androidsprint1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidsprint1.MainActivity.Companion.EDIT_MOVIE_DATA
import com.example.androidsprint1.Model.Movie
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        var bundle: Bundle? = intent.extras
        if(bundle != null) {
            loadMovie(bundle!!.getSerializable(EDIT_MOVIE_DATA) as Movie)
        }

        save.setOnClickListener {
            val intentSaveMovie = Intent()
            intentSaveMovie.putExtra("movie", createMovie())
            if (switch_button.isChecked) {
                intentSaveMovie.putExtra("watched", "watched")
            }
            else {
                intentSaveMovie.putExtra("watched", "unwatched")
            }
            setResult(Activity.RESULT_OK, intentSaveMovie)
            finish()
        }
    }
    fun loadMovie(movie: Movie) {
        edit_text.setText(movie.title)
    }
    fun createMovie(): Movie {
        var newMovie = Movie(edit_text.text.toString())
        return newMovie
    }
}
