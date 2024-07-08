package com.example.shopping_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class ShoppingItemFormValues(
    var name: String = "",
    var quantity: String = ""
)

val INITIAL_VALUES = ShoppingItemFormValues()

@Composable
fun ShoppingItemForm(
    initialValues: ShoppingItemFormValues = INITIAL_VALUES,
    onCancel: () -> Unit,
    onSubmit: (ShoppingItemFormValues) -> Unit,
) {

    var values by remember {
        mutableStateOf(initialValues)
    }



    Column {
        OutlinedTextField(
            label = { Text("Name") },
            value = values.name,
            onValueChange = {
                values = values.copy(name = it)
            })
        OutlinedTextField(
            label = { Text("Quantity") },
            value = values.quantity,
            onValueChange = {
                values = values.copy(quantity = it)
            })


        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(
                onClick = { onCancel() }, modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text("Cancel")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { onSubmit(values) }, modifier = Modifier.weight(1f)
            ) {
                Text("Submit")
            }
        }
    }


}