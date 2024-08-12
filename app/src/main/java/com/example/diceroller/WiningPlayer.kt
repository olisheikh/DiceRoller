package com.example.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.util.Locale

@Composable
fun WinningPlayer(modifier: Modifier = Modifier,
                  innerPadding: PaddingValues = PaddingValues(),
                  winner: String = "",
                  score: Int = 0,
                  navController: NavController
) {
    val imagesOfWinner = listOf(
        R.drawable.one,
        R.drawable.two
    )
    val currentWinner = if (winner.toLowerCase(Locale.ROOT).equals("player 1")) imagesOfWinner[0] else imagesOfWinner[1]

    var returnToPlayScreen by remember {mutableStateOf(false)}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xffD0BDF4))
            .padding(top = 200.dp)
    ) {
        Text(
            text = "$score",
            fontSize = 60.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .size(width = 300.dp, height = 85.dp)
                .background(color = Color(0xffA0D2EB))
                .padding(10.dp)
        )

        Text(
            text = "The Winner is Player",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
            modifier = Modifier.padding(top = 50.dp)
        )
        
        Image(
            painter = painterResource(currentWinner),
            contentDescription = "one",
            modifier = Modifier
                .size(height = 217.dp, width = 45.dp)
                .padding(top = 100.dp)
        )

        Spacer(
            modifier = Modifier.height(50.dp)
        )
        ElevatedButton(
            onClick = {
                navController.navigate("playingPage")
            },
            content = {Text("Restart", fontSize = 18.sp)},
            modifier = Modifier.size(width = 300.dp, height = 50.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewWinningPlayer() {
    val navController = rememberNavController()
    WinningPlayer(navController = navController)
}