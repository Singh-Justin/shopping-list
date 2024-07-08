package com.example.shopping_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun AddShoppingItemButton(onSubmit: (values: ShoppingItemFormValues) -> Unit) {

    var isDialogVisible by remember { mutableStateOf(false) }

    Button(onClick = { isDialogVisible = true }, modifier = Modifier.fillMaxWidth()) {
        Text("Add Item")
    }


    val handleSubmit = { values: ShoppingItemFormValues ->
        onSubmit(values)
        isDialogVisible = false
    }


    if (isDialogVisible) {
        AlertDialog(
            onDismissRequest = { isDialogVisible = false },
            confirmButton = { },
            text = {
                ShoppingItemForm(
                    onCancel = { isDialogVisible = false },
                    onSubmit = handleSubmit
                )
            }
        )
    }
}