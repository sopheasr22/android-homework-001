import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework.R

class ManageUser : ViewModel() {
    var name = mutableStateOf("")
    var gender = mutableStateOf("")
    var phoneNumber = mutableStateOf("")
    var address = mutableStateOf("")
}

@Composable
fun Reuseinput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = modifier
            .padding(vertical = 8.dp),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = keyboardOptions

    )
}

@Composable
fun FormScreen() {
    val user: ManageUser = viewModel()
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
        ) {
            Text(
                text = "Registration Form",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp,
            )
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier.padding(16.dp),

            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Reuseinput(
                label = "Full Name",
                value = user.name.value,
                onValueChange = { user.name.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = "please enter your name",
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),

            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Reuseinput(
                    label = "Gender",
                    value = user.gender.value,
                    onValueChange = { user.gender.value = it },
                    modifier = Modifier.weight(0.5f),
                    placeholder = "gender",
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                )
                Reuseinput(
                    label = "Phone Number",
                    value = user.phoneNumber.value,
                    onValueChange = { user.phoneNumber.value = it },
                    modifier = Modifier.weight(1f),
                    placeholder = "phone",
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                )
            }

            Reuseinput(
                label = "Address",
                value = user.address.value,
                onValueChange = { user.address.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = "enter your address",
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            )

             if (user.name.value.isEmpty() || user.gender.value.isEmpty() || user.phoneNumber.value.isEmpty()
                 ||user.address.value.isEmpty()){
                 Button(
                     onClick = { },
                     modifier = Modifier
                         .padding(top = 16.dp)
                         .fillMaxWidth(),
                     enabled = false
                 ) {
                     Text("Submit")
                 }

             }
            else {
                 Button(
                     onClick = { },
                     modifier = Modifier
                         .padding(top = 16.dp)
                         .fillMaxWidth(),
                     enabled = true
                 ) {
                     Text("Submit")
                 }
             }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormScreenPreview() {
    FormScreen()
}
