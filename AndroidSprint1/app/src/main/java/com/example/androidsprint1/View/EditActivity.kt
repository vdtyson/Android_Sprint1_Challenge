package com.example.androidsprint1.View

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidsprint1.MainActivity
import com.example.androidsprint1.MainActivity.Companion.EDIT_MOVIE_DATA
import com.example.androidsprint1.Model.Movie
import com.example.androidsprint1.R
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
            val intentSaveMovie = Intent(this, MainActivity::class.java)
            intentSaveMovie.putExtra("movie", createMovie())

            setResult(Activity.RESULT_OK, intentSaveMovie)
            finish()
        }
        delete.setOnClickListener {
            finish()
        }
    }
    //
    fun loadMovie(movie: Movie) {
        edit_text.setText(movie.title)
        if (movie.wasItWatched == true) {
            switch_button.isChecked
        } else {
            !switch_button.isChecked
        }
    }
    //
    fun createMovie(): Movie {
        val newMovie = Movie(edit_text.text.toString(),switch_button.isChecked)
        return newMovie
    }
}
