package com.example.diceroller

import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlin.random.Random

@Composable
fun PlayingPage(modifier: Modifier = Modifier, innerpadding:PaddingValues = PaddingValues(),
                navController: NavController) {
    var player1Score = remember {mutableStateOf(0)}
    var player2Score = remember {mutableStateOf(0)}
    var isPlayer1turn = remember {mutableStateOf(true)}
    var imageToShow = remember {mutableStateOf(0)}

    val imagesOfDice = listOf(
        R.drawable.startgame,
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )

    if (player1Score.value >= 20 || player2Score.value >= 20) {
        if (player1Score.value > player2Score.value) {
            WinningPlayer(innerPadding = innerpadding, winner = "Player 1", score = player1Score.value, navController = navController)
        } else {
            WinningPlayer(innerPadding = innerpadding, winner = "Player 2", score =  player2Score.value, navController = navController)
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xffD0BDF4))
                .padding(top = 100.dp)) {

            // Show which players turn
            Text(text = if (isPlayer1turn.value) "Player 1 turns" else "Player 2 turns",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(width = 300.dp, height = 50.dp)
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xffC2EDDA), shape = RoundedCornerShape(10.dp))
                    .padding(10.dp)
            )


            Spacer(
                modifier = Modifier.height(50.dp)
            )

            // Two buttons for showing the score of the players
            Row{
                // Player 1's score
                Text(
                    text = "Player 1 score: ${player1Score.value}",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .size(height = 60.dp, width = 120.dp)
                        .background(Color(0xffA0D2EB), shape = RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp)
                )
                Spacer (
                    modifier = Modifier.width(50.dp)
                )

                // Player 2's score
                Text(
                    text = "Player 2 score: ${player2Score.value}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(Color(0xffA0D2EB), shape = RoundedCornerShape(10.dp))
                        .size(width = 120.dp, height = 60.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(100.dp)
            )

            // Show the dice image one by one
            Image(
                painter = painterResource(imagesOfDice.get(imageToShow.value)),
                contentDescription = "Start game image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(width = 170.dp, height = 170.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp))
            )

            Spacer(
                modifier = Modifier.height(100.dp)
            )

            // Player will press the button to play their turns.
            Row {
                // 1st player
                ElevatedButton(
                    onClick = {
                        val firstPlayerGet = Random.nextInt(1, 7)
                        player1Score.value += firstPlayerGet
                        imageToShow.value = firstPlayerGet
                        isPlayer1turn.value = false
                    },
                    enabled = isPlayer1turn.value,
                    colors = ButtonDefaults.buttonColors(Color(0xffF9C5BD)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .size(width = 120.dp, height = 50.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(text = "Player 1",
                        color = Color.Black,
                        fontSize = 18.sp)
                }

                Spacer(
                    modifier = Modifier.width(30.dp)
                )

                // 2nd player
                ElevatedButton (
                    onClick = {
                        val secondPlayerGet = Random.nextInt(1, 7)
                        player2Score.value += secondPlayerGet
                        imageToShow.value = secondPlayerGet
                        isPlayer1turn.value = true
                    },
                    enabled = !isPlayer1turn.value,
                    colors = ButtonDefaults.buttonColors(Color(0xffF9C5BD)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .size(width = 120.dp, height = 50.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(text = "Player 2",
                        color = Color.Black,
                        fontSize = 18.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayingPage() {
    val navController = rememberNavController()
    PlayingPage(navController = navController)
}