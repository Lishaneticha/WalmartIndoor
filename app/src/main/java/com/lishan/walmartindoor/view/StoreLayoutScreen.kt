package com.lishan.walmartindoor.view

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lishan.walmartindoor.viewmodel.StoreViewModel
import kotlinx.coroutines.launch

@Composable
fun StoreLayoutScreen(
    onLongPress: (index: Int) -> Unit
) {
    var viewModel: StoreViewModel = hiltViewModel()
    var scale by remember { mutableStateOf(2f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    var showDialog by remember { mutableStateOf(false) }
    var sectionName by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val saveSuccess by viewModel.saveSuccess.collectAsState()
    val sectionsWithShelves by viewModel.sectionsWithShelves.collectAsState()
    val existingShelfLabels = remember { mutableStateListOf("") }
    val scope = rememberCoroutineScope()

    // Observe save success and show snackbar
    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            scope.launch {
                snackbarHostState.showSnackbar("Saved successfully ✅")
            }
            viewModel.clearSuccessFlag()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(padding)
        ) {
            val screenWidth = constraints.maxWidth.toFloat()
            val screenHeight = constraints.maxHeight.toFloat()

            val contentWidth = screenWidth * 2
            val contentHeight = screenHeight * 2

            val rects = listOf(
                Offset(1130.3f, 1398.9f) to Size(53.1f, 165.4f) to "Garden",
                Offset(1187.3f, 1398.9f) to Size(41.3f, 165.4f) to "Garden",
                Offset(1233.3f, 1519.9f) to Size(87.0f, 44.4f) to "Garden",
                Offset(1231.6f, 1416.3f) to Size(34.2f, 29.3f) to "Paint",
                Offset(1233.1f, 1368.5f) to Size(32.7f, 43.9f) to "Hardware",
                Offset(1233.1f, 1344.8f) to Size(32.7f, 19.4f) to "Auto",
                Offset(1231.6f, 1282.8f) to Size(64.3f, 53.4f) to "Sports",
                Offset(1300.9f, 1282.8f) to Size(50.6f, 53.4f) to "Toys",
                Offset(1355.2f, 1282.8f) to Size(92.3f, 53.4f) to "Electronics",
                Offset(1452.7f, 1284.0f) to Size(37.1f, 52.3f) to "Pets",
                Offset(1494.1f, 1284.0f) to Size(28.3f, 52.3f) to "Cleaning",
                Offset(1525.9f, 1284.0f) to Size(28.4f, 52.3f) to "Household Paper",
                Offset(1565.2f, 1281.3f) to Size(58.9f, 23.7f) to "Dairy",
                Offset(1628.7f, 1253.4f) to Size(56.3f, 51.6f) to "Snacks & Bev",
                Offset(1586.7f, 1311.8f) to Size(61.3f, 86.6f) to "Grocery",
                Offset(1658.3f, 1343.3f) to Size(25.0f, 56.5f) to "Meat & Seafood",
                Offset(1650.7f, 1411.7f) to Size(10.7f, 40.9f) to "Deli",
                Offset(1586.7f, 1405.0f) to Size(61.3f, 47.5f) to "Frozen",
                Offset(1586.7f, 1458.2f) to Size(68.3f, 33.0f) to "Fresh Produce",
                Offset(1586.7f, 1494.4f) to Size(37.4f, 25.9f) to "Bakery",
                Offset(1627.5f, 1494.4f) to Size(27.4f, 25.9f) to "Deli",
                Offset(1685.9f, 1472.8f) to Size(40.0f, 76.1f) to "Alcohol",
                Offset(1567.6f, 1556.9f) to Size(19.1f, 20.1f) to "",
                Offset(1378.9f, 1556.9f) to Size(18.7f, 20.1f) to "",
                Offset(1327.1f, 1460.4f) to Size(52.1f, 38.1f) to "Health",
                Offset(1384.6f, 1483.9f) to Size(189.1f, 25.4f) to "Checkout",
                Offset(1456.8f, 1451.5f) to Size(113.4f, 23.1f) to "Women",
                Offset(1513.5f, 1419.2f) to Size(56.7f, 28.6f) to "Intimates",
                Offset(1456.8f, 1419.2f) to Size(55.7f, 28.6f) to "Shoes",
                Offset(1476.7f, 1387.9f) to Size(93.4f, 24.2f) to "Men",
                Offset(1438.3f, 1387.9f) to Size(34.6f, 24.2f) to "Boys",
                Offset(1438.3f, 1350.8f) to Size(34.6f, 34.6f) to "Girls",
                Offset(1476.7f, 1350.8f) to Size(93.4f, 34.6f) to "Baby",
                Offset(1438.3f, 1419.2f) to Size(15.5f, 55.5f) to "Jewelery",
                Offset(1393.5f, 1449.0f) to Size(39.1f, 25.7f) to "Seasonal",
                Offset(1393.5f, 1406.2f) to Size(39.1f, 24.7f) to "Party Supplies",
                Offset(1393.5f, 1434.7f) to Size(39.1f, 10.2f) to "Clearance",
                Offset(1335.1f, 1420.4f) to Size(44.1f, 23.8f) to "Beauty",
                Offset(1353.1f, 1350.8f) to Size(26.0f, 62.5f) to "Kitchen",
                Offset(1334.3f, 1350.8f) to Size(14.0f, 62.5f) to "Home",
                Offset(1321.7f, 1349.7f) to Size(10.2f, 63.5f) to "Bedding",
                Offset(1304.0f, 1349.7f) to Size(15.0f, 63.5f) to "Bath & Shower",
                Offset(1280.3f, 1349.7f) to Size(20.6f, 47.8f) to "Laundry",
                Offset(1280.3f, 1400.6f) to Size(20.6f, 12.6f) to "Furniture",
                Offset(1280.3f, 1420.4f) to Size(48.0f, 23.8f) to "Personal Care",
                Offset(1393.5f, 1349.7f) to Size(39.1f, 21.6f) to "Office Supplies",
                Offset(1393.5f, 1376.1f) to Size(39.1f, 25.2f) to "Arts & Crafts",
                Offset(1417.3f, 1262.6f) to Size(20.9f, 18.7f) to "",
                Offset(1446.0f, 1512.0f) to Size(19.3f, 19.3f) to "",
                Offset(1341.2f, 1499.8f) to Size(19.0f, 17.4f) to "",
                Offset(1357.1f, 1519.2f) to Size(19.1f, 19.1f) to "",
                Offset(1403.1f, 1526.1f) to Size(18.6f, 18.6f) to "",
                Offset(1410.8f, 1513.8f) to Size(18.6f, 20.4f) to "",
            )

            val originalMinX = 1126.3f
            val originalMaxX = 1726.6f
            val originalMinY = 1252.6f
            val originalMaxY = 1577.8f

            val originalWidth = originalMaxX - originalMinX
            val originalHeight = originalMaxY - originalMinY

            val finalScaleRect = minOf(
                (screenWidth * 2.5f) / originalWidth,
                (screenHeight * 2.5f) / originalHeight
            )

            val offsetXRect = (screenWidth - originalWidth * finalScaleRect) / 2
            val offsetYRect = (screenHeight - originalHeight * finalScaleRect) / 2

            fun inverseTransformTap(tap: Offset): Offset {
                val canvasSpace = tap - offset
                return Offset(
                    x = ((canvasSpace.x - offsetXRect) / finalScaleRect) + originalMinX,
                    y = ((canvasSpace.y - offsetYRect) / finalScaleRect) + originalMinY
                )
            }

            val maxOffsetX = ((contentWidth * scale) - screenWidth) / 2
            val maxOffsetY = ((contentHeight * scale) - screenHeight) / 2

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale = (scale * zoom).coerceIn(0.5f, 3f)

                            val newOffset = offset + pan
                            offset = Offset(
                                x = newOffset.x.coerceIn(-maxOffsetX, maxOffsetX),
                                y = newOffset.y.coerceIn(-maxOffsetY, maxOffsetY)
                            )
                        }
                    }
            ) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = { rawTapOffset ->
                                    val tap = inverseTransformTap(rawTapOffset)
                                    rects.forEachIndexed { index, (rectPair, label) ->
                                        val (topLeft, size) = rectPair
                                        val rect = Rect(topLeft, size)
                                        if (rect.contains(tap)) {
                                            Log.d("Canvas", "Long-pressed rect #$index at $tap")
                                            selectedIndex = index
                                            showDialog = true
                                            sectionName = label
                                            onLongPress(index)
                                            return@detectTapGestures
                                        }
                                    }
                                }
                            )
                        }
                ) {

                    SectionShelfDialog(
                        showDialog = showDialog,
                        onDismiss = { showDialog = false },
                        sectionName = sectionName,
                        existingShelfLabels = existingShelfLabels,
                        onSave = { sectionName, shelfLabels ->
                            viewModel.saveSectionWithShelves(sectionName, shelfLabels, Offset(0f, 0f))
                        }
                    )

                    Canvas(
                        modifier = Modifier
                            .size(contentWidth.dp, contentHeight.dp)
                            .graphicsLayer(
                                translationX = offset.x,
                                translationY = offset.y
                            )
                    ) {

                        val stroke = Stroke(width = 1f)

                        fun transformRectOffset(offset: Offset): Offset = Offset(
                            x = offsetXRect + (offset.x - originalMinX) * finalScaleRect,
                            y = offsetYRect + (offset.y - originalMinY) * finalScaleRect
                        )

                        fun transformRectSize(size: Size): Size = Size(
                            width = size.width * finalScaleRect,
                            height = size.height * finalScaleRect
                        )

                        rects.forEachIndexed { index, (rectPair, label) ->
                            val (rectOffset, rectSize) = rectPair

                            val fillColor = if (index == selectedIndex) {
                                println("rect offset: ${rectOffset.x}f, ${rectOffset.y}f")
                                existingShelfLabels.clear()
                                val existingShelves = sectionsWithShelves.firstOrNull { section -> section.section.name == label }?.shelves?.map { it.label } ?: listOf("")
                                existingShelfLabels.addAll(existingShelves)
                                Color(0xFFB0BEC5)
                            } else if (sectionsWithShelves.any { section -> section.section.name == label }) {
                                Color(0xFFFFF176)
                            } else Color.Transparent

                            // Fill first (only if selected)
                            if (fillColor != Color.Transparent) {
                                drawRect(
                                    color = fillColor,
                                    topLeft = transformRectOffset(rectOffset),
                                    size = transformRectSize(rectSize),
                                    style = Fill
                                )
                            }

                            // Draw outline always
                            drawRect(
                                color = Color.Black,
                                topLeft = transformRectOffset(rectOffset),
                                size = transformRectSize(rectSize),
                                style = stroke
                            )
                        }

                        val targetWidth = size.width * 2.5f
                        val targetHeight = size.height * 2.5f

                        val scaleX = targetWidth / originalWidth
                        val scaleY = targetHeight / originalHeight
                        val finalScale = minOf(scaleX, scaleY)

                        val offsetX = (size.width - originalWidth * finalScale) / 2
                        val offsetY = (size.height - originalHeight * finalScale) / 2

                        fun transform(x: Float, y: Float): Offset {
                            return Offset(
                                x = offsetX + (x - originalMinX) * finalScale,
                                y = offsetY + (y - originalMinY) * finalScale
                            )
                        }

                        val polygonPath = Path().apply {
                            val p = transform(1230.4f, 1282.2f)
                            moveTo(p.x, p.y)

                            listOf(
                                transform(1392.1f, 1282.2f),
                                transform(1392.1f, 1260.4f),
                                transform(1442.9f, 1260.4f),
                                transform(1442.9f, 1283.3f),
                                transform(1533.9f, 1283.3f),
                                transform(1533.9f, 1280.3f),
                                transform(1608.1f, 1280.3f),
                                transform(1608.1f, 1252.6f),
                                transform(1685.7f, 1252.6f),
                                transform(1685.7f, 1463.9f),
                                transform(1726.6f, 1463.9f),
                                transform(1726.6f, 1549.9f),
                                transform(1641f, 1549.9f),
                                transform(1641f, 1568.1f),
                                transform(1587.9f, 1568.1f),
                                transform(1587.9f, 1577.8f),
                                transform(1566.6f, 1577.8f),
                                transform(1566.6f, 1567.9f),
                                transform(1525.6f, 1567.9f),
                                transform(1525.6f, 1550.4f),
                                transform(1434.3f, 1550.4f),
                                transform(1434.3f, 1567.4f),
                                transform(1398.6f, 1567.4f),
                                transform(1398.6f, 1577.8f),
                                transform(1377.8f, 1577.8f),
                                transform(1377.8f, 1566.9f),
                                transform(1349.2f, 1566.9f),
                                transform(1349.2f, 1519.1f),
                                transform(1322.3f, 1519.1f),
                                transform(1322.3f, 1566.8f),
                                transform(1126.3f, 1566.8f),
                                transform(1126.3f, 1396.9f),
                                transform(1230.9f, 1396.9f)
                            ).forEach { point ->
                                lineTo(point.x, point.y)
                            }

                            close()
                        }

                        drawPath(
                            path = polygonPath,
                            color = Color.Black,
                            style = Stroke(width = 1f)
                        )

                        val paint = Paint().asFrameworkPaint().apply {
                            isAntiAlias = true
                            textSize = 30f // Adjust for visibility
                            color = android.graphics.Color.BLACK
                        }

                        fun transformPoint(x: Float, y: Float): Pair<Float, Float> {
                            val tx = offsetX + (x - originalMinX) * finalScale
                            val ty = offsetY + (y - originalMinY) * finalScale
                            return tx to ty
                        }

                        val labelHeight = 20f

                        drawContext.canvas.nativeCanvas.apply {
                            // Horizontal labels
                            listOf(
                                "Garden" to (1143.91f to 1484.76f),
                                "Garden" to (1193.69f to 1484.35f),
                                "Garden" to (1262.65f to 1544.82f),
                                "Paint" to (1238.4f to 1434.5f),
                                "Auto" to (1239.34f to 1357.20f),
                                "Office\nSupplies" to (1401.43f to 1359.97f),
                                "Arts &\nCrafts" to (1401.38f to 1387.85f),
                                "Girls" to (1447.68f to 1370.89f),
                                "Boys" to (1446.89f to 1403.61f),
                                "Baby" to (1514.20f to 1370.59f),
                                "Mens" to (1512.76f to 1403.40f),
                                "Party\nSupplies" to (1402.42f to 1418.01f),
                                "Clearance" to (1394.20f to 1442.99f),
                                "Seasonal" to (1394.97f to 1464.56f),
                                "Health" to (1340.25f to 1482.69f),
                                "Checkout" to (1459.80f to 1499.51f),
                                "Shoes" to (1472.73f to 1436.52f),
                                "Intimates" to (1524.05f to 1435.78f),
                                "Womens" to (1495.05f to 1466.25f),
                                "Furnture" to (1274.62f to 1410.57f),
                                "Sports" to (1250.94f to 1312.84f),
                                "Toys" to (1317.14f to 1313.33f),
                                "Electronics" to (1378.89f to 1313.44f),
                                "Pets" to (1462.74f to 1313.16f),
                                "Dairy" to (1585.19f to 1295.89f),
                                "Snacks &\nBev" to (1640.19f to 1285.89f),
                                "Grocery" to (1602.33f to 1357.65f),
                                "Frozen" to (1604.95f to 1431.76f),
                                "Fresh Produce" to (1594.12f to 1477.35f),
                                "Bakery" to (1592.37f to 1510.08f),
                                "Deli" to (1633.31f to 1510.33f),
                                "Alcohol" to (1691.58f to 1513.95f),
                                "Personal\nCare" to (1288.39f to 1432.29f),
                                "Beauty" to (1344.20f to 1435.69f)
                            ).forEach { (label, coords) ->
                                val (tx, ty) = transformPoint(coords.first, coords.second)
                                label.split("\n").forEachIndexed { i, line ->
                                    drawText(line, tx, ty + i * labelHeight, paint)
                                }
                            }

                            // Rotated labels
                            listOf(
                                "Hardware" to (1245.29f to 1371.92f),
                                "Laundary" to (1288.27f to 1355.69f),
                                "Bath &\nShower" to (1312.03f to 1369.09f),
                                "Bedding" to (1324.70f to 1364.30f),
                                "Home" to (1338.42f to 1369.21f),
                                "Kitchen" to (1363.50f to 1365.76f),
                                "Jewelry" to (1442.80f to 1436.27f),
                                "Cleaning" to (1505.68f to 1291.32f),
                                "Household\nPaper" to (1541.08f to 1289.78f),
                                "Meat &\nSeafood" to (1672.73f to 1357.54f),
                                "Deli" to (1653.21f to 1424.09f)
                            ).forEach { (label, coords) ->
                                val (tx, ty) = transformPoint(coords.first, coords.second)
                                save()
                                rotate(90f, tx, ty)
                                label.split("\n").forEachIndexed { i, line ->
                                    drawText(line, tx, ty + i * labelHeight, paint)
                                }
                                restore()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SectionShelfDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    sectionName: String,
    existingShelfLabels: SnapshotStateList<String>,
    onSave: (sectionName: String, shelfLabels: List<String>) -> Unit
) {
    if (!showDialog) return

    val shelfLabels = remember { mutableStateListOf("") }
    shelfLabels.clear()
    shelfLabels.addAll(existingShelfLabels)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Enter Section and Shelves") },
        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                OutlinedTextField(
                    value = sectionName,
                    onValueChange = {}, //{ sectionName = it }
                    label = { Text("Section Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    readOnly = true
                )

                Text("Shelves", style = MaterialTheme.typography.titleMedium)

                shelfLabels.forEachIndexed { index, label ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        OutlinedTextField(
                            value = label,
                            onValueChange = { shelfLabels[index] = it },
                            label = { Text("Shelf ${index + 1}") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = {
                                if (shelfLabels.size > 1) {
                                    shelfLabels.removeAt(index)
                                }
                            }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Remove Shelf")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { shelfLabels.add("") },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Add Shelf")
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val cleaned = shelfLabels.map { it.trim() }.filter { it.isNotBlank() }.minus(existingShelfLabels)
                    if (cleaned.isNotEmpty()){
                        onSave(sectionName.trim(), cleaned)
                    }
                    onDismiss()
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}