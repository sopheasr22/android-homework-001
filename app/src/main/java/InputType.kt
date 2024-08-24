import android.annotation.SuppressLint
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

data class User(
    var name: String = "",
    var gender: String = "",
    var phoneNumber: String = "",
    var address: String = "",
)
class ManageUser : ViewModel() {
    var userData = mutableStateOf(User())

    fun UpdateName(input: String) {
        userData.value = userData.value.copy(
            name = input
        )
    }
    fun UpdateGender(input: String) {
        userData.value = userData.value.copy(
            gender = input
        )
    }
    fun UpdatePhone(input: String) {
        userData.value = userData.value.copy(
            phoneNumber = input
        )
    }
    fun UpdateAddress(input: String) {
        userData.value = userData.value.copy(
            address = input
        )
    }
}

@Composable
fun TextFiledComponent(
    placeholder: String,
    label: String,
    onTextChange: (String) -> Unit,
    event: String,
    vm: ManageUser,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions
) {
    OutlinedTextField(
        value = if (event == "name") vm.userData.value.name
        else if (event == "gender") {
            vm.userData.value.gender
        } else if (event == "phone") {
            vm.userData.value.phoneNumber
        } else {
            vm.userData.value.address
        },
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) },
        onValueChange = { onTextChange(it) },
        modifier = modifier.padding(8.dp),
        keyboardOptions = keyboardOptions
    )

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun FormScreen() {
    val user: ManageUser = viewModel()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
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

            TextFiledComponent(
                placeholder = "Enter your Name",
                label = "Name",
                {
                    user.UpdateName(it)
                },
                "name",
                user,
                Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextFiledComponent(
                    placeholder = "Gender",
                    label = "gender",
                    {
                        user.UpdateGender(it)
                    },
                    "gender",
                    user,
                    modifier = Modifier.weight(0.5f),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                )
                TextFiledComponent(
                    placeholder = "phone",
                    label = "phone",
                    {
                        user.UpdatePhone(it)
                    },
                    "phone",
                    user, modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                )
            }

            TextFiledComponent(
                placeholder = "address",
                label = "address",
                {
                    user.UpdateAddress(it)
                },
                "address",
                user, Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            )


            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                enabled = user.userData.value.name.isNotEmpty() &&
                        user.userData.value.gender.isNotEmpty() &&
                        user.userData.value.phoneNumber.isNotEmpty() &&
                        user.userData.value.address.isNotEmpty()
            ) {
                Text("Submit")
            }
             }

        }



@Preview(showBackground = true)
@Composable
fun FormScreenPreview() {
    FormScreen()
}
