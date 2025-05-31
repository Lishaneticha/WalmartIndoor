package com.lishan.walmartindoor.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import com.lishan.walmartindoor.view.theme.WalmartIndoorTheme
import com.lishan.walmartindoor.viewmodel.StoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.sqrt

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WalmartIndoorTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        StoreLayoutScreen(){ index ->
                            Log.d("LongPress", "Long-pressed on rect #$index")
                        }
//                        MapStyledStoreCanvas(){}
                    }
                }
            }
        }
    }
}

@Composable
fun MapStyledStoreCanvas(
    onShelfClick: (Int) -> Unit
) {
    val shelfRects = listOf(
        Offset(100f, 100f) to Size(120f, 40f),
        Offset(250f, 100f) to Size(120f, 40f),
        Offset(100f, 180f) to Size(270f, 40f)
    )

    val selectedIndex = remember { mutableStateOf(-1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // light gray map background
            .pointerInput(Unit) {
                detectTapGestures { tap ->
                    shelfRects.forEachIndexed { index, (offset, size) ->
                        val rect = Rect(offset, size)
                        if (rect.contains(tap)) {
                            selectedIndex.value = index
                            onShelfClick(index)
                            return@detectTapGestures
                        }
                    }
                }
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            shelfRects.forEachIndexed { index, (offset, size) ->
                // Fill color
                drawRect(
                    color = if (index == selectedIndex.value)
                        Color(0xFFFFF176) // yellow highlight
                    else
                        Color(0xFFB0BEC5), // gray-blue shelf
                    topLeft = offset,
                    size = size
                )

                // Border
                drawRect(
                    color = Color.Black,
                    topLeft = offset,
                    size = size,
                    style = Stroke(width = 1f)
                )

                // Text label (centered)
                drawContext.canvas.nativeCanvas.drawText(
                    "Shelf ${index + 1}",
                    offset.x + size.width / 4,
                    offset.y + size.height / 1.5f,
                    Paint().asFrameworkPaint().apply {
                        isAntiAlias = true
                        color = android.graphics.Color.BLACK
                        textSize = 24f
                    }
                )
            }
        }
    }
}

@Composable
fun StoreLayoutCanvas(
    onLongPress: (index: Int) -> Unit
) {
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

        val rects = listOf(
            Offset(1130.3f, 1398.9f) to Size(53.1f, 165.4f) to "Garden",
            Offset(1187.3f, 1398.9f) to Size(41.3f, 165.4f) to "Garden",
            Offset(1233.3f, 1519.9f) to Size(87.0f, 44.4f) to "Garden",
//            Offset(1231.6f, 1416.3f) to Size(34.2f, 29.3f) to "Paint",
//            Offset(1233.1f, 1368.5f) to Size(32.7f, 43.9f) to "",
//            Offset(1233.1f, 1344.8f) to Size(32.7f, 19.4f) to "",
//            Offset(1231.6f, 1282.8f) to Size(64.3f, 53.4f) to "",
//            Offset(1300.9f, 1282.8f) to Size(50.6f, 53.4f) to "",
//            Offset(1355.2f, 1282.8f) to Size(92.3f, 53.4f) to "",
//            Offset(1452.7f, 1284.0f) to Size(37.1f, 52.3f) to "",
//            Offset(1494.1f, 1284.0f) to Size(28.3f, 52.3f) to "",
//            Offset(1525.9f, 1284.0f) to Size(28.4f, 52.3f) to "",
//            Offset(1565.2f, 1281.3f) to Size(58.9f, 23.7f) to "",
//            Offset(1628.7f, 1253.4f) to Size(56.3f, 51.6f) to "",
//            Offset(1586.7f, 1311.8f) to Size(61.3f, 86.6f) to "",
//            Offset(1658.3f, 1343.3f) to Size(25.0f, 56.5f) to "",
//            Offset(1650.7f, 1411.7f) to Size(10.7f, 40.9f) to "",
//            Offset(1586.7f, 1405.0f) to Size(61.3f, 47.5f) to "",
//            Offset(1586.7f, 1458.2f) to Size(68.3f, 33.0f) to "",
//            Offset(1586.7f, 1494.4f) to Size(37.4f, 25.9f) to "",
//            Offset(1627.5f, 1494.4f) to Size(27.4f, 25.9f) to "",
//            Offset(1685.9f, 1472.8f) to Size(40.0f, 76.1f) to "",
//            Offset(1567.6f, 1556.9f) to Size(19.1f, 20.1f) to "",
//            Offset(1378.9f, 1556.9f) to Size(18.7f, 20.1f) to "",
//            Offset(1327.1f, 1460.4f) to Size(52.1f, 38.1f) to "",
//            Offset(1384.6f, 1483.9f) to Size(189.1f, 25.4f) to "",
//            Offset(1456.8f, 1451.5f) to Size(113.4f, 23.1f) to "",
//            Offset(1513.5f, 1419.2f) to Size(56.7f, 28.6f) to "",
//            Offset(1456.8f, 1419.2f) to Size(55.7f, 28.6f) to "",
//            Offset(1476.7f, 1387.9f) to Size(93.4f, 24.2f) to "",
//            Offset(1438.3f, 1387.9f) to Size(34.6f, 24.2f) to "",
//            Offset(1438.3f, 1350.8f) to Size(34.6f, 34.6f) to "",
//            Offset(1476.7f, 1350.8f) to Size(93.4f, 34.6f) to "",
//            Offset(1438.3f, 1419.2f) to Size(15.5f, 55.5f) to "",
//            Offset(1393.5f, 1449.0f) to Size(39.1f, 25.7f) to "",
//            Offset(1393.5f, 1406.2f) to Size(39.1f, 24.7f) to "",
//            Offset(1393.5f, 1434.7f) to Size(39.1f, 10.2f) to "",
//            Offset(1335.1f, 1420.4f) to Size(44.1f, 23.8f) to "",
//            Offset(1353.1f, 1350.8f) to Size(26.0f, 62.5f) to "",
//            Offset(1334.3f, 1350.8f) to Size(14.0f, 62.5f) to "",
//            Offset(1321.7f, 1349.7f) to Size(10.2f, 63.5f) to "",
//            Offset(1304.0f, 1349.7f) to Size(15.0f, 63.5f) to "",
//            Offset(1280.3f, 1349.7f) to Size(20.6f, 47.8f) to "",
//            Offset(1280.3f, 1400.6f) to Size(20.6f, 12.6f) to "",
//            Offset(1280.3f, 1420.4f) to Size(48.0f, 23.8f) to "",
//            Offset(1393.5f, 1349.7f) to Size(39.1f, 21.6f) to "",
//            Offset(1393.5f, 1376.1f) to Size(39.1f, 25.2f) to "",
//            Offset(1417.3f, 1262.6f) to Size(20.9f, 18.7f) to "",
//            Offset(1446.0f, 1512.0f) to Size(19.3f, 19.3f) to "",
//            Offset(1341.2f, 1499.8f) to Size(19.0f, 17.4f) to "",
//            Offset(1357.1f, 1519.2f) to Size(19.1f, 19.1f) to "",
//            Offset(1403.1f, 1526.1f) to Size(18.6f, 18.6f) to "",
//            Offset(1410.8f, 1513.8f) to Size(18.6f, 20.4f) to "",
        )

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
                            onPress = { rawTapOffset ->
                                // Map screen tap to virtual canvas space
                                val adjustedTapOffset = (rawTapOffset - offset) / scale

                                Log.d(
                                    "Touch",
                                    "Raw tap: $rawTapOffset, Adjusted: $adjustedTapOffset"
                                )

                                rects.forEachIndexed { index, (rectPair, label) ->
                                    val (topLeft, size) = rectPair
                                    val rect = Rect(topLeft, size)
                                    if (rect.contains(adjustedTapOffset)) {
                                        Log.d("LongPress", "Tapped rect #$index")
                                        onLongPress(index)
                                        return@detectTapGestures
                                    }
                                }
                            }
                        )
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
                    drawRect(Color(0xFFF5F5F5))

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

                    val stroke = Stroke(width = 1f)

                    rects.forEach { (rectPair, label) ->
                        val (offsetRect, sizeRect) = rectPair
                        drawRect(
                            color = Color.Black,
                            topLeft = transformRectOffset(offsetRect),
                            size = transformRectSize(sizeRect),
                            style = stroke
                        )
                    }

                    // 2. Draw transformed text labels
                    val paint = Paint().asFrameworkPaint().apply {
                        isAntiAlias = true
                        textSize = 10f // Adjust for visibility
                        color = android.graphics.Color.BLACK
                    }

                    fun transformPoint(x: Float, y: Float): Pair<Float, Float> {
                        val tx = offsetXRect + (x - originalMinX) * finalScaleRect
                        val ty = offsetYRect + (y - originalMinY) * finalScaleRect
                        return tx to ty
                    }

                    val labelHeight = 6f

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
}

data class Node(val id: String, val position: Offset)
data class Edge(val from: String, val to: String, val weight: Float)

fun dijkstra(
    nodes: List<Node>,
    edges: List<Edge>,
    startId: String,
    endId: String
): List<Offset> {
    val nodeMap = nodes.associateBy { it.id }
    val distances = mutableMapOf<String, Float>().withDefault { Float.POSITIVE_INFINITY }
    val previous = mutableMapOf<String, String?>()
    val visited = mutableSetOf<String>()
    val queue = mutableListOf(startId)

    distances[startId] = 0f

    while (queue.isNotEmpty()) {
        val current = queue.minByOrNull { distances.getValue(it) } ?: break
        queue.remove(current)
        visited.add(current)

        edges.filter { it.from == current && it.to !in visited }.forEach { edge ->
            val alt = distances.getValue(current) + edge.weight
            if (alt < distances.getValue(edge.to)) {
                distances[edge.to] = alt
                previous[edge.to] = current
                if (edge.to !in queue) queue.add(edge.to)
            }
        }
    }

    val path = mutableListOf<Offset>()
    var u: String? = endId
    while (u != null) {
        path.add(nodeMap[u]!!.position)
        u = previous[u]
    }
    return path.reversed()
}

fun dist(a: Offset, b: Offset): Float = sqrt((a.x - b.x).pow(2) + (a.y - b.y).pow(2))
fun findNearestNode(position: Offset, nodes: List<Node>): Node = nodes.minByOrNull { dist(it.position, position) }!!

@Composable
fun CustomMapScreen() {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var userPosition by remember { mutableStateOf(Offset(90f, 90f)) }

    val nodes = listOf(
        Node("A", Offset(100f, 100f)), Node("B", Offset(200f, 100f)), Node("C", Offset(300f, 100f)),
        Node("D", Offset(100f, 200f)), Node("E", Offset(200f, 200f)), Node("F", Offset(300f, 200f)),
        Node("G", Offset(100f, 300f)), Node("H", Offset(200f, 300f)), Node("I", Offset(300f, 300f))
    )

    val edges = listOf(
        Edge("A", "B", 100f), Edge("B", "C", 100f),
        Edge("A", "D", 100f), Edge("B", "E", 100f), Edge("C", "F", 100f),
        Edge("D", "E", 100f), Edge("E", "F", 100f),
        Edge("D", "G", 100f), Edge("E", "H", 100f), Edge("F", "I", 100f),
        Edge("G", "H", 100f), Edge("H", "I", 100f)
    )

    val targetNode = "I"
    val startNode = findNearestNode(userPosition, nodes)
    val path = remember(startNode) { dijkstra(nodes, edges, startNode.id, targetNode) }

    var animatedPosition by remember { mutableStateOf(userPosition) }

    LaunchedEffect(path) {
        path.forEach {
            animatedPosition = it
            delay(500)
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTransformGestures { _, pan, zoom, _ ->
                scale = (scale * zoom).coerceIn(0.5f, 3f)
                offset += pan
            }
        }) {

        Canvas(modifier = Modifier.fillMaxSize()) {
            scale(scale, pivot = Offset.Zero) {
                translate(offset.x, offset.y) {
                    edges.forEach { edge ->
                        val from = nodes.find { it.id == edge.from }!!.position
                        val to = nodes.find { it.id == edge.to }!!.position
                        drawLine(Color.LightGray, from, to, strokeWidth = 4f)
                    }

                    nodes.forEach { node ->
                        drawCircle(
                            color = when (node.id) {
                                startNode.id -> Color.Green
                                targetNode -> Color.Red
                                else -> Color.DarkGray
                            },
                            radius = 15f,
                            center = node.position
                        )
                    }

                    path.windowed(2).forEach { (a, b) ->
                        drawLine(Color.Blue, a, b, strokeWidth = 6f)
                    }

                    drawCircle(
                        color = Color.Cyan,
                        radius = 10f,
                        center = animatedPosition
                    )
                }
            }
        }
    }
}