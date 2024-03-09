package mbk.io.alarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mbk.io.alarm.databinding.ActivityClockBinding
import mbk.io.alarm.databinding.ActivityMainBinding

class ClockActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClockBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textClock.setOnClickListener {
            Toast.makeText(this@ClockActivity, "TextClock", Toast.LENGTH_SHORT).show()
        }
    }
}