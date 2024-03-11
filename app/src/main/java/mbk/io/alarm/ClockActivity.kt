package mbk.io.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mbk.io.alarm.databinding.ActivityClockBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class ClockActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun setAlarm(context: Context, alarmTime: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent)
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.cancel(pendingIntent)
    }

    fun saveAlarmTime(context: Context, timeInMillis: Long) {
        val sharedPreferences = context.getSharedPreferences("AlarmPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong("alarmTime", timeInMillis)
        editor.apply()
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

        val alarmTime = Calendar.getInstance().timeInMillis
        saveAlarmTime(context, alarmTime)

        val formattedMessage =
            "Будильник установлен на ${SimpleDateFormat("HH:mm").format(Date(alarmTime))}"
        Toast.makeText(context, formattedMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        binding = ActivityClockBinding.inflate(layoutInflater)
        val view = binding.root

        binding.btnSetAlarm.setOnClickListener {
            val selectedTimeInMillis = getTimeInMillisFromTimePicker()

            if (selectedTimeInMillis != -1L) {
                setAlarm(context, selectedTimeInMillis)
                showToast(context, "Будильник установлен")
            } else {
                showToast(context, "Выберите время будильника")
            }
        }

        return view
    }

    private fun getTimeInMillisFromTimePicker(): Long {
        val calendar = Calendar.getInstance()
        val selectedHour = binding.timerPicker.hour
        val selectedMinute = binding.timerPicker.minute

        calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
        calendar.set(Calendar.MINUTE, selectedMinute)
        calendar.set(Calendar.SECOND, 0)

        return calendar.timeInMillis
    }
}