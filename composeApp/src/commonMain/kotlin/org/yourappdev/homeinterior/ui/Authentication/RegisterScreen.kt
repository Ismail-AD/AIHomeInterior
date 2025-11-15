package org.yourappdev.homeinterior.ui.Authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import homeinterior.composeapp.generated.resources.Res
import homeinterior.composeapp.generated.resources.backicon
import homeinterior.composeapp.generated.resources.emailicon
import homeinterior.composeapp.generated.resources.hide_
import homeinterior.composeapp.generated.resources.page_
import homeinterior.composeapp.generated.resources.passicon
import homeinterior.composeapp.generated.resources.person
import homeinterior.composeapp.generated.resources.show_1_
import org.jetbrains.compose.resources.painterResource
import org.yourappdev.homeinterior.ui.UiUtils.BackIconButton
import org.yourappdev.homeinterior.ui.UiUtils.ClickableText
import org.yourappdev.homeinterior.ui.theme.buttonBack
import org.yourappdev.homeinterior.ui.theme.smallText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onRegister: () -> Unit = {}, onBack: () -> Unit = {}) {
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var show by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .statusBarsPadding()
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
            value = email,
            onValueChange = { email = it },
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
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
            value = name,
            onValueChange = { name = it },
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
            )
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
            value = password,
            onValueChange = { password = it },
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
                    show = !show
                }, contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(if (show) Res.drawable.show_1_ else Res.drawable.hide_),
                        contentDescription = "Close",
                        colorFilter = ColorFilter.tint(color = buttonBack),
                        modifier = Modifier.size(23.dp)
                    )
                }
            },
            visualTransformation = if (show) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = buttonBack,
                focusedLeadingIconColor = buttonBack,
                unfocusedLeadingIconColor = Color(0xffDBDBDB)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Register button
        Button(
            onClick = onRegister,
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