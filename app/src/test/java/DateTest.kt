import com.zenjob.android.browsr.utils.AppUtils
import org.junit.Assert.assertEquals
import org.junit.Test
class DateTest {
    @Test
    fun testFormat(){
        val date=AppUtils.getDate("Wed Apr 06 00:00:00 GMT+05:30 2022")
        assertEquals("06 Apr 2022",date)
    }
}