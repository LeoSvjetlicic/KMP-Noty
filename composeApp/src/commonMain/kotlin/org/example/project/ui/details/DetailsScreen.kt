package org.example.project.ui.details

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import org.example.project.ui.main.viewstates.NoteViewState
import org.ls.notey.utils.SALMON

@Composable
fun DetailsScreen(
    note: NoteViewState,
    modifier: Modifier = Modifier,
    onDoneClick: (String, String) -> Unit
) {
    var inputName by remember { mutableStateOf("") }
    var inputDescription by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(note) {
        inputName = note.topic
        inputDescription = note.description
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            val nameInteractionSource = remember { MutableInteractionSource() }
            val nameIsFocused by nameInteractionSource.collectIsFocusedAsState()
            TextField(
                value = inputName,
                onValueChange = { inputName = it },
                singleLine = true,
                placeholder = { Text(text = "Title", color = Color.LightGray) },
                modifier = Modifier.focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            keyboardController?.show()
                        }
                    }
                    .fillMaxWidth()
                    .border(
                        width = if (nameIsFocused) 2.dp else 0.5.dp,
                        color = SALMON,
                        shape = RoundedCornerShape(20.dp)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = SALMON
                ),
                interactionSource = nameInteractionSource
            )

            Spacer(Modifier.height(16.dp))

            val descriptionInteractionSource = remember { MutableInteractionSource() }
            val descriptionIsFocused by descriptionInteractionSource.collectIsFocusedAsState()
            TextField(
                value = inputDescription,
                onValueChange = { inputDescription = it },
                placeholder = { Text(text = "Content", color = Color.LightGray) },
                modifier = Modifier.focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            // Show keyboard when focused
                            keyboardController?.show()
                        }
                    }
                    .heightIn(min = 150.dp, max = 350.dp)
                    .fillMaxWidth()
                    .border(
                        width = if (descriptionIsFocused) 2.dp else 0.5.dp,
                        color = SALMON,
                        shape = RoundedCornerShape(20.dp)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = SALMON
                ),
                interactionSource = descriptionInteractionSource
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                modifier = Modifier.align(Alignment.Center),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp
                ),
                onClick = { onDoneClick(inputName, inputDescription) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = SALMON,
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(16.dp, 6.dp),
                    text = "Done",
                    color = Color.White
                )
            }
        }
    }
}
