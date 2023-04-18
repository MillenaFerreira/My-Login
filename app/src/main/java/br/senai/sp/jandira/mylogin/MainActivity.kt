package br.senai.sp.jandira.mylogin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.mylogin.reposity.UserRepository
import br.senai.sp.jandira.mylogin.ui.theme.MyLoginTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLoginTheme {
                LoginScreen()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreen() {

    // se utilizado o by para evitar colocar o value toda vez que eu chamar a função
    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }
    var passwordVisibilityState by remember {
        mutableStateOf(false)
    }

    //contexto atual
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            //Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Surface(
                    modifier = Modifier
                        .width(120.dp)
                        .height(40.dp),
                    color = colorResource(id = R.color.pink_login),
                    shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 15.dp)
                ) {}
            }

            Spacer(modifier = Modifier.height(140.dp))

            //Form
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(17.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.title_login),
                    fontSize = 40.sp,
                    color = colorResource(id = R.color.pink_login),
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = stringResource(id = R.string.subtitle_login),
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(87.dp))

                OutlinedTextField(
                    value = emailState,
                    onValueChange = { emailState = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    label = {
                        Text(
                            text = stringResource(id = R.string.email)
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.baseline_email_24
                            ),
                            contentDescription = "",
                            tint = colorResource(id = R.color.pink_login)
                        )
                    }


                )

                Spacer(modifier = Modifier.height(31.dp))

                OutlinedTextField(
                    value = passwordState,
                    onValueChange = { passwordState = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    visualTransformation =
                        if(!passwordVisibilityState){
                            PasswordVisualTransformation()
                        }else{
                            VisualTransformation.None
                         },
                    label = {
                        Text(
                            text = stringResource(id = R.string.password)
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.baseline_lock_24
                            ),
                            contentDescription = "",
                            tint = colorResource(id = R.color.pink_login)
                        )
                    },

                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibilityState = !passwordVisibilityState
                            }
                        ) {
                            Icon(
                                imageVector =
                                if(passwordVisibilityState){
                                    Icons.Default.VisibilityOff
                                } else {
                                    Icons.Default.Visibility
                                },
                                contentDescription = null,
                                tint = colorResource(id = R.color.pink_login)

                            )
                        }

                    }
                )

                Spacer(modifier = Modifier.height(31.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Button(
                        onClick = {
                                authenticate(
                                    emailState, passwordState, context
                                )
                                  },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .height(48.dp)
                            .width(134.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.pink_login))
                    ) {
                        Text(
                            text = stringResource(id = R.string.sign_up).uppercase(),
                            color = Color.White
                        )
                        Icon(
                            painter = painterResource(
                                id = R.drawable.baseline_east_24
                            ),
                            tint = Color.White,
                            contentDescription = ""
                        )
                    }
                }
                Spacer(modifier = Modifier.height(31.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.have_account)
                        )
                        Row(
                            modifier = Modifier.padding(start = 3.dp),
                        ) {
                            TextButton(
                                onClick = {
                                    var openSignUp = Intent(context, SignUpActivity::class.java)

                                    //start a Activity
                                    context.startActivity(openSignUp)
                                }
                            ) {
                                Text(
                                    text = stringResource(id = R.string.sign_up),
                                    color = colorResource(id = R.color.pink_login)
                                )
                            }
                        }
                    }
                }

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Surface(
                    modifier = Modifier
                        .width(120.dp)
                        .height(40.dp),
                    color = colorResource(id = R.color.pink_login),
                    shape = RoundedCornerShape(0.dp, 15.dp, 0.dp, 0.dp)
                ) {}
            }

        }

    }

}

fun authenticate(
    email: String,
    password: String,
    context : Context
) {
    val userRepository = UserRepository(context)
    val user = userRepository.authenticate(email, password)

    if (user != null){
        val openHome = Intent(context, HomeActivity::class.java)
        context.startActivity(openHome)
    }else{

    }
}
