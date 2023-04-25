package br.senai.sp.jandira.mylogin

import android.os.Bundle
import android.security.AttestedKeyPair
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.mylogin.dao.repository.LocationRepository
import br.senai.sp.jandira.mylogin.dao.repository.ProductRepository
import br.senai.sp.jandira.mylogin.model.Location
import br.senai.sp.jandira.mylogin.model.Product
import br.senai.sp.jandira.mylogin.ui.theme.MyLoginTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLoginTheme {
                Home(
                    ProductRepository.getProductList(),
                    LocationRepository.getLocationList()
                )
            }
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)

@Composable
fun Home(
    products: List<Product>,
    locations: List<Location>
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.paris
                    ),
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier
                        .height(220.dp)
                        .padding(15.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.susanna_profile
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .size(61.dp)
                                .border(
                                    BorderStroke(4.dp, Color.White),
                                    CircleShape
                                )
                                .padding(4.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop

                        )
                        Text(
                            text = stringResource(id = R.string.name_user),
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_location_on_24),
                                contentDescription = "",
                                tint = Color.White
                            )
                            Text(
                                text = stringResource(id = R.string.your_in_paris),
                                modifier = Modifier
                                    .padding(start = 3.dp),
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400
                            )
                        }
                        Column(modifier = Modifier.padding(start = 24.dp)) {
                            Text(
                                text = stringResource(id = R.string.my_trips),
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W700
                            )
                        }

                    }
                }

            }

            Spacer(modifier = Modifier.height(13.dp))

            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column() {
                            Text(
                                text = stringResource(id = R.string.categories),
                                modifier = Modifier
                                    .padding(start = 17.dp)
                            )
                            Spacer(modifier = Modifier.height(7.dp))
                            LazyRow(
                                modifier = Modifier
                                    .padding(start = 19.dp)
                            ) {
                                items(products) { product ->
                                    Card(
                                        modifier = Modifier
                                            .width(109.dp) //width 125 e height 75 modificados para se adequar a tela(tamanho não está igual do figma)
                                            .height(64.dp),
                                        backgroundColor = colorResource(id = R.color.pink_login)

                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxSize().padding(8.dp),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                painter = product.image ?: painterResource(
                                                    id = R.drawable.baseline_lock_24
                                                ),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .size(32.dp),
                                                tint = Color.White
                                            )
                                            Text(
                                                text = product.name,
                                                color = Color.White,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 16.dp,
                                    end = 17.dp
                                ),
                            shape = RoundedCornerShape(16.dp),
                            label = {
                                Text(
                                    text = stringResource(
                                        id = R.string.search_your_destiny
                                    )
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(
                                        id = R.drawable.baseline_search_24
                                    ),
                                    contentDescription = "",
                                    tint = Color.Gray
                                )
                            }


                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column() {
                            Text(
                                text = stringResource(id = R.string.past_trips),
                                modifier = Modifier
                                    .padding(start = 17.dp)
                            )
                            Spacer(modifier = Modifier.height(9.dp))
                            LazyColumn(
                                modifier = Modifier
                                    .padding(start = 17.dp, end = 18.dp)
                            ) {
                                items(locations) { locations ->
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(208.dp),
                                        elevation = 8.dp
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .padding(5.dp)
                                                .fillMaxWidth(),
                                            verticalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Image(
                                                painter = locations.image ?:
                                                painterResource(
                                                    id = R.drawable.baseline_lock_24
                                                ),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(106.dp)
                                                    .clip(shape = RoundedCornerShape(10.dp)),
                                                contentScale = ContentScale.Crop
                                            )
                                            Row() {
                                                Text(
                                                    text = locations.name,
                                                    fontSize = 14.sp,
                                                    color = colorResource(id = R.color.pink_login),
                                                    fontWeight = FontWeight.W400,
                                                    lineHeight = 15.sp
                                                )
                                                Spacer(modifier = Modifier.width(3.dp))
                                                Text(
                                                    text = locations.year,
                                                    fontSize = 14.sp,
                                                    color = colorResource(id = R.color.pink_login),
                                                    fontWeight = FontWeight.W400,
                                                    lineHeight = 15.sp
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(5.dp))
                                            Text(
                                                text = locations.description,
                                                modifier = Modifier.fillMaxWidth(),
                                                fontSize = 10.sp,
                                                color = Color.Gray,
                                                fontWeight = FontWeight.W400
                                            )
                                            Spacer(modifier = Modifier.width(13.dp))
                                            Text(
                                                text = locations.data_start_final,
                                                modifier = Modifier.fillMaxWidth(),
                                                textAlign = TextAlign.Right,
                                                fontSize = 10.sp,
                                                color = colorResource(id = R.color.pink_login)
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(11.dp))
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MyLoginTheme {
        Home(
            ProductRepository.getProductList(),
            LocationRepository.getLocationList()
        )
    }
}

