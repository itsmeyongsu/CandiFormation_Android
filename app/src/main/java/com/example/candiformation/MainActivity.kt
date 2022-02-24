package com.example.candiformation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.candiformation.navigation.SetupNavigation
import com.example.candiformation.navigation.bottomBar.BottomNavBar
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.theme.CandiformationTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()

    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            sharedViewModel.loginRefresh() // 저장된 로그인 정보 불러오기

                Scaffold(
                    content = {
                        SetupNavigation(
                            navController = navController,
                            viewModel = sharedViewModel
                        )
                    },
                    bottomBar = {
                        BottomNavBar(
                            navController = navController,
                            viewModel = sharedViewModel
                        )
                    }
                )
        }
    }
}
