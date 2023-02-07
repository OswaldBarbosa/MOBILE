package com.example.bmicalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalc.ui.theme.BMICalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalcTheme {
                CalculatorScreen()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CalculatorScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            //Header
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.bmi),
                    contentDescription = "Image",
                    modifier = Modifier.size(120.dp)
                )
                Text(
                    text = stringResource(id = R.string.title),
                    fontSize = 24.sp,
                    color = Color.Blue,
                    letterSpacing = 8.sp
                )

                //Form
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            32.dp
                        )
                )
                {
                    Text(
                        text = stringResource(id = R.string.weigth_label),
                        modifier = Modifier.padding(
                            bottom = 8.dp,
                            top = 30.dp
                        ),
                        fontSize = 16.sp,

                        )

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)

                    )

                    Text(
                        text = stringResource(id = R.string.heigth_label),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(
                            bottom = 8.dp,
                            top = 30.dp
                        )
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            text = stringResource(id = R.string.button_calculate),
                            textAlign = TextAlign.Center,
                        )
                    }

                }
                //Footer
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        color = Color.Blue,
                        shape = RoundedCornerShape(
                            topStart = 32.dp,
                            topEnd = 32.dp
                        )
                    ) {

                    }
                }
            }


        }

    }
}
