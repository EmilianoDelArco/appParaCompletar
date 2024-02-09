package com.example.jetpackroom


import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import com.example.jetpackroom.ui.theme.JetpackRoomTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackroom.meteorologia.constant.Const.Companion.colorBg1
import com.example.jetpackroom.meteorologia.constant.Const.Companion.colorBg2
import com.example.jetpackroom.meteorologia.constant.Const.Companion.permissions
import com.example.jetpackroom.todo.db.Todo
import com.example.jetpackroom.meteorologia.model.MyLatLng
import com.example.jetpackroom.meteorologia.view.ForecastSection
import com.example.jetpackroom.meteorologia.view.WeatherSection
import com.example.jetpackroom.meteorologia.viewModel.MainViewModel
import com.example.jetpackroom.meteorologia.viewModel.STATE
import com.example.jetpackroom.todo.MainApplication
import com.example.jetpackroom.todo.TodoItem
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.location.*
import kotlinx.coroutines.*


class MainActivity : ComponentActivity() {
    private val dao = MainApplication.database.todoDao()
    private var todoList = mutableStateListOf<Todo>()
    private var scope = MainScope()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var locationRequired: Boolean = false
    private lateinit var mainViewModel: MainViewModel


    override fun onResume() {
        super.onResume()
        if (locationRequired) startLocationUpdate()
    }

    override fun onPause() {
        super.onPause()
        locationCallback?.let {
            fusedLocationProviderClient.removeLocationUpdates(it)
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdate() {
        locationCallback?.let {
            val locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY, 100
            )
                .setWaitForAccurateLocation(false)
                .setIntervalMillis(3000)
                .setMaxUpdateDelayMillis(100)
                .build()

            fusedLocationProviderClient?.requestLocationUpdates(
                locationRequest,
                it,
                Looper.getMainLooper()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initLocationClient()
        initViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            var currentLocation by remember {
                mutableStateOf(MyLatLng(0.0, 0.0))
            }
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    for (location in p0.locations) {
                        currentLocation = MyLatLng(
                            location.latitude,
                            location.longitude
                        )
                    }

                }
            }
/*-----------------------------COMPLETAR DESDE ACA-----------------------------------------------------------*/



        }//ESTA LLAVE DEBE QUEDAR LUEGO DE loadToDo()
    }//NO BORRAR ESTA LLAVE




    /*---------------------DESDE ACA  COLOCAR LAS FUNCIONES Y LAS VISTAS----------------------*/






}//LLAVE FINAL


