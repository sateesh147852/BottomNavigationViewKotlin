package com.testkotlin

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.testkotlin.databinding.FragmentABinding


class FragmentA : Fragment() {

    private lateinit var binding: FragmentABinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentABinding.inflate(layoutInflater)
        initialize()
        return binding.root
    }

    private fun initialize() {
        binding.tvHideKeyBoard.setOnClickListener() {
            Utility.showKeyBoard(binding.tvShowKeyBoard)
        }

        binding.tvShowKeyBoard.setOnClickListener() {
            Utility.hideKeyBoard(binding.tvShowKeyBoard)
        }

        binding.tvMakeCall.setOnClickListener() {
            val number = Uri.parse("tel:123456789")
            val intent = Intent(Intent.ACTION_DIAL, number)
            startActivity(intent)
        }

        binding.tvSendSms.setOnClickListener() {
            val uri = Uri.parse("smsto:8618378828")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "How are you")
            startActivity(intent)

        }

        binding.tvSendEmail.setOnClickListener() {

            val email = Intent(Intent.ACTION_SENDTO)
            email.type = "text/plain"
            email.type = "message/rfc822"
            email.putExtra(Intent.EXTRA_EMAIL, "sateesh147852@gmail.com")
            email.putExtra(Intent.EXTRA_CC, "sateesh147852@gmail.com");
            email.putExtra(Intent.EXTRA_SUBJECT, "Download " + "getString(R.string.app_name)")
            email.putExtra(Intent.EXTRA_TEXT, "id.shareText")
            email.data = Uri.parse("mailto:")
            startActivity(Intent.createChooser(email, "Choose an Email client :"))
        }

        binding.tvPlayRingtone.setOnClickListener() {
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(context, notification)
            ringtone.play()
        }

        binding.tvTurnOnBluetooth.setOnClickListener() {
            val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            if (mBluetoothAdapter.isEnabled)
                mBluetoothAdapter.disable()
            else
                mBluetoothAdapter.enable()
        }
    }

    companion object {
        val fragmentA = FragmentA()
    }
}