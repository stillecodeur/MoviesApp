import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.ui.MoviesActivity
import com.zenjob.android.browsr.ui.movielist.adapter.MoviesAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieListFragmentTest {

    @get:Rule
    val activityRule=ActivityScenarioRule(MoviesActivity::class.java)
    val LIST_ITEM_TEST=1
    val MOVIE_IN_TEST=FakeMoviesData.movies[1]

    @Test
    fun testIsListFragmentVisibleOnAppLaunch(){
        onView(withId(R.id.recyclerViewMovies)).check(matches(isDisplayed()))
    }

    @Test
    fun testSelectMovieDetailFragmentVisible(){
        onView(withId(R.id.recyclerViewMovies)).perform(actionOnItemAtPosition<MoviesAdapter.MovieViewHolder>(LIST_ITEM_TEST,click()))
        onView(withId(R.id.tvTitle)).check(matches(withText(MOVIE_IN_TEST.title)))
    }

    @Test
    fun testBackNavigationListFragment(){
        onView(withId(R.id.recyclerViewMovies)).perform(actionOnItemAtPosition<MoviesAdapter.MovieViewHolder>(LIST_ITEM_TEST,click()))
        onView(withId(R.id.tvTitle)).check(matches(withText(MOVIE_IN_TEST.title)))
        pressBack()
        onView(withId(R.id.recyclerViewMovies)).check(matches(isDisplayed()))
    }
}