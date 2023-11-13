package com.example.smart_home.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_home.R
import com.example.smart_home.ui.theme.colorAfterDark
import com.example.smart_home.ui.theme.colorLightBgApp
import com.example.smart_home.ui.theme.colorOrange
import com.example.smart_home.ui.theme.colorSlider
import com.example.smart_home.ui.theme.colorWhite
import com.example.smart_home.ui.theme.colorWhite60
import com.example.smart_home.ui.theme.manrope_font

@Preview
@Composable
fun RoomTempItems(
    modifier: Modifier = Modifier
) {

    var switch by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(color = colorAfterDark)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "36%",
                    style = TextStyle(
                        color = colorWhite,
                        fontSize = 32.sp,
                        fontFamily = manrope_font,
                        fontWeight = FontWeight.Medium
                    )
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_humidity),
                    contentDescription = "Humidity",
                    tint = colorWhite,
                )
            }

            Text(
                text = "Humidifier \nAir",
                style = TextStyle(
                    fontFamily = manrope_font,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = colorWhite60,
                    letterSpacing = 1.sp
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Divider(
                thickness = 1.dp,
                color = colorLightBgApp,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 24.dp, bottom = 20.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Mode 2",
                    style = TextStyle(
                        fontFamily = manrope_font,
                        fontWeight = FontWeight.Normal,
                        color = colorWhite60,
                        fontSize = 12.sp,
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1F)
                )

                Switch(
                    checked = switch,
                    onCheckedChange = { isChecked ->
                        switch = isChecked
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorLightBgApp,
                        checkedTrackColor = colorOrange
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun LightSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(colorLightBgApp)
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LightsSectionItem(
                name = "Main light",
                icon = R.drawable.ic_bell
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            LightsSectionItem(
                name = "Floor lamp",
                icon = R.drawable.ic_tablelamp
            )
        }
    }
}

@Preview
@Composable
fun LightsSectionItem(
    name: String = "Main light",
    @DrawableRes icon: Int = R.drawable.ic_bell
) {
    var sliderPositionMainLight by remember {
        mutableFloatStateOf(60F)
    }

    Column {
        Text(
            text = name,
            style = TextStyle(
                fontFamily = manrope_font,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = colorWhite60,
                letterSpacing = 1.sp
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Slider(
                value = sliderPositionMainLight,
                onValueChange = { position ->
                    sliderPositionMainLight = position
                },
                steps = 0,
                valueRange = 0F..100F,
                colors = SliderDefaults.colors(
                    activeTrackColor = colorOrange,
                    inactiveTrackColor = colorSlider,
                    thumbColor = colorWhite,
                    activeTickColor = colorWhite
                ),
                modifier = Modifier
                    .weight(1F)
                    .padding(end = 16.dp)
            )

            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Main Light",
                tint = colorWhite
            )
        }
    }
}