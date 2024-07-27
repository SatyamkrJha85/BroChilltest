package com.theapplicationpad.brochilltest.Mvvm.View

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.theapplicationpad.brochilltest.Mvvm.Model.Tweet
import com.theapplicationpad.brochilltest.ui.theme.Green1


@Composable
fun TweetItem(tweet: Tweet, modifier: Modifier = Modifier) {
    Card(
        modifier

            .padding(10.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(60.dp)


            .border(width = 1.dp, color = Color.Blue, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(Green1)

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth().padding(top = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = tweet.tweet.toString(),
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}



@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    triling: (@Composable () -> Unit)? = null
) {
    Column {
        Text(text = label)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .shadow(3.dp, RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value, onValueChange = onValueChange,
                visualTransformation = visualTransformation,
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    disabledTextColor = Color.Black,
                    unfocusedContainerColor = Color.White,

                    ), trailingIcon = triling
            )
        }
    }

}