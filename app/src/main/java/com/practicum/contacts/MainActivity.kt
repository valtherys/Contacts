package com.practicum.contacts

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.contacts.ui.theme.Contact
import com.practicum.contacts.ui.theme.TestData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                ContactDetails(TestData.strangeNeighbor)
            }
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContactPreview(contact.imageRes, contact.name, contact.familyName)
        FullName(contact.name, contact.surname, contact.familyName, contact.isFavorite)
        Info(contact.phone, contact.email, contact.address)
    }
}

@Composable
fun FullName(name: String, surname: String?, familyName: String, isFavorite: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "$name ${surname ?: ""} ",
            style = TextStyle(color = Color.Black, fontWeight = FontWeight.W500, fontSize = 16.sp)
        )
        Row {
            Text(
                familyName,
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.W400,
                    fontSize = 20.sp
                )
            )
            if (isFavorite) {
                Image(
                    modifier = Modifier.padding(start = 12.dp),
                    painter = painterResource(android.R.drawable.star_big_on),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ContactPreview(image: Int?, name: String, familyName: String) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .padding(12.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (image != null) {
            Photo(image)
        } else {
            Initials(name, familyName)
        }
    }
}

@Composable
fun Info(phoneInfo: String, emailInfo: String?, addressInfo: String) {
    val tel = stringResource(R.string.phone)
    val email = stringResource(R.string.email)
    val address = stringResource(R.string.address)
    Column(modifier = Modifier.padding(top = 20.dp)) {
        InfoRow(tel, phoneInfo)
        InfoRow(address, addressInfo)
        InfoRow(email, emailInfo)
    }
}

@Composable
fun Initials(name: String, familyName: String) {
    val initials = (name.take(1) + familyName.take(1)).uppercase()
    Box(
        modifier = Modifier
            .fillMaxHeight(), contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier =
                Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.circle_orange),
            contentDescription = null,
            tint = Color.LightGray,
        )
        Text(
            initials,
            style = TextStyle(color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.W500)
        )
    }
}

@Composable
fun InfoRow(row: String, info: String?) {
    Row(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(
            "$row: ",
            modifier = Modifier.weight(0.5F),
            textAlign = TextAlign.End,
            fontStyle = FontStyle.Italic
        )
        Text(
            info ?: "---",
            modifier = Modifier.weight(0.5F),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodySmall
        )
    }
}


@Composable
fun Photo(photo: Int) {
    Image(
        painter = painterResource(photo),
        contentDescription = "Contact photo",
        modifier = Modifier.fillMaxHeight(), contentScale = ContentScale.FillHeight
    )
}


@Preview(showBackground = false, showSystemUi = true)
@Composable
fun ContactTest() {
    ContactDetails(contact = TestData.noPhotoGuy)
}

@Preview(showBackground = false, showSystemUi = true)
@Composable
fun ContactTest2() {
    ContactDetails(contact = TestData.strangeNeighbor)
}