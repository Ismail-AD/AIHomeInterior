package org.yourappdev.homeinterior.ui.Authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import homeinterior.composeapp.generated.resources.Res
import homeinterior.composeapp.generated.resources.emailicon
import homeinterior.composeapp.generated.resources.google
import org.jetbrains.compose.resources.painterResource
import org.yourappdev.homeinterior.ui.UiUtils.ButtonWithIcon
import org.yourappdev.homeinterior.ui.UiUtils.ClickableText
import org.yourappdev.homeinterior.ui.theme.buttonBack
import org.yourappdev.homeinterior.ui.theme.smallText

@Composable
fun WelcomeScreen(
    onContinueWithGoogle: () -> Unit = {},
    onContinueWithEmail: () -> Unit = {},
    onLogin: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Welcome to",
            fontSize = 16.sp,
            color = smallText
        )

        Text(
            text = "Home Interior AI",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF9CA986)
        )

        Text(
            text = "Interiors shaped by your imagination, perfected by AI.",
            fontSize = 14.sp,
            color = smallText,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Let's Get Started...",
            fontSize = 14.sp,
            color = Color(0xFF666666)
        )

        Spacer(modifier = Modifier.height(28.dp))

        ButtonWithIcon(image = Res.drawable.google, borderColor = buttonBack, title = "Continue with Google")

        Spacer(modifier = Modifier.height(12.dp))

        ButtonWithIcon(image = Res.drawable.emailicon, title = "Continue with Email")

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
                color = Color.Black,
                lineHeight = 1.sp,
                fontWeight = FontWeight.Normal,
            )
            ClickableText(title = "Login", textSize = 14.sp, fontWeight = FontWeight.Bold) {

            }
        }
    }
}


