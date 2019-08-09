package com.example.androidsprint1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.androidsprint1.Model.Movie
import com.example.androidsprint1.View.EditActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var movieList: ArrayList<Movie> = ArrayList()

    companion object {
        const val MOVIE_REQUEST_CODE = 0
        const val EDIT_MOVIE_DATA = "Some String"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_movie_button.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)

            startActivityForResult(intent, MOVIE_REQUEST_CODE)
        }
    }
// Line 32-42: Refreshes views onPostResume() so list is not duplicated
    fun refreshMovieList() {
        movie_text_linear_layout.removeAllViews()
        for ((index, movie) in movieList.withIndex())
            createTextView(movie, index)
    }

    override fun onPostResume() {
        refreshMovieList()
        super.onPostResume()
    }
// Line 44-46: after startActivityForResult gets Extras from the intent and populates movie data with it
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == MOVIE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val movie = data?.getSerializableExtra("movie") as Movie
            val watched = data?.getStringExtra("watched") as String
            if (movie != null) {
                movieList.add(movie)
                populateMovieData()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
// Line 58-72: Programmatically creates TextView from user input and adds a listener to the data to retrieve Movie Data and Edit it
    fun createTextView(movie: Movie, index: Int): TextView {
        val newMovieView = TextView(this)
        newMovieView.textSize = 24f
        newMovieView.id = index
        newMovieView.text = movie.title

        newMovieView.setOnClickListener{
            val intent = Intent(this, EditActivity::class.java)

            intent.putExtra(EDIT_MOVIE_DATA, movie)

            startActivity(intent)
        }
        return newMovieView
    }
//  Line 73-78: Populates movie data from list (used in line 46-56)
    fun populateMovieData() {
        for(i in 0 until movieList.size) {
            movie_text_linear_layout.addView(createTextView(movieList[i], i))
        }
    }
}
