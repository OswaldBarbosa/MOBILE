package br.senai.sp.jandira.bmicalc.model.calcs

import android.content.Context
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.bmicalc.R
import kotlin.math.pow

fun bmiCalculate(weight: Double, heigth: Double): Double {
    return weight / (heigth / 100).pow(2)
}

fun getBmiClassification(bmi: Double, context: Context): String {

    return if (bmi <= 18.5) {
        context.getString(R.string.under_weight)
    } else if (bmi > 18.5 && bmi < 25.0) {
        context.getString(R.string.normal_weight)
    } else if (bmi >= 25.0 && bmi < 30.0) {
        context.getString(R.string.over_weigth)
    } else if (bmi >= 30.0 && bmi < 40) {
        context.getString(R.string.obesity)
    } else {
        context.getString(R.string.morbity_obesity)
    }

}

fun getColorClassification(bmi: Double): Color {

    return if (bmi <= 18.5) {
        Color(255,165,0)
    } else if (bmi > 18.5 && bmi < 25.0) {
        Color(0,255,0)
    } else if (bmi >= 25.0 && bmi < 30.0) {
        Color(255,165,0)
    } else if (bmi >= 30.0 && bmi < 40) {
        Color(255,0,0)
    } else {
        Color(255,0,0)
    }

}