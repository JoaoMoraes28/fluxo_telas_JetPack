package com.example.routes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.routes.screens.LoginScreen
import com.example.routes.screens.MenuScreen
import com.example.routes.screens.PedidosScreen
import com.example.routes.screens.PerfilScreen
import com.example.routes.ui.theme.RoutesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoutesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable(route = "login") {
                            LoginScreen(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        composable(route = "menu") {
                            MenuScreen(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        composable(
                            route = "perfil/{nome}/{idade}",
                            arguments = listOf(
                                navArgument("nome") {
                                    type = NavType.StringType
                                },
                                navArgument("idade") {
                                    type = NavType.IntType
                                }
                            )
                        ) {

                            val nome = it.arguments?.getString("nome")
                            val idade = it.arguments?.getInt("idade")

                            PerfilScreen(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding),
                                nome = nome!!,
                                idade = idade!!
                            )
                        }

                        composable(
                            route = "pedidos?numeroPedido={numeroPedido}",
                            arguments = listOf(
                                navArgument("numeroPedido", {
                                    defaultValue = "Sem pedidos!"
                                } )
                            )) {
                            val pedido = it.arguments?.getString("numeroPedido")

                            PedidosScreen(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding),
                                numeroPedido = pedido!!
                            )
                        }
                    }
                }
            }


        }
    }
}