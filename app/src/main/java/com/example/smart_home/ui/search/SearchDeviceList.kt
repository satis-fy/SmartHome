package com.example.smart_home.ui.search

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smart_home.R
import com.example.smart_home.domain.model.SearchDevice
import com.example.smart_home.ui.theme.colorAfterDark
import com.example.smart_home.ui.theme.colorAllWhite
import com.example.smart_home.ui.theme.colorBgApp
import com.example.smart_home.ui.theme.colorLightBgApp
import com.example.smart_home.ui.theme.colorOrange
import com.example.smart_home.ui.theme.colorWhite
import com.example.smart_home.ui.theme.manrope_font

@SuppressLint("UnrememberedMutableState")
@Composable
fun SearchDeviceList(
    navController: NavController
) {

    val listDevices = listOf<SearchDevice>(
        SearchDevice(
            picture = R.drawable.img_bork,
            title = "Bork V530",
            deviceType = "Vacuum cleaner",
            isSelected = true
        ),
        SearchDevice(
            picture = R.drawable.img_led_light,
            title = "LIFX LED Light",
            deviceType = "Smart bulb"
        ),
        SearchDevice(
            picture = R.drawable.img_humidifier,
            title = "Xiaomi DEM-F600",
            deviceType = "Humidifier"
        ),
        SearchDevice(
            picture = R.drawable.ic_wifi,
            title = "Not found\ndevice?",
            deviceType = "Select manually"
        )
    )

    var selectedItemIndex by remember {
        mutableIntStateOf(0)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBgApp)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column() {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Search",
                        maxLines = 1,
                        style = TextStyle(
                            color = colorWhite,
                            fontSize = 32.sp,
                            fontFamily = manrope_font,
                            fontWeight = FontWeight.Medium
                        ),
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(weight = 1F, fill = true)
                    )

                    Text(
                        text = "Wifi:tw1r_413_7G",
                        style = TextStyle(
                            color = colorAllWhite,
                            fontSize = 12.sp,
                            fontFamily = manrope_font,
                            fontWeight = FontWeight.Medium
                        )
                    )

                }

                Text(
                    "3 new devices",
                    style = TextStyle(
                        color = colorAllWhite,
                        fontSize = 18.sp,
                        fontFamily = manrope_font,
                        fontWeight = FontWeight.Medium,
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(top = 32.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(items = listDevices) {
                        if (it.picture == R.drawable.ic_wifi) {
                            SearchDeviceManually(searchDevice = it)
                        } else {
                            SearchDeviceItem(
                                searchDevice = it,
                                isSelected = selectedItemIndex == listDevices.indexOf(it)
                            ) { searchDevice ->
                                selectedItemIndex = listDevices.indexOf(searchDevice)
                            }
                        }
                    }
                }
            }

            Column() {
                TextButton(
                    onClick = { },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .defaultMinSize(
                            minHeight = 1.dp,
                            minWidth = 1.dp
                        )
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorOrange
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Add device",
                        style = TextStyle(
                            color = colorBgApp,
                            fontSize = 18.sp,
                            fontFamily = manrope_font,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun SearchDeviceItem(
    searchDevice: SearchDevice,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    onItemClick: (SearchDevice) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorLightBgApp
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) colorOrange else colorLightBgApp
        ),
        modifier = modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(searchDevice)
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 24.dp),
        ) {
            Image(
                painter = painterResource(id = searchDevice.picture),
                contentDescription = "Search Device Item Picture",
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .size(78.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = searchDevice.title,
                style = TextStyle(
                    color = colorWhite,
                    fontSize = 16.sp,
                    fontFamily = manrope_font,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(
                text = searchDevice.deviceType,
                style = TextStyle(
                    color = colorAllWhite,
                    fontSize = 12.sp,
                    fontFamily = manrope_font,
                    fontWeight = FontWeight.Medium
                )
            )
        }

    }
}

@Composable
fun SearchDeviceManually(
    searchDevice: SearchDevice
) {
    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
    )

    Box(
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(color = colorBgApp)
            .padding(2.dp)
            .drawBehind {
                drawRoundRect(
                    color = colorAfterDark,
                    style = stroke,
                    cornerRadius = CornerRadius(24.dp.toPx())
                )
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 24.dp)
        ) {
            Image(
                painter = painterResource(
                    id = searchDevice.picture
                ),
                contentDescription = searchDevice.title,
                contentScale = ContentScale.Crop,
            )

            Text(
                text = searchDevice.title,
                style = TextStyle(
                    color = colorWhite,
                    fontSize = 18.sp,
                    fontFamily = manrope_font,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            Text(
                text = searchDevice.deviceType,
                style = TextStyle(
                    color = colorOrange,
                    fontSize = 12.sp,
                    fontFamily = manrope_font,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
        }
    }
}