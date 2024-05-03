package com.target.targetcasestudy.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.target.targetcasestudy.domain.model.Product
import com.target.targetcasestudy.ui.compose.theme.DarkGreyColor
import com.target.targetcasestudy.ui.compose.theme.GREEN
import com.target.targetcasestudy.ui.compose.theme.GreyColor
import com.target.targetcasestudy.ui.compose.theme.RED
import com.target.targetcasestudy.ui.compose.viewmodel.DealListComposeViewModel

@Composable
fun DealList(
    viewModel: DealListComposeViewModel = hiltViewModel(),
    onItemClick: (id: Int) -> Unit
) {
    val deals = viewModel.getDeals().collectAsState()
    LazyColumn(verticalArrangement = Arrangement.Absolute.spacedBy(2.dp)) {
        items(deals.value.orEmpty()) {
            DealListItem(product = it, onItemClick)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DealListItem(product: Product, onItemClick: (id: Int) -> Unit) {
    val availability by remember {
        derivedStateOf {
            buildAnnotatedString {
                withStyle(SpanStyle(color = GREEN)) {
                    append("In stock")
                }
                withStyle(SpanStyle(color = GreyColor)) {
                    append(" in aisle ${product.aisle}")
                }
            }
        }
    }
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .clickable {
                onItemClick(product.id)
            }
    ) {
        GlideImage(
            model = product.imageUrl,
            contentDescription = "",
            modifier = Modifier
                .size(140.dp, 140.dp)
                .padding(start = 12.dp, end = 12.dp)
                .clip(RoundedCornerShape(12.dp))

        )
        Column(modifier = Modifier.padding(12.dp)) {
            if (product.salePrice != null) {
                Row {
                    Text(
                        text = product.salePrice.displayString,
                        fontSize = 21.sp,
                        color = RED,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "reg ${product.regularPrice.displayString}",
                        fontSize = 12.sp,
                        color = DarkGreyColor
                    )
                }
            }
            Text(
                text = product.regularPrice.displayString.orEmpty(), fontSize = 21.sp,
                color = RED,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = product.fulfillment,
                fontSize = 14.sp,
                color = GreyColor
            )
            Text(
                text = product.title,
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(text = availability, fontSize = 12.sp)
        }
    }
}