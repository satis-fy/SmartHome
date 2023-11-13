package com.example.smart_home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.smart_home.domain.model.BottomNavigationItem
import com.example.smart_home.ui.navigation.Navigation
import com.example.smart_home.ui.theme.HomeSmartTheme
import com.example.smart_home.ui.theme.colorAfterDark
import com.example.smart_home.ui.theme.colorBgApp
import com.example.smart_home.ui.theme.colorOrange
import com.example.smart_home.ui.theme.colorWhite

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()

            HomeSmartTheme (
                darkTheme = true
            ) {
                val bottomNavigationItems = listOf(
                    BottomNavigationItem(
                        route = "home",
                        title = "Home",
                        selectedIcon = R.drawable.ic_home,
                        hasNews = false
                    ),
                    BottomNavigationItem(
                        route = "search",
                        title = "Search",
                        selectedIcon = R.drawable.ic_search,
                        hasNews = true
                    ),
                    BottomNavigationItem(
                        route = "application",
                        title = "Application",
                        selectedIcon = R.drawable.ic_grid,
                        hasNews = false,
                        badgeCount = 20
                    ),
                    BottomNavigationItem(
                        route = "profile",
                        title = "Profile",
                        selectedIcon = R.drawable.ic_profile,
                        hasNews = false,
                    ),
                )

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = bottomNavigationItems,
                                navController = navController,
                                onItemCLick = {
                                    if (
                                        it == bottomNavigationItems.first()
                                        || it == bottomNavigationItems.component2()
                                    ) {
                                        navController.navigate(it.route) {
                                            popUpTo(0)
                                        }
                                    }
                                },
                            )
                        },
                        contentWindowInsets = WindowInsets(
                            left = 0.dp,
                            top = 0.dp,
                            right = 0.dp,
                            bottom = 0.dp
                        )
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            Navigation(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemCLick: (BottomNavigationItem) -> Unit
) {

    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        containerColor = colorBgApp,
        tonalElevation = 4.dp,
        modifier = modifier
    ) {
        items.forEachIndexed { index, bottomNavigationItem ->

            val selected = bottomNavigationItem.route == backStackEntry.value?.destination?.route

            NavigationBarItem(
                selected = selected,
                colors = androidx.compose.material3.NavigationBarItemDefaults
                    .colors(
                        selectedIconColor = colorBgApp,
                        indicatorColor = colorAfterDark
                    ),
                onClick = {
                    onItemCLick(bottomNavigationItem)
                },
                label = {
                    Text(text = bottomNavigationItem.title)
                },
                alwaysShowLabel = false,
                icon = {
                    BadgedBox(
                        badge = {
                            if (bottomNavigationItem.badgeCount != null) {
                                Badge {
                                    Text(text = bottomNavigationItem.badgeCount.toString())
                                }
                            } else if (bottomNavigationItem.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = bottomNavigationItem.selectedIcon),
                            contentDescription = bottomNavigationItem.title,
                            tint = if (selected)
                                colorOrange
                            else
                                colorWhite
                        )
                    }
                }
            )
        }
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column() {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomeSmartTheme {
        Greeting("Android")
    }
}