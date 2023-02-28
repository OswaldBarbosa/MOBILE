package com.example.bmicalc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.bmicalc.model.calcs.bmiCalculate
import br.senai.sp.jandira.bmicalc.model.calcs.getBmiClassification
import br.senai.sp.jandira.bmicalc.model.calcs.getColorClassification
import com.example.bmicalc.ui.theme.BMICalcTheme
import java.time.LocalDate
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*
        val p = Product()
        p.id = 1
        p.name = "Mouse"
        p.price = 230.0

        var x = p.addName()
        var y = p.listProducts()

        val c = Client(
            id = 10,
            name = "Oswaldo",
            birthDay = LocalDate.of(2004, 8, 19)
        )
*/

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

    var weigthState = rememberSaveable {
        mutableStateOf("")
    }

    var heigthState = rememberSaveable {
        mutableStateOf("")
    }

    var bmiState = rememberSaveable {
        mutableStateOf("")
    }

    var bmiClassificationState = rememberSaveable {
        mutableStateOf("")
    }

    var context = LocalContext.current

    var color = rememberSaveable {
        mutableStateOf("")
    }

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
                        value = weigthState.value,
                        onValueChange = {
                            weigthState.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
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
                        value = heigthState.value,
                        onValueChange = {
                            heigthState.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            var bmi = bmiCalculate(
                                weight = weigthState.value.toDouble(),
                                heigth = heigthState.value.toDouble()
                            )
                            bmiState.value = bmi.toString()
                            bmiClassificationState.value =
                                getBmiClassification(bmi, context)
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(Color.Green)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            text = stringResource(id = R.string.button_calculate),
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }

                }
                //Footer
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                )
                {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize(),
                        color = if (bmiState.value.isEmpty()) {
                            Color(0,0,255,255)
                        } else {
                            getColorClassification(bmiState.value.toDouble())
                        },
                        shape = RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp
                        )
                    )
                    {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.your_score),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Text(
                                text = String.format("%.2f", if (bmiState.value.isEmpty()) 0.0 else bmiState.value.toDouble()),
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text =  bmiClassificationState.value,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Row() {
                                Button(onClick = { /*TODO*/ }
                                ) {
                                    Text(text = stringResource(id = R.string.reset))
                                }
                                Spacer(modifier = Modifier.width(48.dp))
                                Button(onClick = { /*TODO*/ }) {
                                    Text(text = stringResource(id = R.string.share))
                                }
                            }
                        }

                    }
                }
            }

        }

    }
}
