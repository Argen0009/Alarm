package mbk.io.alarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mbk.io.alarm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.Clock.setOnClickListener{
            val intent = Intent(this@MainActivity, ClockActivity::class.java)
            startActivity(intent)
        }
        binding.Stopwatch.setOnClickListener{
            val intent = Intent(this@MainActivity, StopwatchActivity::class.java)
            startActivity(intent)
        }
        binding.Timer.setOnClickListener{
            val intent = Intent(this@MainActivity, TimerActivity::class.java)
            startActivity(intent)
        }
    }
}