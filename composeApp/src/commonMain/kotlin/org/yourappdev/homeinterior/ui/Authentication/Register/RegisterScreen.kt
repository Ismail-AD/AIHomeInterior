package org.yourappdev.homeinterior.ui.Authentication.Register

import StackedSnackbarHost
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import homeinterior.composeapp.generated.resources.Res
import homeinterior.composeapp.generated.resources.emailicon
import homeinterior.composeapp.generated.resources.hide_
import homeinterior.composeapp.generated.resources.passicon
import homeinterior.composeapp.generated.resources.person
import homeinterior.composeapp.generated.resources.show_1_
import org.jetbrains.compose.resources.painterResource
import org.yourappdev.homeinterior.data.remote.util.ResultState
import org.yourappdev.homeinterior.ui.UiUtils.BackIconButton
import org.yourappdev.homeinterior.ui.UiUtils.ClickableText
import org.yourappdev.homeinterior.ui.UiUtils.ProgressLoading
import org.yourappdev.homeinterior.ui.UiUtils.rememberCustomSnackbarState
import org.yourappdev.homeinterior.ui.theme.buttonBack
import org.yourappdev.homeinterior.ui.theme.smallText
import rememberStackedSnackbarHostState


@Composable
fun RegisterRoot(viewModel: RegisterViewModel, onRegisterSuccess: () -> Unit) {
    val state by viewModel.registerState.collectAsState()
    RegisterScreen(state, viewModel::onRegisterFormEvent, onRegisterSuccess)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    state: RegisterState,
    onRegisterEvent: (event: RegisterEvent) -> Unit,
    onRegisterSuccess: () -> Unit
) {
    val snackBarState = rememberCustomSnackbarState()

//    LaunchedEffect(state.registerResponse, key2 = state.fieldError) {
//        if (state.fieldError != null) {
//            snackBarState.showSnackbar(
//                message = state.fieldError,
//                duration = SnackbarDuration.Short
//            )
//        } else if (state.registerResponse is ResultState.Failure) {
//            snackBarState.showSnackbar(
//                message = state.registerResponse.msg,
//                duration = SnackbarDuration.Short
//            )
//        } else if (state.registerResponse is ResultState.Success) {
//            onRegisterSuccess()
//        }
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .statusBarsPadding()
    ) {
        if (state.registerResponse is ResultState.Loading) {
            ProgressLoading()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BackIconButton {

            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Register",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF9CA986)
            )

            Text(
                text = "From imagination to inspiration—AI designs it for you.",
                fontSize = 14.sp,
                color = smallText,
                modifier = Modifier.padding(top = 8.dp),
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Email field
            Text(
                text = "Email",
                fontSize = 14.sp,
                color = smallText,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = state.email,
                onValueChange = { onRegisterEvent(RegisterEvent.EmailUpdate(it)) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        "Ex. abc@example.com",
                        color = Color(0xFFCCCCCC)
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.emailicon),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = LocalContentColor.current)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedBorderColor = buttonBack,
                    focusedLeadingIconColor = buttonBack,
                    unfocusedLeadingIconColor = Color(0xffDBDBDB)
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Name field
            Text(
                text = "Your Name",
                fontSize = 14.sp,
                color = smallText,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = state.username,
                onValueChange = { onRegisterEvent(RegisterEvent.NameUpdate(it)) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        "Ex. Soul Ramirez",
                        color = Color(0xFFCCCCCC)
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.person),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = LocalContentColor.current)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedBorderColor = buttonBack,
                    focusedLeadingIconColor = buttonBack,
                    unfocusedLeadingIconColor = Color(0xffDBDBDB)
                ),
                singleLine = true

            )


            Spacer(modifier = Modifier.height(20.dp))

            // Password field
            Text(
                text = "Your Password",
                fontSize = 14.sp,
                color = smallText,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = state.password,
                onValueChange = { onRegisterEvent(RegisterEvent.PasswordUpdate(it)) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        "••••••••••",
                        color = Color(0xFFCCCCCC)
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.passicon),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = LocalContentColor.current)
                    )
                },
                trailingIcon = {
                    Box(modifier = Modifier.size(30.dp).clip(CircleShape).clickable {
                        onRegisterEvent(RegisterEvent.TogglePassword(!state.showPassword))
                    }, contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(if (state.showPassword) Res.drawable.show_1_ else Res.drawable.hide_),
                            contentDescription = "Close",
                            colorFilter = ColorFilter.tint(color = buttonBack),
                            modifier = Modifier.size(23.dp)
                        )
                    }
                },
                singleLine = true,
                visualTransformation = if (state.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedBorderColor = buttonBack,
                    focusedLeadingIconColor = buttonBack,
                    unfocusedLeadingIconColor = Color(0xffDBDBDB)
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Register button
            Button(
                onClick = {
                    onRegisterEvent(RegisterEvent.Register)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonBack
                )
            ) {
                Text(
                    text = "Register",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Login link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account? ",
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )
                ClickableText(title = "Login", textSize = 14.sp, fontWeight = FontWeight.Bold) {

                }
            }
        }
    }
}