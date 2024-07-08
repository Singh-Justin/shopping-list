package com.example.shopping_list.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class ShoppingItem(val name: String, val quantity: String)

@Composable
fun ShoppingList(
    items: List<ShoppingItem>, onEdit: (index: Int, values: ShoppingItemFormValues) -> Unit,
    onDelete: (index: Int) -> Unit

) {
    Column {

        if (items.isEmpty()) {
            return Text("No items.")
        }

        LazyColumn {
            itemsIndexed(items) { index, item ->
                ShoppingListItem(
                    item = item,
                    onEdit = { onEdit(index, it) },
                    onDelete = { onDelete(index) })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }


    }
}

@Composable
fun UpdateShoppingItemIconButton(
    item: ShoppingItem,
    onSubmit: (values: ShoppingItemFormValues) -> Unit
) {

    var isDialogVisible by remember { mutableStateOf(false) }

    IconButton(
        onClick = { isDialogVisible = true },
    ) {

        Icon(Icons.Filled.Edit, contentDescription = "Edit")
    }

    val handleSubmit: (values: ShoppingItemFormValues) -> Unit = { values ->
        onSubmit(values)
        isDialogVisible = false
    }


    if (isDialogVisible) {

        val initialValues = ShoppingItemFormValues(
            item.name,
            item.quantity
        )

        AlertDialog(
            onDismissRequest = { isDialogVisible = false },
            confirmButton = { },
            text = {
                ShoppingItemForm(
                    initialValues = initialValues,
                    onCancel = { isDialogVisible = false },
                    onSubmit = handleSubmit
                )
            }
        )
    }
}


@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEdit: (values: ShoppingItemFormValues) -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .padding(8.dp)
            .fillMaxWidth()


    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text("Name")
            Text(item.name)

        }

        Column(modifier = Modifier.weight(1f)) {
            Text("Quantity")
            Text(item.quantity)
        }

        Column {
            UpdateShoppingItemIconButton(item, onSubmit = onEdit)
        }


        Column {
            IconButton(
                onClick = { onDelete() },
            ) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete")
            }
        }

    }

}