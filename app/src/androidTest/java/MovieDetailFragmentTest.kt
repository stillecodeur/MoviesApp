import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.ui.MoviesActivity
import com.zenjob.android.browsr.ui.movielist.adapter.MoviesAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)

class MovieDetailFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MoviesActivity::class.java)
    val MOVIE_IN_TEST = FakeMoviesData.movies[1]
    val LIST_ITEM_TEST = 1

    @Test
    fun testMovieDetailFragmentVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesAdapter.MovieViewHolder>(
                LIST_ITEM_TEST,
                ViewActions.click()
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rbVote))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvRating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvCount))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.chipGenre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun testMovieDetails() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesAdapter.MovieViewHolder>(
                LIST_ITEM_TEST,
                ViewActions.click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.withText(MOVIE_IN_TEST.title)))
        Espresso.onView(ViewMatchers.withId(R.id.tvRating))
            .check(ViewAssertions.matches(ViewMatchers.withText(MOVIE_IN_TEST.voteAverage.toString())))
        Espresso.onView(ViewMatchers.withId(R.id.tvCount))
            .check(ViewAssertions.matches(ViewMatchers.withText("(${MOVIE_IN_TEST.voteCount.toString()})")))
        Espresso.onView(ViewMatchers.withId(R.id.tvDescription))
            .check(ViewAssertions.matches(ViewMatchers.withText(MOVIE_IN_TEST.overview)))


    }
}