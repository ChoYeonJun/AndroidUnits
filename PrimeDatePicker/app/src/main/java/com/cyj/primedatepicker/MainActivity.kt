package com.cyj.primedatepicker

import android.graphics.Typeface
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseIntArray
import android.widget.Toast
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.common.BackgroundShapeType
import com.aminography.primedatepicker.common.LabelFormatter
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.callback.RangeDaysPickCallback
import com.aminography.primedatepicker.picker.theme.LightThemeFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // To show a date picker with Civil dates, also today as the starting date
        val today = CivilCalendar()

        val callback = RangeDaysPickCallback { start, end ->
            Toast.makeText(this, start.date.toString() + "/" + end.date.toString(), Toast.LENGTH_SHORT).show()
            // TODO
        }


        val themeFactory = object : LightThemeFactory() {
            //region selectionbar
            //from to 배경
            override val selectionBarBackgroundColor: Int
                get() = getColor(R.color.gray300)
            //from to highlight
            override val selectionBarRangeDaysItemBackgroundColor: Int
                get() = getColor(R.color.gray500)
            override val selectionBarMultipleDaysItemBackgroundColor: Int
                get() = getColor(R.color.green500)
            override val selectionBarRangeDaysItemBottomLabelTextColor: Int
                get() = getColor(R.color.gray700)
            override val selectionBarRangeDaysItemTopLabelTextColor: Int
                get() = getColor(R.color.gray700)

            //뭔지 모르겠음 달력 테마 바뀜
//            override val calendarViewDeveloperOptionsShowGuideLines: Boolean
//                get() = true
            //선택 날짜 문자 크기
            override val selectionBarRangeDaysItemBottomLabelTextSize: Int
                get() = 30
            //From To 문자 크기
            override val selectionBarRangeDaysItemTopLabelTextSize: Int
                get() = 30
            //From,TO / 날짜 문자 사이 공간 크기
            override val selectionBarRangeDaysItemGapBetweenLines: Int
                get() = 10
            //endregion


            //폰트 경로 main/assets/fonts
            override val typefacePath: String?
                get() = "fonts/scdream6.otf"
            //다이얼로그 배경
            override val dialogBackgroundColor: Int
                get() = getColor(R.color.white)
            //달력 배경
            override val calendarViewBackgroundColor: Int
                get() = getColor(R.color.white)
            //날짜 선택 모양
            override val pickedDayBackgroundShapeType: BackgroundShapeType
                get() = BackgroundShapeType.CIRCLE
            //선택 시작, 끝 날짜 색
            override val calendarViewPickedDayBackgroundColor: Int
                get() = getColor(R.color.blue400)
            //선택 범위 중간 날짜 색
            override val calendarViewPickedDayInRangeBackgroundColor: Int
                get() = getColor(R.color.blue400)
            //선택 범위 중간 날짜 문자 색
            override val calendarViewPickedDayInRangeLabelTextColor: Int
                get() = getColor(R.color.white)
            //오늘 날짜 문자 색
            override val calendarViewTodayLabelTextColor: Int
                get() = getColor(R.color.blue400)
            //캘린더 당월 문자 색
            override val calendarViewDayLabelTextColor: Int
                get() = getColor(R.color.gray700)
            //캘린더 월 라벨 문자 색
            override val calendarViewMonthLabelTextColor: Int
                get() = getColor(R.color.gray700)
            //캘린더 날짜 문자 크기
            override val calendarViewDayLabelTextSize: Int
                get() = 30
            override val calendarViewMonthLabelTextSize: Int
                get() = 40
            //
//            override val calendarViewWeekLabelFormatter: LabelFormatter
//                get() = { primeCalendar ->
//                    when (primeCalendar[Calendar.DAY_OF_WEEK]) {
//                        Calendar.SATURDAY,
//                        Calendar.SUNDAY -> String.format("%s😍", primeCalendar.weekDayNameShort)
//                        else -> String.format("%s", primeCalendar.weekDayNameShort)
//                    }
//                }

            override val calendarViewWeekLabelTextColors: SparseIntArray
                get() = SparseIntArray(7).apply {
                    val red = getColor(R.color.red300)
                    val indigo = getColor(R.color.blue300)
                    put(Calendar.SATURDAY, indigo)
                    put(Calendar.SUNDAY, indigo)
                    put(Calendar.MONDAY, indigo)
                    put(Calendar.TUESDAY, indigo)
                    put(Calendar.WEDNESDAY, indigo)
                    put(Calendar.THURSDAY, indigo)
                    put(Calendar.FRIDAY, indigo)
                }
            //다음달 날짜 보여줄지 말지
            override val calendarViewShowAdjacentMonthDays: Boolean
                get() = false
            //region actionBar
            override val actionBarNegativeTextColor: Int
                get() = getColor(R.color.blue300)

            override val actionBarPositiveTextColor: Int
                get() = getColor(R.color.blue300)

            override val actionBarTodayTextColor: Int
                get() = getColor(R.color.blue300)


            override val actionBarTextSize: Int
                get() = 30
            //endregion


        }
        val date = PrimeDatePicker.Companion.dialogWith(today)
            .pickRangeDays(callback)
            .applyTheme(themeFactory)
            .build()
//        val datePicker = PrimeDatePicker.bottomSheetWith(today)
//            .pickRangeDays(callback)
//            .build()// or dialogWith(today)

        date.show(supportFragmentManager, "SOME_TAG")
    }
}