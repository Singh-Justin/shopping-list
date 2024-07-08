package com.example.shopping_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopping_list.components.AddShoppingItemButton
import com.example.shopping_list.components.ShoppingItem
import com.example.shopping_list.components.ShoppingItemFormValues
import com.example.shopping_list.components.ShoppingList
import com.example.shopping_list.ui.theme.ShoppinglistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppinglistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {

    var shoppingItems by remember {
        mutableStateOf(listOf<ShoppingItem>())
    }


    val handleSubmit: (values: ShoppingItemFormValues) -> Unit = { values ->
        val newItem = ShoppingItem(values.name, values.quantity)
        shoppingItems = listOf(newItem).plus(shoppingItems)
    }

    val handleDelete: (index: Int) -> Unit = { index ->
        shoppingItems = shoppingItems.filterIndexed { i, _ -> i != index }
    }

    val handleEdit: (index: Int, values: ShoppingItemFormValues) -> Unit = { index, values ->
        shoppingItems = shoppingItems.mapIndexed { i, item ->
            if (i == index) {
                ShoppingItem(values.name, values.quantity)
            } else {
                item
            }
        }

    }



    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),

            ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                ShoppingList(
                    items = shoppingItems,
                    onEdit = handleEdit,
                    onDelete = handleDelete
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            AddShoppingItemButton(onSubmit = handleSubmit)
        }
    }

}
