package com.example.ergoen.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.ergoen.R
import com.example.ergoen.data.network.interceptors.UnauthorizedInterceptor
import com.example.ergoen.databinding.ActivityMainBinding
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

const val REQUEST_CHECK_SETTINGS = 11111
const val LOCATION_REQUEST_INTERVAL: Long = 1000

class MainActivity : AppCompatActivity(), UnauthorizedInterceptor.UnauthorizedExceptionListener {
    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationRequest by lazy {
        LocationRequest.create().apply {
            interval = LOCATION_REQUEST_INTERVAL
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            locationResult.locations[0]?.let { location ->
                mainActivityViewModel.setUserLocation(location)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        UnauthorizedInterceptor.addListener(this)
        requestLocation()
    }

    override fun onTokenInvalid(exception: Exception) {
        mainActivityViewModel.resetToken()
    }

    // All location related things go below.
    @SuppressLint("MissingPermission")
    private fun initLocationUpdates() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                mainActivityViewModel.setUserLocation(location)

                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )
            }
        }
    }

    private fun requestLocation() {
        if (locationPermissionGranted()) {
            getLocationSettings()?.let {
                it.addOnSuccessListener {
                    // All location settings are satisfied.
                    initLocationUpdates()
                }
                it.addOnFailureListener { exception ->
                    if (exception is ResolvableApiException) {
                        // Location settings are not satisfied, but this can be fixed by showing
                        // a dialog to user .
                        try {
                            exception.startResolutionForResult(this, REQUEST_CHECK_SETTINGS)
                        } catch (sendEx: IntentSender.SendIntentException) {
                            sendEx.printStackTrace()
                        }
                    }
                }
            }
        }
    }

    private fun locationPermissionGranted(): Boolean {
        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(
            this as Context, Manifest.permission.ACCESS_FINE_LOCATION
        )
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 9
            )

            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 9) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocation()
            }
        }
    }

    private fun getLocationSettings(): Task<LocationSettingsResponse>? {
        val settingsBuilder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val settingsClient: SettingsClient = LocationServices.getSettingsClient(this)
        return settingsClient.checkLocationSettings(settingsBuilder.build())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS && resultCode == Activity.RESULT_OK) {
            requestLocation()
        }
    }
}
