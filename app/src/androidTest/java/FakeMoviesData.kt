import com.zenjob.android.browsr.data.model.Genre
import com.zenjob.android.browsr.data.model.Movie
import java.util.*

object FakeMoviesData {

    val movies = arrayOf(
        Movie(
            338953,
            "",
            "Professor Albus Dumbledore knows the powerful, dark wizard Gellert Grindelwald is moving to seize control of the wizarding world. Unable to stop him alone, he entrusts magizoologist Newt Scamander to lead an intrepid team of wizards and witches. They soon encounter an array of old and new beasts as they clash with Grindelwald's growing legion of followers.",
            "Fantastic Beasts: The Secrets of Dumbledore",
            "released",
            "/8ZbybiGYe8XM4WGmGlhF0ec5R7u.jpg",
            "/zGLHX92Gk96O1DJvLil7ObJTbaL.jpg",
            Date("Wed Apr 06 00:00:00 GMT+05:30 2022"),
            6.9f,
            1761,
            arrayListOf(
                Genre(1, "Action"),
                Genre(2, "Comedy")
            )
        ),
        Movie(
            526896,
            "",
            "Dangerously ill with a rare blood disorder, and determined to save others suffering his same fate, Dr. Michael Morbius attempts a desperate gamble. What at first appears to be a radical success soon reveals itself to be a remedy potentially worse than the disease.",
            "Morbius",
            "released",
            "/6JjfSchsU6daXk2AKX8EEBjO3Fm.jpg",
            "/gG9fTyDL03fiKnOpf2tr01sncnt.jpg\n",
            Date("Wed Mar 04 00:00:00 GMT+05:30 2022"),
            6.4f,
            1641,
            arrayListOf(
                Genre(1, "Action"),
                Genre(2, "Thriller")
            )
        )
    )


}