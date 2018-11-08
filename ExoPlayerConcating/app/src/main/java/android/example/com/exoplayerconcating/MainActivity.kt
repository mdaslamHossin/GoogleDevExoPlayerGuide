package android.example.com.exoplayerconcating

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.exoplayer2.source.ExtractorMediaSource


class MainActivity : AppCompatActivity() {

  private lateinit var player: SimpleExoPlayer
  private lateinit var dataSourceFactory: DataSource.Factory
  private lateinit var firstVideoSource: MediaSource
  private lateinit var secondVideoSource: MediaSource
  private lateinit var concatenatedSource: ConcatenatingMediaSource
  private val fisrtVideoUri =
    "https://vodcmssec-a.akamaihd.net/omnisport/ready/HD/287/071118-EN-PERFORM-ERIKSSON-LEICESTER-2_1541624236154_287.mp4"
  private val secondVideoUri =
    "https://vodcmssec-a.akamaihd.net/omnisport/ready/HD/ptv_omni_ready/287/061118-EN-PERFORM-LEICESTER-FUNERAL-2_1541513248344_287.mp4"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    player = ExoPlayerFactory.newSimpleInstance(this)

    playerView.player = player

    dataSourceFactory = DefaultDataSourceFactory(
      this,
      Util.getUserAgent(this, getString(R.string.app_name))
    )

    firstVideoSource = ExtractorMediaSource.Factory(dataSourceFactory)
      .createMediaSource(Uri.parse(fisrtVideoUri))
    secondVideoSource = ExtractorMediaSource.Factory(dataSourceFactory)
      .createMediaSource(Uri.parse(secondVideoUri))

    concatenatedSource = ConcatenatingMediaSource(
      firstVideoSource/*First video play */, secondVideoSource/*Second
    video will play after completing first video */
    )


    player.prepare(concatenatedSource)


  }

/* Thanks for watching this video :) */
}
