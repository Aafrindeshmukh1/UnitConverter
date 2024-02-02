package com.example.unitconverter

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputValue by remember{ mutableStateOf("")}
    var outputValue by remember{ mutableStateOf("") }
    var inputType by remember{ mutableStateOf("meter") }
    var outputType by remember{ mutableStateOf("meter") }
    var conversionFactor=remember{ mutableStateOf(1.0) }
    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpanded by remember {
        mutableStateOf(false)
    }
    var oConversionFactor= remember {
        mutableStateOf(1.0)
    }

    fun convertUnit(){
        var inputValueDouble=inputValue.toDouble()?:0.0
       var result=(inputValueDouble * conversionFactor.value *100/oConversionFactor.value).roundToInt()/100.0
        outputValue=result.toString()
    }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
                                   inputValue=it
            convertUnit()
        },
            label = { Text(text = "Enter Value")})
        //OutlinedTextField(value = inputValue, onValueChange ={inputValue=it} )
        Spacer(modifier = Modifier.padding(16.dp))
        Row {
            Box {
                Button(onClick = {
                    iExpanded=true
                }) {
                    Text(text = inputType)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription ="" )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "centimeter") }, onClick = {
                        iExpanded=false
                        inputType="centimeter"
                        conversionFactor.value=0.01
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "meter") }, onClick = {
                        iExpanded=false
                        inputType="meter"
                        conversionFactor.value=1.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        iExpanded=false
                        inputType="Feet"
                        conversionFactor.value=0.3048
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "millimeter") }, onClick = {
                        iExpanded=false
                        inputType="millimeter"
                        conversionFactor.value=0.001
                        convertUnit()
                    })
                }
               
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded=true}) {
                    Text(text = outputType)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "centimeter") }, onClick = {
                        oExpanded=false
                        outputType="centimeter"
                        oConversionFactor.value=0.01
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "meter") }, onClick = {
                        oExpanded=false
                        outputType="meter"
                        oConversionFactor.value=1.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded=false
                        outputType="Feet"
                        oConversionFactor.value=0.3048
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "millimeter") }, onClick = { /*TODO*/
                        oExpanded=false
                        outputType="millimeter"
                        oConversionFactor.value=0.001
                        convertUnit()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "Result:${outputValue} ${outputType}", style = MaterialTheme.typography.headlineMedium)
    }
    
}
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}