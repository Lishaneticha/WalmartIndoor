package com.lishan.walmartindoor

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.lishan.walmartindoor.ui.theme.WalmartIndoorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WalmartIndoorTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        ZoomableBox()
                    }
                }
            }
        }
    }
}

@Composable
fun StoreLayoutCanvas_sample() {
    val polygonPath = Path().apply {
        moveTo(1230.4f, 1282.2f)
        lineTo(1392.1f, 1282.2f)
        lineTo(1392.1f, 1260.4f)
        lineTo(1442.9f, 1260.4f)
        lineTo(1442.9f, 1283.3f)
        lineTo(1533.9f, 1283.3f)
        lineTo(1533.9f, 1280.3f)
        lineTo(1608.1f, 1280.3f)
        lineTo(1608.1f, 1252.6f)
        lineTo(1685.7f, 1252.6f)
        lineTo(1685.7f, 1463.9f)
        lineTo(1726.6f, 1463.9f)
        lineTo(1726.6f, 1549.9f)
        lineTo(1641f, 1549.9f)
        lineTo(1641f, 1568.1f)
        lineTo(1587.9f, 1568.1f)
        lineTo(1587.9f, 1577.8f)
        lineTo(1566.6f, 1577.8f)
        lineTo(1566.6f, 1567.9f)
        lineTo(1525.6f, 1567.9f)
        lineTo(1525.6f, 1550.4f)
        lineTo(1434.3f, 1550.4f)
        lineTo(1434.3f, 1567.4f)
        lineTo(1398.6f, 1567.4f)
        lineTo(1398.6f, 1577.8f)
        lineTo(1377.8f, 1577.8f)
        lineTo(1377.8f, 1566.9f)
        lineTo(1349.2f, 1566.9f)
        lineTo(1349.2f, 1519.1f)
        lineTo(1322.3f, 1519.1f)
        lineTo(1322.3f, 1566.8f)
        lineTo(1126.3f, 1566.8f)
        lineTo(1126.3f, 1396.9f)
        lineTo(1230.9f, 1396.9f)
        close() // important to close the shape
    }

    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(0.5f, 5f) // limit zoom range
                    offset += pan
                }
            }
    ) {
        Canvas(
            modifier = Modifier
                .size(400.dp) //2834.6
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                )
                .background(Color.Red)
        ) {
            drawPath(path = polygonPath, color = Color.Black, style = Stroke(width = 1f))

            val stroke = Stroke(width = 1f)
            drawRect(Color.Black, Offset(1130.3f, 1398.9f), Size(53.1f, 165.4f), style = stroke)
            drawRect(Color.Black, Offset(1187.3f, 1398.9f), Size(41.3f, 165.4f), style = stroke)
            drawRect(Color.Black, Offset(1233.3f, 1519.9f), Size(87.0f, 44.4f), style = stroke)
            drawRect(Color.Black, Offset(1231.6f, 1416.3f), Size(34.2f, 29.3f), style = stroke)
            drawRect(Color.Black, Offset(1233.1f, 1368.5f), Size(32.7f, 43.9f), style = stroke)
            drawRect(Color.Black, Offset(1141.4f, 1474.5f), Size(32.7f, 14.0f), style = stroke)
            drawRect(Color.Black, Offset(1233.1f, 1344.8f), Size(32.7f, 19.4f), style = stroke)
            drawRect(Color.Black, Offset(1231.6f, 1282.8f), Size(64.3f, 53.4f), style = stroke)
            drawRect(Color.Black, Offset(1300.9f, 1282.8f), Size(50.6f, 53.4f), style = stroke)
            drawRect(Color.Black, Offset(1355.2f, 1282.8f), Size(92.3f, 53.4f), style = stroke)
            drawRect(Color.Black, Offset(1452.7f, 1284.0f), Size(37.1f, 52.3f), style = stroke)
            drawRect(Color.Black, Offset(1494.1f, 1284.0f), Size(28.3f, 52.3f), style = stroke)
            drawRect(Color.Black, Offset(1525.9f, 1284.0f), Size(28.4f, 52.3f), style = stroke)
            drawRect(Color.Black, Offset(1565.2f, 1281.3f), Size(58.9f, 23.7f), style = stroke)
            drawRect(Color.Black, Offset(1628.7f, 1253.4f), Size(56.3f, 51.6f), style = stroke)
            drawRect(Color.Black, Offset(1586.7f, 1311.8f), Size(61.3f, 86.6f), style = stroke)
            drawRect(Color.Black, Offset(1658.3f, 1343.3f), Size(25.0f, 56.5f), style = stroke)
            drawRect(Color.Black, Offset(1650.7f, 1411.7f), Size(10.7f, 40.9f), style = stroke)
            drawRect(Color.Black, Offset(1586.7f, 1405.0f), Size(61.3f, 47.5f), style = stroke)
            drawRect(Color.Black, Offset(1586.7f, 1458.2f), Size(68.3f, 33.0f), style = stroke)
            drawRect(Color.Black, Offset(1586.7f, 1494.4f), Size(37.4f, 25.9f), style = stroke)
            drawRect(Color.Black, Offset(1627.5f, 1494.4f), Size(27.4f, 25.9f), style = stroke)
            drawRect(Color.Black, Offset(1685.9f, 1472.8f), Size(40.0f, 76.1f), style = stroke)
            drawRect(Color.Black, Offset(1567.6f, 1556.9f), Size(19.1f, 20.1f), style = stroke)
            drawRect(Color.Black, Offset(1378.9f, 1556.9f), Size(18.7f, 20.1f), style = stroke)
            drawRect(Color.Black, Offset(1327.1f, 1460.4f), Size(52.1f, 38.1f), style = stroke)
            drawRect(Color.Black, Offset(1384.6f, 1483.9f), Size(189.1f, 25.4f), style = stroke)
            drawRect(Color.Black, Offset(1456.8f, 1451.5f), Size(113.4f, 23.1f), style = stroke)
            drawRect(Color.Black, Offset(1513.5f, 1419.2f), Size(56.7f, 28.6f), style = stroke)
            drawRect(Color.Black, Offset(1456.8f, 1419.2f), Size(55.7f, 28.6f), style = stroke)
            drawRect(Color.Black, Offset(1476.7f, 1387.9f), Size(93.4f, 24.2f), style = stroke)
            drawRect(Color.Black, Offset(1438.3f, 1387.9f), Size(34.6f, 24.2f), style = stroke)
            drawRect(Color.Black, Offset(1438.3f, 1350.8f), Size(34.6f, 34.6f), style = stroke)
            drawRect(Color.Black, Offset(1476.7f, 1350.8f), Size(93.4f, 34.6f), style = stroke)
            drawRect(Color.Black, Offset(1438.3f, 1419.2f), Size(15.5f, 55.5f), style = stroke)
            drawRect(Color.Black, Offset(1393.5f, 1449.0f), Size(39.1f, 25.7f), style = stroke)
            drawRect(Color.Black, Offset(1393.5f, 1406.2f), Size(39.1f, 24.7f), style = stroke)
            drawRect(Color.Black, Offset(1393.5f, 1434.7f), Size(39.1f, 10.2f), style = stroke)
            drawRect(Color.Black, Offset(1335.1f, 1420.4f), Size(44.1f, 23.8f), style = stroke)
            drawRect(Color.Black, Offset(1353.1f, 1350.8f), Size(26.0f, 62.5f), style = stroke)
            drawRect(Color.Black, Offset(1334.3f, 1350.8f), Size(14.0f, 62.5f), style = stroke)
            drawRect(Color.Black, Offset(1321.7f, 1349.7f), Size(10.2f, 63.5f), style = stroke)
            drawRect(Color.Black, Offset(1304.0f, 1349.7f), Size(15.0f, 63.5f), style = stroke)
            drawRect(Color.Black, Offset(1280.3f, 1349.7f), Size(20.6f, 47.8f), style = stroke)
            drawRect(Color.Black, Offset(1280.3f, 1400.6f), Size(20.6f, 12.6f), style = stroke)
            drawRect(Color.Black, Offset(1280.3f, 1420.4f), Size(48.0f, 23.8f), style = stroke)
            drawRect(Color.Black, Offset(1393.5f, 1349.7f), Size(39.1f, 21.6f), style = stroke)
            drawRect(Color.Black, Offset(1393.5f, 1376.1f), Size(39.1f, 25.2f), style = stroke)
            drawRect(Color.Black, Offset(1417.3f, 1262.6f), Size(20.9f, 18.7f), style = stroke)
            drawRect(Color.Black, Offset(1446.0f, 1512.0f), Size(19.3f, 19.3f), style = stroke)
            drawRect(Color.Black, Offset(1341.2f, 1499.8f), Size(19.0f, 17.4f), style = stroke)
            drawRect(Color.Black, Offset(1357.1f, 1519.2f), Size(19.1f, 19.1f), style = stroke)
            drawRect(Color.Black, Offset(1403.1f, 1526.1f), Size(18.6f, 18.6f), style = stroke)
            drawRect(Color.Black, Offset(1410.8f, 1513.8f), Size(18.6f, 20.4f), style = stroke)

            // 2. Draw text labels
            val paint = Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                textSize = 10f
                color = android.graphics.Color.BLACK
            }
            drawContext.canvas.nativeCanvas.apply {
                // --- Horizontal text ---
                drawText("Garden", 1143.91f, 1484.76f, paint)
                drawText("Garden", 1193.69f, 1484.35f, paint)
                drawText("Garden", 1262.65f, 1544.82f, paint)
                drawText("Paint", 1238.4f, 1434.5f, paint)
                drawText("Auto", 1239.34f, 1357.20f, paint)
                drawText("Office\nSupplies", 1401.43f, 1359.97f, paint)
                drawText("Arts &\nCrafts", 1401.38f, 1387.85f, paint)
                drawText("Girls", 1447.68f, 1370.89f, paint)
                drawText("Boys", 1446.89f, 1403.61f, paint)
                drawText("Baby", 1514.20f, 1370.59f, paint)
                drawText("Mens", 1512.76f, 1403.40f, paint)
                drawText("Party\nSupplies", 1402.42f, 1418.01f, paint)
                drawText("Clearance", 1394.20f, 1442.99f, paint)
                drawText("Seasonal", 1394.97f, 1464.56f, paint)
                drawText("Health", 1340.25f, 1482.69f, paint)
                drawText("Checkout", 1459.80f, 1499.51f, paint)
                drawText("Shoes", 1472.73f, 1436.52f, paint)
                drawText("Intimates", 1524.05f, 1435.78f, paint)
                drawText("Womens", 1495.05f, 1466.25f, paint)
                drawText("Furnture", 1274.62f, 1410.57f, paint)
                drawText("Sports", 1250.94f, 1312.84f, paint)
                drawText("Toys", 1317.14f, 1313.33f, paint)
                drawText("Electronics", 1378.89f, 1313.44f, paint)
                drawText("Pets", 1462.74f, 1313.16f, paint)
                drawText("Dairy", 1585.19f, 1295.89f, paint)
                drawText("Grocery", 1602.33f, 1357.65f, paint)
                drawText("Frozen", 1604.95f, 1431.76f, paint)
                drawText("Fresh Produce", 1594.12f, 1477.35f, paint)
                drawText("Bakery", 1592.37f, 1510.08f, paint)
                drawText("Deli", 1633.31f, 1510.33f, paint)
                drawText("Alcohol", 1691.58f, 1513.95f, paint)
                drawText("Personal\nCare", 1288.39f, 1432.29f, paint)
                drawText("Beauty", 1344.20f, 1435.69f, paint)

                // Rotated Labels
                save()
                rotate(90f, 1245.29f, 1371.92f)
                drawText("Hardware", 1245.29f, 1371.92f, paint)
                restore()

                save()
                rotate(90f, 1288.27f, 1355.69f)
                drawText("Laundary", 1288.27f, 1355.69f, paint)
                restore()

                save()
                rotate(90f, 1312.03f, 1369.09f)
                drawText("Bath &\nShower", 1312.03f, 1369.09f, paint)
                restore()

                save()
                rotate(90f, 1324.70f, 1364.30f)
                drawText("Bedding", 1324.70f, 1364.30f, paint)
                restore()

                save()
                rotate(90f, 1338.42f, 1369.21f)
                drawText("Home", 1338.42f, 1369.21f, paint)
                restore()

                save()
                rotate(90f, 1363.50f, 1365.76f)
                drawText("Kitchen", 1363.50f, 1365.76f, paint)
                restore()

                save()
                rotate(90f, 1442.80f, 1436.27f)
                drawText("Jewelry", 1442.80f, 1436.27f, paint)
                restore()

                save()
                rotate(90f, 1505.68f, 1291.32f)
                drawText("Cleaning", 1505.68f, 1291.32f, paint)
                restore()

                save()
                rotate(90f, 1541.08f, 1289.78f)
                drawText("Household\nPaper", 1541.08f, 1289.78f, paint)
                restore()

                save()
                rotate(90f, 1672.73f, 1357.54f)
                drawText("Meat &\nSeafood", 1672.73f, 1357.54f, paint)
                restore()

                save()
                rotate(90f, 1653.21f, 1424.09f)
                drawText("Deli", 1653.21f, 1424.09f, paint)
                restore()
            }
        }
    }
}

@Composable
fun StoreLayoutCanvas() {
    val polygonPath = Path().apply {
        moveTo(1230.4f, 1282.2f)
        lineTo(1392.1f, 1282.2f)
        lineTo(1392.1f, 1260.4f)
        lineTo(1442.9f, 1260.4f)
        lineTo(1442.9f, 1283.3f)
        lineTo(1533.9f, 1283.3f)
        lineTo(1533.9f, 1280.3f)
        lineTo(1608.1f, 1280.3f)
        lineTo(1608.1f, 1252.6f)
        lineTo(1685.7f, 1252.6f)
        lineTo(1685.7f, 1463.9f)
        lineTo(1726.6f, 1463.9f)
        lineTo(1726.6f, 1549.9f)
        lineTo(1641f, 1549.9f)
        lineTo(1641f, 1568.1f)
        lineTo(1587.9f, 1568.1f)
        lineTo(1587.9f, 1577.8f)
        lineTo(1566.6f, 1577.8f)
        lineTo(1566.6f, 1567.9f)
        lineTo(1525.6f, 1567.9f)
        lineTo(1525.6f, 1550.4f)
        lineTo(1434.3f, 1550.4f)
        lineTo(1434.3f, 1567.4f)
        lineTo(1398.6f, 1567.4f)
        lineTo(1398.6f, 1577.8f)
        lineTo(1377.8f, 1577.8f)
        lineTo(1377.8f, 1566.9f)
        lineTo(1349.2f, 1566.9f)
        lineTo(1349.2f, 1519.1f)
        lineTo(1322.3f, 1519.1f)
        lineTo(1322.3f, 1566.8f)
        lineTo(1126.3f, 1566.8f)
        lineTo(1126.3f, 1396.9f)
        lineTo(1230.9f, 1396.9f)
        close()
    }

    // Initial layout center based on polygon bounds (approximation)
    val layoutCenter = Offset((1126f + 1727f) / 2f, (1252f + 1578f) / 2f)

    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var hasCentered by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(0.5f, 5f)
                    offset += pan
                }
            }
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                )
        ) {
            val canvasCenter = Offset(size.width / 2f, size.height / 2f)
            if (!hasCentered) {
                offset = canvasCenter - layoutCenter
                hasCentered = true
            }

            val stroke = Stroke(width = 1f)
            drawPath(polygonPath, color = Color.Black, style = stroke)

            // Use your original drawRect and drawText code here without modification
            // For brevity, it is assumed unchanged

            val paint = Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                textSize = 10f
                color = android.graphics.Color.BLACK
            }

            drawContext.canvas.nativeCanvas.apply {
                // Example text draw
                drawText("Garden", 1143.91f, 1484.76f, paint)
                drawText("Paint", 1238.4f, 1434.5f, paint)

                // Rotated
                save()
                rotate(90f, 1245.29f, 1371.92f)
                drawText("Hardware", 1245.29f, 1371.92f, paint)
                restore()

                // Add all your other drawText and drawRect as-is here...
            }
        }
    }
}

@Composable
fun ZoomableBox() {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        val screenWidth = constraints.maxWidth.toFloat()
        val screenHeight = constraints.maxHeight.toFloat()

        // Define a larger virtual content size
        val contentWidth = screenWidth * 2
        val contentHeight = screenHeight * 2

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
            Canvas(
                modifier = Modifier
                    .size(contentWidth.dp, contentHeight.dp)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    )
            ) {
                drawRect(Color.LightGray)

                val originalMinX = 1126.3f
                val originalMaxX = 1726.6f
                val originalMinY = 1252.6f
                val originalMaxY = 1577.8f

                val originalWidth = originalMaxX - originalMinX
                val originalHeight = originalMaxY - originalMinY

                val targetWidth = size.width * 0.8f
                val targetHeight = size.height * 0.8f

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
                    style = Stroke(width = 2f)
                )

                val rectWidth = originalMaxX - originalMinX
                val rectHeight = originalMaxY - originalMinY

                val scaleXRect = (size.width * 0.8f) / rectWidth
                val scaleYRect = (size.height * 0.8f) / rectHeight
                val finalScaleRect = minOf(scaleXRect, scaleYRect)

                val offsetXRect = (size.width - rectWidth * finalScaleRect) / 2
                val offsetYRect = (size.height - rectHeight * finalScaleRect) / 2

                fun transformRectOffset(offset: Offset): Offset = Offset(
                    x = offsetXRect + (offset.x - originalMinX) * finalScaleRect,
                    y = offsetYRect + (offset.y - originalMinY) * finalScaleRect
                )

                fun transformRectSize(size: Size): Size = Size(
                    width = size.width * finalScaleRect,
                    height = size.height * finalScaleRect
                )

                val rects = listOf(
                    Offset(1130.3f, 1398.9f) to Size(53.1f, 165.4f),
                    Offset(1187.3f, 1398.9f) to Size(41.3f, 165.4f),
                    Offset(1233.3f, 1519.9f) to Size(87.0f, 44.4f),
                    Offset(1231.6f, 1416.3f) to Size(34.2f, 29.3f),
                    Offset(1233.1f, 1368.5f) to Size(32.7f, 43.9f),
                    Offset(1233.1f, 1344.8f) to Size(32.7f, 19.4f),
                    Offset(1231.6f, 1282.8f) to Size(64.3f, 53.4f),
                    Offset(1300.9f, 1282.8f) to Size(50.6f, 53.4f),
                    Offset(1355.2f, 1282.8f) to Size(92.3f, 53.4f),
                    Offset(1452.7f, 1284.0f) to Size(37.1f, 52.3f),
                    Offset(1494.1f, 1284.0f) to Size(28.3f, 52.3f),
                    Offset(1525.9f, 1284.0f) to Size(28.4f, 52.3f),
                    Offset(1565.2f, 1281.3f) to Size(58.9f, 23.7f),
                    Offset(1628.7f, 1253.4f) to Size(56.3f, 51.6f),
                    Offset(1586.7f, 1311.8f) to Size(61.3f, 86.6f),
                    Offset(1658.3f, 1343.3f) to Size(25.0f, 56.5f),
                    Offset(1650.7f, 1411.7f) to Size(10.7f, 40.9f),
                    Offset(1586.7f, 1405.0f) to Size(61.3f, 47.5f),
                    Offset(1586.7f, 1458.2f) to Size(68.3f, 33.0f),
                    Offset(1586.7f, 1494.4f) to Size(37.4f, 25.9f),
                    Offset(1627.5f, 1494.4f) to Size(27.4f, 25.9f),
                    Offset(1685.9f, 1472.8f) to Size(40.0f, 76.1f),
                    Offset(1567.6f, 1556.9f) to Size(19.1f, 20.1f),
                    Offset(1378.9f, 1556.9f) to Size(18.7f, 20.1f),
                    Offset(1327.1f, 1460.4f) to Size(52.1f, 38.1f),
                    Offset(1384.6f, 1483.9f) to Size(189.1f, 25.4f),
                    Offset(1456.8f, 1451.5f) to Size(113.4f, 23.1f),
                    Offset(1513.5f, 1419.2f) to Size(56.7f, 28.6f),
                    Offset(1456.8f, 1419.2f) to Size(55.7f, 28.6f),
                    Offset(1476.7f, 1387.9f) to Size(93.4f, 24.2f),
                    Offset(1438.3f, 1387.9f) to Size(34.6f, 24.2f),
                    Offset(1438.3f, 1350.8f) to Size(34.6f, 34.6f),
                    Offset(1476.7f, 1350.8f) to Size(93.4f, 34.6f),
                    Offset(1438.3f, 1419.2f) to Size(15.5f, 55.5f),
                    Offset(1393.5f, 1449.0f) to Size(39.1f, 25.7f),
                    Offset(1393.5f, 1406.2f) to Size(39.1f, 24.7f),
                    Offset(1393.5f, 1434.7f) to Size(39.1f, 10.2f),
                    Offset(1335.1f, 1420.4f) to Size(44.1f, 23.8f),
                    Offset(1353.1f, 1350.8f) to Size(26.0f, 62.5f),
                    Offset(1334.3f, 1350.8f) to Size(14.0f, 62.5f),
                    Offset(1321.7f, 1349.7f) to Size(10.2f, 63.5f),
                    Offset(1304.0f, 1349.7f) to Size(15.0f, 63.5f),
                    Offset(1280.3f, 1349.7f) to Size(20.6f, 47.8f),
                    Offset(1280.3f, 1400.6f) to Size(20.6f, 12.6f),
                    Offset(1280.3f, 1420.4f) to Size(48.0f, 23.8f),
                    Offset(1393.5f, 1349.7f) to Size(39.1f, 21.6f),
                    Offset(1393.5f, 1376.1f) to Size(39.1f, 25.2f),
                    Offset(1417.3f, 1262.6f) to Size(20.9f, 18.7f),
                    Offset(1446.0f, 1512.0f) to Size(19.3f, 19.3f),
                    Offset(1341.2f, 1499.8f) to Size(19.0f, 17.4f),
                    Offset(1357.1f, 1519.2f) to Size(19.1f, 19.1f),
                    Offset(1403.1f, 1526.1f) to Size(18.6f, 18.6f),
                    Offset(1410.8f, 1513.8f) to Size(18.6f, 20.4f)
                )

                val stroke = Stroke(width = 1f)

                rects.forEach { (offset, size) ->
                    drawRect(
                        color = Color.Black,
                        topLeft = transformRectOffset(offset),
                        size = transformRectSize(size),
                        style = stroke
                    )
                }

                // 2. Draw transformed text labels
                val paint = Paint().asFrameworkPaint().apply {
                    isAntiAlias = true
                    textSize = 8f // Adjust for visibility
                    color = android.graphics.Color.BLACK
                }

                fun transformPoint(x: Float, y: Float): Pair<Float, Float> {
                    val tx = offsetXRect + (x - originalMinX) * finalScaleRect
                    val ty = offsetYRect + (y - originalMinY) * finalScaleRect
                    return tx to ty
                }

                val labelHeight = 8f

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