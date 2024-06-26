/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.plantdetail

import android.content.res.Configuration
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.theme.SunflowerTheme

@Composable
fun PlantDetailDescription(plantState: State<Plant?>) {
    Surface {
        plantState.value?.let { plant ->
            PlantDetailContent(plant)
        }
    }
}

@Composable
fun PlantDetailContent(plant: Plant) {
    val centeringModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = dimensionResource(id = R.dimen.margin_small))
        .wrapContentWidth(Alignment.CenterHorizontally)

    Column(Modifier.padding(dimensionResource(R.dimen.margin_normal))) {
        PlantName(name = plant.name, modifier = centeringModifier)
        WateringHeader(modifier = centeringModifier)
        PlantWatering(
            wateringInterval = plant.wateringInterval,
            modifier = centeringModifier
        )
        PlantDescription(description = plant.description, modifier = centeringModifier)
    }
}

@Composable
private fun PlantName(name: String, modifier: Modifier) {
    Text(
        text = name,
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.primary,
        modifier = modifier
    )
}

@Composable
private fun WateringHeader(modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.watering_needs_prefix),
        color = MaterialTheme.colors.primaryVariant,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(top = dimensionResource(id = R.dimen.margin_normal))
    )
}

@Composable
private fun PlantWatering(wateringInterval: Int, modifier: Modifier) {
    val wateringIntervalText = pluralStringResource(
        R.plurals.watering_needs_suffix, wateringInterval, wateringInterval
    )
    Text(
        text = wateringIntervalText,
        color = MaterialTheme.colors.onSurface,
        modifier = modifier
    )
}

@Composable
private fun PlantDescription(description: String, modifier: Modifier) {
    val htmlDescription = remember(description) {
        HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }
    AndroidView(
        factory = { context ->
            TextView(context).apply {
                movementMethod = LinkMovementMethod.getInstance()
            }
        },
        update = {
            it.text = htmlDescription
        },
        modifier = modifier.padding(top = dimensionResource(id = R.dimen.margin_small))
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PlantDetailContentPreview() {
    val plant = Plant(
        plantId = "id",
        name = "Apple",
        description = "HTML<br><br>description",
        growZoneNumber = 3,
        wateringInterval = 30,
        imageUrl = ""
    )
    SunflowerTheme {
        PlantDetailContent(plant)
    }
}