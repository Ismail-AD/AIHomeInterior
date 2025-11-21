package org.yourappdev.homeinterior.data.remote.service

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Parameters
import io.ktor.http.parameters
import org.yourappdev.homeinterior.domain.model.RegisterRequest
import org.yourappdev.homeinterior.domain.model.RegisterResponse

class AuthService(val client: HttpClient) {
    suspend fun register(registerRequest: RegisterRequest) = client.post("auth/register") {
        setBody(
            FormDataContent(
                Parameters.build {
                    append("fullname", registerRequest.fullname)
                    append("email", registerRequest.email)
                    append("password", registerRequest.password)
                }
            )
        )
    }

    suspend fun verifyOtp(email: String, otp: String) = client.post("auth/verify-otp") {
        setBody(
            FormDataContent(
                Parameters.build {
                    append("email", email)
                    append("otp", otp)
                }
            )
        )
    }

    suspend fun resendOtp(email: String) = client.post("auth/resend-otp") {
        setBody(
            FormDataContent(
                Parameters.build {
                    append("email", email)
                }
            )
        )
    }

    suspend fun forgetPasswordRequest(email: String) = client.post("auth/forgot-password/send") {
        setBody(
            FormDataContent(
                Parameters.build {
                    append("email", email)
                }
            )
        )
    }

    suspend fun forgetPasswordVerify(email: String, otp: String) = client.post("auth/forgot-password/verify") {
        setBody(
            FormDataContent(
                Parameters.build {
                    append("email", email)
                    append("otp", otp)
                }
            )
        )
    }

    suspend fun forgetPasswordReset(email: String, password: String, confirm_password: String) =
        client.post("auth/forgot-password/verify") {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("email", email)
                        append("password", password)
                        append("password_confirmation", confirm_password)
                    }
                )
            )
        }

    suspend fun login(email: String, password: String) = client.post("auth/login") {
        parameter("email", email)
        parameter("password", password)
    }
}